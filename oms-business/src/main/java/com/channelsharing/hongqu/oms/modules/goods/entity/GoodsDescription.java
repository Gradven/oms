/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.entity;


import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import lombok.Data;

/**
 * 商品介绍详情Entity
 * @author Karl
 * @version 2018-06-08
 */
@Data
public class GoodsDescription extends DataEntity<GoodsDescription> {

	private static final long serialVersionUID = 1L;
	private String content;		// 商品详情


	public GoodsDescription() {
		super();
	}

	public GoodsDescription(Long id){
		super(id);
	}



}
