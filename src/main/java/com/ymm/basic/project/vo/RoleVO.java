package com.ymm.basic.project.vo;

import com.ymm.basic.project.entity.Role;
import com.ymm.basic.project.entity.RoleResources;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVO extends Role {
    private List<RoleResources> roleResourcesList;
}
