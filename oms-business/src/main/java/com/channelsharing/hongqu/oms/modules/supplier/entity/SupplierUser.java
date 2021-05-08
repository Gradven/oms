/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.supplier.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import lombok.Data;
import com.channelsharing.hongqu.oms.common.persistence.DataEntity;

/**
 * 供应商运营用户Entity
 * @author Karl
 * @version 2018-06-05
 */
@Data
public class SupplierUser extends DataEntity<SupplierUser> {

	private static final long serialVersionUID = 1L;
	@Length(min=0, max=32, message="企业用户姓名长度必须介于 0 和 32 之间")
	private String name;		// 企业用户姓名

	@Length(min=1, max=512, message="登录密码长度必须介于 1 和 512 之间")
	private String password;		// 登录密码

	private Integer age;		// 年龄

	@Length(min=1, max=50, message="登录名长度必须介于 1 和 50 之间")
	private String account;		// 登录名

	@Length(min=0, max=20, message="手机号码长度必须介于 0 和 20 之间")
	private String mobile;		// 手机号码

	@Length(min=0, max=128, message="邮箱长度必须介于 0 和 128 之间")
	private String email;		// 邮箱

	private Integer status;		// 用户状态

	@NotNull(message="所属供应商不能为空")
	private Long supplierId;		// 所属供应商


	public SupplierUser() {
		super();
	}

	public SupplierUser(Long id){
		super(id);
	}



}
