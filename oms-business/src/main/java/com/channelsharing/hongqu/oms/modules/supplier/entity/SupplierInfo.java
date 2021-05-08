/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.supplier.entity;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 供应商信息Entity
 * @author Karl
 * @version 2018-06-05
 */
@Data
public class SupplierInfo extends DataEntity<SupplierInfo> {

	private static final long serialVersionUID = 1L;
	@Length(min=1, max=64, message="企业名称长度必须介于 1 和 64 之间")
	private String name;		// 企业名称

	@Length(min=0, max=24, message="统一社会信用代码长度必须介于 0 和 24 之间")
	private String creditCode;		// 统一社会信用代码

	@Length(min=0, max=255, message="营业执照图片长度必须介于 0 和 128 之间")
	private String licenseImg;		// 营业执照图片

	@Length(min=0, max=24, message="企业法人长度必须介于 0 和 24 之间")
	private String legalRepresentative;		// 企业法人

	@Length(min=0, max=16, message="登记机关所在省份长度必须介于 0 和 16 之间")
	private String regProvince;		// 登记机关所在省份

	@Length(min=0, max=255, message="地址长度必须介于 0 和 255 之间")
	private String address;		// 地址

	@Length(min=0, max=32, message="联系人长度必须介于 0 和 32 之间")
	private String contact;		// 联系人

	@Length(min=0, max=16, message="联系人手机号码长度必须介于 0 和 16 之间")
	private String mobile;		// 联系人手机号码

	@Length(min=0, max=16, message="固定电话长度必须介于 0 和 16 之间")
	private String phone;		// 固定电话

	 @Length(min=0, max=16, message="固定电话长度必须介于 0 和 16 之间")
	private String serviceTel;		// 客服电话

	private String serviceWeiXin;		// 客服微信

	// @Length(min=0, max=16, message="固定电话长度必须介于 0 和 16 之间")
	private String serviceQQ;		// 客服QQ

	private String serviceAddress;		// 客服地址

	@Length(min=0, max=128, message="电子邮箱长度必须介于 0 和 128 之间")
	private String email;		// 电子邮箱

	@NotNull(message="状态不能为空")
	private Integer status;		// 状态


	public SupplierInfo() {
		super();
	}

	public SupplierInfo(Long id){
		super(id);
	}



}
