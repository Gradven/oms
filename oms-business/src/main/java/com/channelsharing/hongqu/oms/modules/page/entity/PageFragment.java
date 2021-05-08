/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.page.entity;

import javax.validation.constraints.NotNull;

import com.sun.org.glassfish.gmbal.NameValue;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.omg.CORBA.NameValuePair;

/**
 * 页面区块配置Entity
 * @author liuhangjun
 * @version 2018-07-26
 */
@Data
public class PageFragment extends DataEntity<PageFragment> {

	private static final long serialVersionUID = 1L;
	@NotNull(message="页面id不能为空")
	private Long pageId;		// 页面id
	
	@NotNull(message="关键字key 不能为空")
	@Length(min=0, max=32, message="关键字长度必须介于 0 和 32 之间")
	private String keyword;		// 关键字

	@NotNull(message="区块类型不能为空")
	private Integer type;		// 区块类型（1:商品，2:商品分类，3:店铺）

	@Length(min=1, max=20, message="区块名称长度必须介于 1 和 20 之间")
	private String name;		// 区块名称

	@Length(min=1, max=2000, message="区块值长度必须介于 1 和 2000 之间")
	private String value;		// 区块值

	public PageFragment() {
		super();
	}

	public PageFragment(Long id){
		super(id);
	}



}
