package com.ymm.basic.project.vo;

import lombok.Data;

/**
 * Created by yemingming on 2021/12/9.
 */
@Data
public class LoginRequestPojo {

	/**
	 *  扫码登录
	 *  钉钉免登
	 */
	private String accessToken;


	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;


	/**
	 * 请求路径
	 */
	private String uri;


}
