package com.ymm.basic.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ymm.basic.project.entity.User;
import com.ymm.basic.project.model.GetEasyUIData;
import com.ymm.basic.project.vo.UserAddModel;
import com.ymm.basic.project.vo.UserPage;
import com.ymm.basic.project.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author y
 * @since 2021-09-18
 */
public interface IUserService extends IService<User>, UserDetailsService {

    GetEasyUIData list(UserPage user);

    void add(UserAddModel user);

    UserVO selectVOByPrimaryKey(Long id);

    void updateUser(UserAddModel muser);

    void deleteUser(Long[] id);

    String findTByT(User user);

    void updatePassword(UserAddModel muser);

    User findTByUsername(String userDetails);
}
