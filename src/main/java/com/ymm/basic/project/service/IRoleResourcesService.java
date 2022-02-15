package com.ymm.basic.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ymm.basic.project.entity.RoleResources;
import com.ymm.basic.project.vo.RoleResourcesVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author y
 * @since 2021-09-18
 */
public interface IRoleResourcesService extends IService<RoleResources> {

	/**
	 * 查询角色对应的url
 	 * @return
	 */
	List<RoleResourcesVO> selectRoleResourcesInfoList();


}
