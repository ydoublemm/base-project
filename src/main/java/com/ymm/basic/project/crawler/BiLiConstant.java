package com.ymm.basic.project.crawler;

/**
 * Created by yemingming on 2022/2/16.
 */
public interface BiLiConstant {


	/**
	 * 登录时间 10秒
	 */
	Long LOGIN_TIME = 10000L;


	interface Url {
		/**
		 * 总粉丝排行榜
		 */
		String FANS = "https://xz.newrank.cn/data/upMaster/singleRank/fans";


		/**
		 * 作品列表
		 */
		String WORKS = "https://xz.newrank.cn/data/d/up/works/%s";


		/**
		 * 作品分析
		 */
		String WORK_ANALYSIS = "https://xz.newrank.cn/data/d/up/workAnalysis/%s";


	}


	interface Cooike{

		/**
		 * cookie 文本相对路径
		 */
		String COOKIE_TEXT_PATH = "src/cookie.txt";

	}


}
