package com.ymm.basic.project.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ymm.basic.project.entity.RoleResources;
import com.ymm.basic.project.mapper.RoleResourcesMapper;
import com.ymm.basic.project.service.IRoleResourcesService;
import com.ymm.basic.project.vo.RoleResourcesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author y
 * @since 2021-09-18
 */
@Service
public class RoleResourcesServiceImpl extends ServiceImpl<RoleResourcesMapper, RoleResources> implements IRoleResourcesService {


	@Autowired
	private RoleResourcesMapper roleResourcesMapper;


	@Override
	public List<RoleResourcesVO> selectRoleResourcesInfoList() {

		return roleResourcesMapper.selectRoleResourcesInfoList();

	}
}
