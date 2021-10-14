package com.ymm.basic.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ymm.basic.project.entity.User;
import com.ymm.basic.project.enums.ResultEnum;
import com.ymm.basic.project.exception.CustomException;
import com.ymm.basic.project.form.AddUserForm;
import com.ymm.basic.project.form.ListUserForm;
import com.ymm.basic.project.mapper.UserMapper;
import com.ymm.basic.project.service.IUserService;
import com.ymm.basic.project.utils.MethodUtil;
import com.ymm.basic.project.vo.UserVo;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

/**
 * @author y
 * @date Created in 2020/3/6 4:50 下午
 * Utils: Intellij Idea
 * Description: 用户服务实现类
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private UserMapper userMapper;


    @Autowired
	private MapperFacade mapperFacade;

    /**
     * 添加用户
     * @param userForm 表单数据
     * @return true 或者 false
     */
    @Override
    public boolean addUser(AddUserForm userForm) {
        return save(mapperFacade.map(userForm,User.class));
    }

    /**
     * 获取用户列表
     * @param listUserForm 表单数据
     * @return 用户列表
     */
    @Override
    public IPage<User>  listUser(ListUserForm listUserForm) {

		IPage<User> userVoIPage = new Page<>(listUserForm.getCurrent(),listUserForm.getSize());

		LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
		wrapper.eq(User::getStatus,listUserForm.getStatus());

		IPage<User> userIPage = userMapper.selectPage(userVoIPage, wrapper);
		return userIPage;
    }

    /**
     * 删除用户
     * @param id id
     */
    @Override
    public void deleteUser(String id) {
        // 如果删除失败抛出异常。 -- 演示而已不推荐这样干
        if(!removeById(id)){
            throw new CustomException(ResultEnum.DELETE_ERROR, MethodUtil.getLineInfo());
        }
    }

    /**
     * 获取用户数量
     * @param status 状态
     * @return 用户数量
     */
    private Integer countUser(String status){
        return count(new QueryWrapper<User>().eq("status",status));
    }

}
