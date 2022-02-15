package com.ymm.basic.project.config.authorization;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yemingming on 2021/12/9.
 *
 *
 *
 * 钉钉免登
 * 扫码登录
 * 账号密码登录
 *
 *
 */

public class LoginFilter extends AbstractAuthenticationProcessingFilter {


	public LoginFilter() {
		super("/listUser");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		return null;
	}
}
