package com.ymm.basic.project.config.authorization;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by yemingming on 2021/12/8.
 */

public class BasicUserInfo implements UserDetails {

	private Long userId;

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

	private Collection<? extends GrantedAuthority> authorities;



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public BasicUserInfo(Long userId, String userCode, String userName, String orgCode, String orgName, String areaCode,
			String areaName, Integer status, Collection<? extends GrantedAuthority> authorities) {
		this.userId = userId;
		this.userCode = userCode;
		this.userName = userName;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.areaCode = areaCode;
		this.areaName = areaName;
		this.status = status;
		this.authorities = authorities;
	}

	public BasicUserInfo() {
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}



}
