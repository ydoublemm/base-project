package com.ymm.basic.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author y
 * @since 2021-09-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("system_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;


	/**
	 * 用户唯一code-2.0浙政钉
	 */
	private String userCode;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 部门code
	 */
	private String orgCode;

	/**
	 * 部门名称
	 */
	private String orgName;

	/**
	 * 所属区域code
	 */
	private String areaCode;

	/**
	 * 地区名称
	 */
	private String areaName;


	private Integer status;
}
