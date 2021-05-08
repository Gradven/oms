/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.specification.entity;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 规格维度信息Entity
 * @author Karl
 * @version 2018-06-05
 */
@Data
public class SpecificationInfo extends DataEntity<SpecificationInfo> {

	private static final long serialVersionUID = 1L;
	@Length(min=0, max=64, message="规格维度名称长度必须介于 0 和 64 之间")
	private String name;		// 规格维度名称

	private Integer sortOrder;		// 排序


	public SpecificationInfo() {
		super();
	}

	public SpecificationInfo(Long id){
		super(id);
	}



}
