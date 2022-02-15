package com.ymm.basic.project.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ymm.basic.project.entity.Resources;
import com.ymm.basic.project.model.JsonTreeData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author y
 * @since 2021-09-18
 */
public interface IResourcesService extends IService<Resources> {
    void deleteResources(Long[] id);

    void insertResources(Resources resources);

    List<JsonTreeData> findResources();

    List<Resources> findResourcesEMUByResources(String username);

    void updateResources(Resources resources);

    Resources selectByPrimaryKey(Long id);

}
