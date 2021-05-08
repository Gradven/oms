/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.entity;

import com.channelsharing.hongqu.oms.common.persistence.TreeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;

/**
 * 商品分类Entity
 *
 * @author Karl
 * @version 2018-06-05
 */
public class GoodsCategory extends TreeEntity<GoodsCategory> {

	private static final long serialVersionUID = 1L;
	private GoodsCategory parent; // 父节点
	private String parentIds; // 节点路径
	private String name; // 分类名称
	private String code; // 分类编码
	private String picUrl; // 分类图片

	public GoodsCategory() {
		super();
	}

	public GoodsCategory(Long id) {
		super(id);
	}

	@JsonBackReference
	public GoodsCategory getParent() {
		return parent;
	}

	public void setParent(GoodsCategory parent) {
		this.parent = parent;
	}

	@Length(min = 0, max = 2000, message = "节点路径长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Length(min = 1, max = 64, message = "分类名称长度必须介于 1 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 0, max = 16, message = "分类编码长度必须介于 0 和 16 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : 0;
	}

	@Length(min = 0, max = 255, message = "分类图片长度必须介于 0 和 255 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
