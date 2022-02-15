package com.ymm.basic.project.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ymm.basic.project.entity.Role;
import com.ymm.basic.project.entity.RoleResources;
import com.ymm.basic.project.mapper.RoleMapper;
import com.ymm.basic.project.model.CodeEnum;
import com.ymm.basic.project.model.GetEasyUIData;
import com.ymm.basic.project.service.IRoleResourcesService;
import com.ymm.basic.project.service.IRoleService;
import com.ymm.basic.project.service.IUserRoleService;
import com.ymm.basic.project.vo.RoleAddModel;
import com.ymm.basic.project.vo.RolePage;
import com.ymm.basic.project.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author y
 * @since 2021-09-19
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private IRoleResourcesService roleResourcesService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    @Transactional
    public void insertRole(RoleAddModel role) {
        Long[] resourcesIds = role.getResourcesId();
        save(role);
        if (resourcesIds == null) {
            return;
        }
        for (Long resourcesId : resourcesIds) {
            RoleResources roleResources = new RoleResources();
            roleResources.setResourcesId(resourcesId);
            roleResources.setRoleId(role.getId());
            roleResourcesService.save(roleResources);
        }
    }

    @Override
    @Transactional
    public void updateRole(RoleAddModel role) {
        Assert.notNull(role.getId(), CodeEnum.NO_NULL.getCode());
        Long[] resourcesIds = role.getResourcesId();
        updateById(role);

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("role_id", role.getId());
        roleResourcesService.remove(updateWrapper);
        if (resourcesIds == null) {
            return;
        }
        for (Long resourcesId : resourcesIds) {
            RoleResources roleResources = new RoleResources();
            roleResources.setResourcesId(resourcesId);
            roleResources.setRoleId(role.getId());
            roleResourcesService.save(roleResources);
        }
    }

    @Override
    public void deleteRole(Long[] id) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.in("role_id", id);
        userRoleService.remove(updateWrapper);
        roleResourcesService.remove(updateWrapper);
        removeByIds(ListUtil.toList(id));
    }

    @Override
    public GetEasyUIData findTByPage(RolePage role) {
        IPage<Role> page = new Page<>(role.getPage(), role.getRows());
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StrUtil.isNotBlank(role.getName())) {
            queryWrapper.eq("name", role.getName());
        }
        IPage<Role> page1 = page(page, queryWrapper);
        return new GetEasyUIData(page1.getRecords(), page1.getTotal());

    }

    @Override
    public RoleVO selectByPrimaryKey(Long id) {
        Role role = getById(id);
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(role, roleVO);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role_id", id);
        List<RoleResources> roleResourcesList = roleResourcesService.list(queryWrapper);
        roleVO.setRoleResourcesList(roleResourcesList);
        return roleVO;
    }


}
