package com.ymm.basic.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ymm.basic.project.entity.Role;
import com.ymm.basic.project.model.GetEasyUIData;
import com.ymm.basic.project.vo.RoleAddModel;
import com.ymm.basic.project.vo.RolePage;
import com.ymm.basic.project.vo.RoleVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author y
 * @since 2021-09-18
 */
public interface IRoleService extends IService<Role> {

    void insertRole(RoleAddModel role);

    void updateRole(RoleAddModel role);

    void deleteRole(Long[] id);

    GetEasyUIData findTByPage(RolePage role);

    RoleVO selectByPrimaryKey(Long id);
}
