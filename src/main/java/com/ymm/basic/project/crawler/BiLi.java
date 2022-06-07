package com.ymm.basic.project.crawler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ymm.basic.project.crawler.service.impl.BiLiServiceImpl;
import com.ymm.basic.project.entity.UpOverview;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.ymm.basic.project.crawler.BiLiConstant.Cooike.COOKIE_TEXT_PATH;

/**
 * Created by yemingming on 2022/2/15.
 */
public class BiLi {

	public static void main(String[] args) throws InterruptedException, IOException {


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

		String html = webDriver.getPageSource();


		List<WebElement> spanList = ((ChromeDriver) webDriver)
				.findElementsByXPath("//span[@class='ant-tag ant-tag-checkable']");

		for(WebElement webElement : spanList){
			String text = webElement.getText();
			if(text.equals("知识")){
				webElement.click();
			}
		}

		Thread.sleep(2000);

		//模糊匹配 tr
		List<WebElement> elementsList = ((ChromeDriver) webDriver).findElementsByXPath("//tr[contains(@class,'ant-table-row ant-table-row-level-0')]");

		Thread.sleep(2000);
		for(WebElement webElement : elementsList){

			//其他信息
			String[] info = webElement.getText().split("\\n");

			UpOverview upOverview = new UpOverview();
			upOverview.setRank(Integer.parseInt(info[0]));
			upOverview.setName(info[1]);
			upOverview.setLevel(Integer.parseInt(info[2].replace("lv","")));
			upOverview.setUpPartition(info[3]);
			upOverview.setFansInfo(info[4]);
			upOverview.setUid(info[5].replace("Uid",""));
			upOverview.setDescription(info[6]);

			String[] count = info[7].split(" ");
			upOverview.setVideoCount(count[0]);
			upOverview.setAvgPlay(count[1]);
			upOverview.setAvgLike(count[2]);
			upOverview.setFollower(count[3]);

			//头像
			String src = webElement.findElement(By.xpath("//img[@referrerpolicy='no-referrer']")).getAttribute("src");

			String img = src.split("@")[0];
			upOverview.setFace(img);

		}

		//driver.find_element_by_xpath("//tag_name[contains(@id,'kw')]").click()


		//

		//webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class='ant-table-row ant-table-row-level-0'])")));
		//Thread.sleep(5000);
		//滑动条拉到最下面
		//((ChromeDriver) webDriver).executeScript("window.scrollTo(0,document.body.scrollHeight)");




		//将字符串变成document对象来获取某个节点的数据
//		Document document= Jsoup.parse(html);
//
//		Elements elements = document.select("span");
//
//
//		for(int i=0;i<elements.size();i++){
//			String text = elements.get(i).text();
//
//			if(text.equals("知识")){
//				System.out.println(1111);
//			}
//
//		}


//		WebElement searchBox = webDriver.findElement(By.name("q"));
//		WebElement searchButton = webDriver.findElement(By.name("btnK"));
//
//		searchBox.sendKeys("Selenium");
//		searchButton.click();
//
//		searchBox = webDriver.findElement(By.name("q"));
//		searchBox.getAttribute("value"); // => "Selenium"
//
//		//拿到页面的数据
//		//String html=driver.getPageSource();
//		System.out.println("The testing page title is: " + webDriver.getTitle());
//
//		System.out.println(document);

		//driver.quit();
	}


	public static void addCookie(WebDriver driver ,final String cookie) {

		String[] items = cookie.trim().split(";");

		for (String item:items) {
			String key = item.split("=")[0];

			String value = item.split("=")[1];
			Cookie c = new Cookie(key.trim(),value.trim());
			driver.manage().addCookie(c);
		}

	}



}
