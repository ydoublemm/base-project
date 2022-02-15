package com.ymm.basic.project.config.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymm.basic.project.vo.LoginRequestPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 认证
 * @date 2019/3/14 17:46
 *
 * Filter->构造Token->AuthenticationManager->转给Provider处理->认证处理成功后续操作或者不通过抛异常
 *
 */

@Slf4j
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private StringRedisTemplate redisTemplate;

    private ObjectMapper objectMapper;

    private AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.redisTemplate = redisTemplate;
        super.setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            //从body中取出,参数是在请求体中使用json形式传入的

			LoginRequestPojo requestPojo = objectMapper.readValue(request.getInputStream(), LoginRequestPojo.class);
			requestPojo.setUri(requestPojo.getUri());

            log.info("用户请求登录信息:{}",objectMapper.writeValueAsString(requestPojo));
            //模仿UsernamePasswordAuthenticationFilter的方式，将用户名密码进行比较
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(objectMapper.writeValueAsString(requestPojo), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        AuthUser user = (AuthUser) authResult.getPrincipal();
//        String existToken = redisTemplate.opsForValue().get(USER_MOBILE_PRE + user.getUsername());
//        if(StringUtils.isNotEmpty(existToken)) {
//            redisTemplate.expire(USER_MOBILE_PRE + user.getUsername(), 0, TimeUnit.SECONDS);
//            redisTemplate.expire(USER_TOKEN_PRE + existToken, 0, TimeUnit.SECONDS);
//        }
//
//        String uuid = UUID.randomUUID().toString();
//        redisTemplate.opsForValue().set(USER_MOBILE_PRE + user.getUsername(), uuid);
//        redisTemplate.opsForValue().set(USER_TOKEN_PRE + uuid, user.getUsername());
//
//        response.setStatus(200);
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().print("{\n" +
//                "    \"code\": 1,\n" +
//                "    \"data\": {\n" +
//                "        \"username\": \""+user.getUsername()+"\",\n" +
//                "        \"token\": \""+uuid+"\",\n" +
//                "        \"roles\": "+objectMapper.writeValueAsString(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))+"\n" +
//                "    }\n" +
//                "}");
//        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(500);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("{\"code\": 500, \n \"msg\":\"用户名或密码不正确，登陆失败\"}");
        response.flushBuffer();
    }
}
