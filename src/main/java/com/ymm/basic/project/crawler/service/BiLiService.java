package com.ymm.basic.project.crawler.service;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * Created by yemingming on 2022/2/15.
 */
public interface BiLiService {

	/**
	 * 初始化
	 * @param url
	 * @return
	 */
	WebDriver WebDriverInit(String url);


	/**
	 * 登录后保存cookie
	 * @param url
	 * @return
	 */
	void loginAndSaveCookie(String url,WebDriver driver) throws InterruptedException, IOException;


	/**
	 * 本地 cookie 是否过期
	 * @return
	 */
	boolean cookieExpiry() throws IOException;


	/**
	 * 保存up信息总揽数据
	 * @return
	 */
	void saveUpOverdata(String type) throws IOException, InterruptedException;


	/**
	 * 保存up作品列表
	 * @return
	 */
	void saveUpWorks(String uid) throws IOException, InterruptedException;
}
