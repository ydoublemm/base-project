package com.ymm.basic.project.crawler.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.ymm.basic.project.crawler.BiLiConstant;
import com.ymm.basic.project.crawler.service.BiLiService;
import com.ymm.basic.project.entity.UpOverview;
import com.ymm.basic.project.service.IUpOverviewService;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.ymm.basic.project.crawler.BiLiConstant.Cooike.COOKIE_TEXT_PATH;
import static com.ymm.basic.project.crawler.BiLiConstant.LOGIN_TIME;

/**
 * Created by yemingming on 2022/2/15.
 */
@Slf4j
@Service
public class BiLiServiceImpl implements BiLiService {

	@Autowired
	private IUpOverviewService upOverviewService;


	@Override
	public WebDriver WebDriverInit(String url) {
		WebDriver driver = new ChromeDriver();

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);

		return driver;
	}


	/**
	 * 登录后保存cookie
	 * @param url
	 * @return
	 */
	@Override
	public void loginAndSaveCookie(String url,WebDriver driver) throws InterruptedException, IOException {



		//扫码登录时间
		Thread.sleep(LOGIN_TIME);

		Set<Cookie> cookies = driver.manage().getCookies();
		//driver.manage().deleteAllCookies();

		//清空文件
		File file = new File(COOKIE_TEXT_PATH);
		FileWriter fileWriter =new FileWriter(file);
		fileWriter.write("");
		fileWriter.flush();

		String cookieStr = JSONArray.toJSONString(cookies);
		fileWriter.write(cookieStr);
		fileWriter.flush();
		fileWriter.close();

		log.info("本地cookie保存成功");
		driver.quit();
	}

	/**
	 * 本地 cookie 是否过期
	 * @return
	 */
	@Override
	public boolean cookieExpiry() throws IOException {




		/**
		 * 文本cookie
		 */
		String cookieStr = Files.readAllLines(Paths.get(COOKIE_TEXT_PATH), StandardCharsets.UTF_8).
				stream().collect(Collectors.joining(""));

		if(StringUtils.isEmpty(cookieStr)){
			return true;
		}

		List<Cookie> cookieList = JSONArray.parseArray(cookieStr, Cookie.class);

		//最小过期时间
		Optional<Cookie> minExpiry = cookieList.stream().filter(e -> !ObjectUtils.isEmpty(e.getExpiry()))
				.min(Comparator.comparing(Cookie::getExpiry));

		if(!minExpiry.isPresent()){
			return true;
		}


		if(minExpiry.get().getExpiry().compareTo(new Date()) < 0){
			return true;
		}


		return false;
	}

	/**
	 * 保存up信息总揽数据
	 * @return
	 */
	@Override
	public void saveUpOverdata(String type) throws IOException, InterruptedException {
		BiLiServiceImpl biLiService = new BiLiServiceImpl();

		WebDriver webDriver = biLiService.WebDriverInit(BiLiConstant.Url.FANS);



		//biLiService.loginAndSaveCookie(BiLiConstant.Url.FANS,webDriver);


		String cookieStr = Files.readAllLines(Paths.get(COOKIE_TEXT_PATH), StandardCharsets.UTF_8).get(0);

		List<Cookie> cookies = JSONArray.parseArray(cookieStr, Cookie.class);

		for(Cookie cookie : cookies) {
			webDriver.manage().addCookie(cookie);
		}

		webDriver.get(BiLiConstant.Url.FANS);


		//等待 知道标签出来
		WebDriverWait webDriverWait = new WebDriverWait(webDriver,10);
		webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='ant-tag ant-tag-checkable']")));

		//webDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);

		Thread.sleep(2000);


		List<WebElement> spanList = ((ChromeDriver) webDriver)
				.findElementsByXPath("//span[@class='ant-tag ant-tag-checkable']");



		for(WebElement webElement : spanList){
			String text = webElement.getText();
			if(text.equals(type)){
				webElement.click();
			}
		}

		Thread.sleep(2000);

		//滚轮下滑
		scrollToBottom(webDriver,13);

		//模糊匹配 tr
		List<WebElement> elementsList = ((ChromeDriver) webDriver).findElementsByXPath("//tr[contains(@class,'ant-table-row ant-table-row-level-0')]");

		Thread.sleep(2000);
		for(WebElement webElement : elementsList){

			//其他信息
			String[] info = webElement.getText().split("\\n");

			//已注销特殊判断
			if(info.length < 11){
				continue;
			}

			try {


				UpOverview upOverview = new UpOverview();
				upOverview.setRank(Integer.parseInt(info[0]));
				upOverview.setName(info[1]);
				upOverview.setLevel(Integer.parseInt(info[2].replace("lv", "")));
				upOverview.setUpPartition(info[3]);
				upOverview.setFansInfo(info[4]);
				upOverview.setUid(info[5].replace("Uid:", ""));
				upOverview.setDescription(info[6]);

				String[] count = info[7].split(" ");
				upOverview.setVideoCount(count[0]);
				upOverview.setAvgPlay(count[1]);
				upOverview.setAvgLike(count[2]);
				upOverview.setFollower(count[3]);

				//头像
				String src = webElement.findElement(By.xpath(".//img[@referrerpolicy='no-referrer']")).getAttribute("src");

				String img = src.split("@")[0];
				upOverview.setFace(img);
				upOverview.setCreateTime(DateUtil.date());

				upOverviewService.save(upOverview);
			}catch (Exception e){
				log.error("数据出错",e);
			}
		}

		webDriver.quit();
	}


	/**
	 * 保存up作品列表
	 * @return
	 * @param uid
	 */
	@Override
	public void saveUpWorks(String uid) throws IOException, InterruptedException {

		String url = String.format(BiLiConstant.Url.WORKS,uid);

		BiLiServiceImpl biLiService = new BiLiServiceImpl();



		WebDriver webDriver = biLiService.WebDriverInit(url);



		//biLiService.loginAndSaveCookie(BiLiConstant.Url.FANS,webDriver);
		//ant-table-row ant-table-row-level-0
		//ant-table-row ant-table-row-level-0
		//tr[contains(@class,'ant-table-row ant-table-row-level-0')]
		String cookieStr = Files.readAllLines(Paths.get(COOKIE_TEXT_PATH), StandardCharsets.UTF_8).get(0);

		List<Cookie> cookies = JSONArray.parseArray(cookieStr, Cookie.class);

		for(Cookie cookie : cookies) {
			webDriver.manage().addCookie(cookie);
		}

		webDriver.get(url);
		Thread.sleep(2000);

		//滚轮下滑
		scrollToBottom(webDriver,13);

		WebElement tbody = ((ChromeDriver) webDriver)
				.findElementByXPath("//tbody[@class='ant-table-tbody']");

		List<WebElement> trList = ((ChromeDriver) webDriver).findElementByXPath("//tbody[@class='ant-table-tbody']")
				.findElements(By.xpath(".//tr[contains(@class,'ant-table-row ant-table-row-level-0')]"));


		for(WebElement webElement : trList){

			//String[] strings = webElement.getText().split("\\n");
			String time = webElement.findElements(By.tagName("td")).get(0).findElements(By.xpath(".//div")).get(1).getText();
			String name = webElement.findElements(By.tagName("td")).get(0).findElements(By.xpath(".//div")).get(3).findElements(By.xpath(".//div")).get(0).getText();




		}


	}

	public static void main(String[] args) throws IOException, InterruptedException {

		BiLiServiceImpl biLiService = new BiLiServiceImpl();
		biLiService.saveUpWorks("546195");

	}

//			0 = "04:49"
//			1 = "如何做出惊艳 100 年的五彩斑斓的黑？"
//			2 = "1657"
//			3 = "34"
//			4 = "56"
//			5 = "发布于2020-10-27"
//			6 = "数据更新于2021-03-03 04:39:22"
//			7 = "419 285 154 1.95w"
//
//					0 = "12:30"
//					1 = "“你们中国人不可能做出成绩，赶紧回国吧，钟南山！”"
//					2 = "活动"
//					3 = "5.30w"
//					4 = "2150"
//					5 = "1498"
//					6 = "发布于2022-02-12"
//					7 = "数据更新于2022-02-18 07:22:25"
//					8 = "2.65w 1.00w 4159 50.37w"

	/**
	 * 滚轮下滑
	 * @param webDriver
	 * @param times
	 * @throws InterruptedException
	 */
	private void scrollToBottom(WebDriver webDriver,Integer times) throws InterruptedException {
		//滚轮下滑
		for(int i=0;i<times;i++){
			((ChromeDriver) webDriver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
			Thread.sleep(2000);
		}
	}

}
