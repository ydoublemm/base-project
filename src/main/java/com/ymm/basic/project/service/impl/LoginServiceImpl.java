package com.ymm.basic.project.service.impl;


import com.ymm.basic.project.service.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {





    @Override
    public void logoutJSON() {

    }

    @Override
    public String getStatus(HttpServletRequest request) {
        return request.getAttribute("javax.servlet.error.status_code").toString();
    }

}


