package com.ymm.basic.project.crawler.service.impl;

import com.ymm.basic.project.crawler.service.BiLiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by yemingming on 2022/2/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BiLiServiceImplTest {

	@Autowired
	private BiLiService biLiService;


	@Test
	public void saveUpOverdata() {

		try {
			biLiService.saveUpOverdata("生活");
			biLiService.saveUpOverdata("游戏");
			biLiService.saveUpOverdata("知识");
			biLiService.saveUpOverdata("动画");
			biLiService.saveUpOverdata("娱乐");
			biLiService.saveUpOverdata("音乐");
			biLiService.saveUpOverdata("影视");
			biLiService.saveUpOverdata("时尚");
			biLiService.saveUpOverdata("汽车");
			biLiService.saveUpOverdata("舞蹈");
			biLiService.saveUpOverdata("美食");
			biLiService.saveUpOverdata("动物圈");
			biLiService.saveUpOverdata("科技");
			biLiService.saveUpOverdata("运动");
			biLiService.saveUpOverdata("鬼畜");
			biLiService.saveUpOverdata("国创");
			biLiService.saveUpOverdata("数码");
			biLiService.saveUpOverdata("番剧");
			biLiService.saveUpOverdata("纪录片");
			biLiService.saveUpOverdata("资讯");
			biLiService.saveUpOverdata("电影");
			biLiService.saveUpOverdata("电视剧");


		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}