package com.ymm.basic.project.config.authorization;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author y
 * @date 2019/12/19 14:22
 */

@Service
public class UserInfoServiceImpl implements  UserDetailsService {



//  @Autowired
//	private RestTemplate restTemplate;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


	@Override
	public UserDetails loadUserByUsername(String loginInfo) {



		return null;

	}




}
