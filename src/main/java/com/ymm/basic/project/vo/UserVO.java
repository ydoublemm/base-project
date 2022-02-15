package com.ymm.basic.project.vo;

import com.ymm.basic.project.entity.User;
import com.ymm.basic.project.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends User {
    private List<UserRole> UserRoleList;
}
