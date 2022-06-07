package com.ymm.basic.project.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yemingming on 2022/2/15.
 *
 * 静态html爬取
 */
public class StaticHtmlCrawler {

	public static void main(String args[]){

		//这个就是博客中的java反射的url
		final String url=  "https://xz.newrank.cn/data/d/video/videoReview/BV1dP4y1w7ao";

		final String cookie = "sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22nr_4l89ix20t%22%2C%22first_id%22%3A%2217ef5ec2748eb-037232fd42cab16-49193201-2073600-17ef5ec2749df4%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22%24device_id%22%3A%2217ef5ec2748eb-037232fd42cab16-49193201-2073600-17ef5ec2749df4%22%7D; UM_distinctid=17ef6f9eb9c6b8-061ab98b07aa8a-49193201-1fa400-17ef6f9eb9d973; Hm_lpvt_5c88d1bb9ac529f1f4a8b2be9e785fcb=1644803766; Hm_lpvt_ab2358e695ccada3424acb6402afd2cb=1644803766; Hm_lvt_5c88d1bb9ac529f1f4a8b2be9e785fcb=1644803399; Hm_lvt_ab2358e695ccada3424acb6402afd2cb=1644803399";


		try {
			//先获得的是整个页面的html标签页面

			HashMap<String, String> stringStringHashMap = convertCookie(cookie);

			Document doc = Jsoup.connect(url).cookies(stringStringHashMap).get();

			//获取正文标题，因为整片文章只有标题是用h1标签
			Elements btEl = doc.select("H1");
			String  bt=btEl.text();
			System.out.println("========正文标题======：");
			System.out.println(bt);

			//获取二级标题
			Elements ejbtEls = doc.select("H2");
			//因为整片文章有多个二级标题所以进行拼接
			StringBuilder  ejbts=new  StringBuilder();
			for(Element el :ejbtEls) {
				ejbts.append(el.text());
				ejbts.append("\n");
			}
			String ejbt=ejbts.toString();
			System.out.println("=======二级标题=========：");
			System.out.println(ejbt);

			//获取时间
			Elements timeEl = doc.select("#post-date");
			String  time=timeEl.text();
			System.out.println("========发布时间=========：");
			System.out.println("发布时间：" + time);

			//获取阅读数量
			Elements readEl = doc.select("#post_view_count");
			String  read=readEl.text();
			System.out.println("========阅读数量=========：");
			System.out.println("阅读数量：" + read);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, String> convertCookie(String cookie) {
		HashMap<String, String> cookiesMap = new HashMap<String, String>();
		String[] items = cookie.trim().split(";");
		for (String item:items) cookiesMap.put(item.split("=")[0], item.split("=")[1]);
		return cookiesMap;
	}

}
