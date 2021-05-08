/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.product.entity;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 产品信息Entity
 * @author Karl
 * @version 2018-06-11
 */
@Data
public class ProductInfo extends DataEntity<ProductInfo> {

	private static final long serialVersionUID = 1L;
	@Length(min=0, max=12, message="产品编号长度必须介于 0 和 12 之间")
	private String sn;		// 产品编号

	private Long goodsId;		// 商品编号

	@Length(min=0, max=64, message="商品规格ids长度必须介于 0 和 64 之间")
	private String goodsSpecificationIds;		// 商品规格ids

	@Length(min=0, max=64, message="商品序列号长度必须介于 0 和 64 之间")
	private String goodsSn;		// 商品序列号

	private Integer storeNumber;		// 库存

	@NotNull(message="零售价格不能为空")
	private BigDecimal retailPrice;		// 零售价格

	@NotNull(message="价格不能为空")
	private BigDecimal unitPrice;		// 价格

	@NotNull(message="利润不能为空")
	private BigDecimal profit;		// 利润

	private String goodsSpecificationDes;
	private String goodsName;
	private String picUrl;
	private String salesVolume;

	public ProductInfo() {
		super();
	}

	public ProductInfo(Long id){
		super(id);
	}



}
