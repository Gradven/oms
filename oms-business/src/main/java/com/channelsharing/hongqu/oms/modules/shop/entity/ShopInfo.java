/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.shop.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import com.channelsharing.hongqu.oms.common.persistence.DataEntity;

/**
 * 店铺信息Entity
 * @author Karl
 * @version 2018-08-27
 */
@Data
public class ShopInfo extends DataEntity<ShopInfo> {

	private static final long serialVersionUID = 1L;
	@Length(min=1, max=32, message="店铺名称长度必须介于 1 和 32 之间")
	private String name;		// 店铺名称

	@Length(min=1, max=255, message="店铺logo长度必须介于 1 和 255 之间")
	private String logo;		// 店铺logo

	@Length(min=0, max=128, message="介绍长度必须介于 0 和 128 之间")
	private String description;		// 介绍

	@Length(min=0, max=255, message="背景图地址长度必须介于 0 和 255 之间")
	private String backgroundUrl;		// 背景图地址

	@NotNull(message="创建人不能为空")
	private Long userId;		// 创建人

	private Integer certificateFlag;		// 是否认证,0:未认证，1:已认证

	private Integer payFeeFlag;		// 缴费标志：0:未缴费，1：已缴费

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expireTime;		// 到期时间


	public ShopInfo() {
		super();
	}

	public ShopInfo(Long id){
		super(id);
	}



}
