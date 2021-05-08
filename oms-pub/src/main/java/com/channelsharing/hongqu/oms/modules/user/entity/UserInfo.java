/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.user.entity;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 用户信息Entity
 * @author Karl
 * @version 2018-06-13
 */
@Data
public class UserInfo extends DataEntity<UserInfo> {

	private static final long serialVersionUID = 1L;
	@Length(min=0, max=32, message="用户昵称长度必须介于 0 和 32 之间")
	private String nickname;		// 用户昵称

	@Length(min=0, max=24, message="手机号码长度必须介于 0 和 24 之间")
	private String mobile;		// 手机号码

	@Length(min=0, max=64, message="电子邮箱长度必须介于 0 和 64 之间")
	private String email;		// 电子邮箱

	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	private String address;		// 地址

	@Length(min=0, max=128, message="工作单位长度必须介于 0 和 128 之间")
	private String company;		// 工作单位

	@Length(min=0, max=255, message="联系方式长度必须介于 0 和 255 之间")
	private String contact;		// 联系方式

	@Length(min=0, max=128, message="密码长度必须介于 0 和 128 之间")
	private String password;		// 密码

	private Integer loginErrorTimes;		// 登录错误次数

	private Integer sex;		// 性别

	@Length(min=0, max=255, message="头像地址长度必须介于 0 和 255 之间")
	private String avatar;		// 头像地址

	private Integer status;		// 状态

	@Length(min=0, max=8, message="激活码长度必须介于 0 和 8 之间")
	private String activationCode;		// 激活码

	@Length(min=0, max=64, message="第三方账号长度必须介于 0 和 64 之间")
	private String thirdPartyUserId;		// 第三方账号

	private Integer accountType;		// 账号类型

	private Long shopId;		// 店铺id

	@Length(min=0, max=64, message="国家长度必须介于 0 和 64 之间")
	private String country;		// 国家

	@Length(min=0, max=64, message="省份长度必须介于 0 和 64 之间")
	private String province;		// 省份

	@Length(min=0, max=64, message="城市长度必须介于 0 和 64 之间")
	private String city;		// 城市

	@Length(min=0, max=255, message="用户简介长度必须介于 0 和 255 之间")
	private String description;		// 用户简介


	public UserInfo() {
		super();
	}

	public UserInfo(Long id){
		super(id);
	}



}
