/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.entity;

import javax.validation.constraints.NotNull;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 商品对应规格表值表Entity
 * @author Karl
 * @version 2018-06-06
 */
@Data
public class GoodsSpecification extends DataEntity<GoodsSpecification> {

	private static final long serialVersionUID = 1L;
	@NotNull(message="商品编号不能为空")
	private Long goodsId;		// 商品编号

	@Length(min=1, max=11, message="规格编号长度必须介于 1 和 11 之间")
	private String specificationId;		// 规格编号

	@Length(min=0, max=64, message="规格值长度必须介于 0 和 64 之间")
	private String value;		// 规格值

	@Length(min=0, max=255, message="图片长度必须介于 0 和 255 之间")
	private String picUrl;		// 图片

	private String goodsName;

	private String specificationName;

	private String specificationIds;



	public GoodsSpecification() {
		super();
	}

	public GoodsSpecification(Long id){
		super(id);
	}



}
