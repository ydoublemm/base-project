package com.ymm.basic.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ymm.basic.project.entity.RoleResources;
import com.ymm.basic.project.vo.RoleResourcesVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author y
 * @since 2021-09-18
 */
public interface RoleResourcesMapper extends BaseMapper<RoleResources> {


		List<RoleResourcesVO> selectRoleResourcesInfoList();


}
