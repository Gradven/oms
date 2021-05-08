/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.page.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import com.channelsharing.hongqu.oms.common.persistence.TreeEntity;

/**
 * 页面信息Entity
 * @author liuhangjun
 * @version 2018-07-26
 */
@Data
public class PageInfo extends TreeEntity<PageInfo> {

	private static final long serialVersionUID = 1L;
	
	@JsonBackReference
	@NotNull(message="父节点id不能为空")
	private PageInfo parent;		// 父节点id
	
	@Length(min=1, max=2000, message="所有父节点长度必须介于 1 和 2000 之间")
	private String parentIds;		// 所有父节点
	
	@Length(min=1, max=20, message="名称长度必须介于 1 和 20 之间")
	private String name;		// 名称

	public PageInfo() {
		super();
	}

	public PageInfo(Long id){
		super(id);
	}
	

	public Long getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : 0;
	}
}
