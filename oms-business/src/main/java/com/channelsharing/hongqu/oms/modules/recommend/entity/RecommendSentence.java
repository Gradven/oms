/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.recommend.entity;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import com.channelsharing.hongqu.oms.common.persistence.DataEntity;

/**
 * 商品推荐语Entity
 * @author liuhangjun
 * @version 2018-07-23
 */
@Data
public class RecommendSentence extends DataEntity<RecommendSentence> {

	private static final long serialVersionUID = 1L;
	@Length(min=1, max=128, message="推荐语长度必须介于 1 和 128 之间")
	private String recommend;		// 推荐语


	public RecommendSentence() {
		super();
	}

	public RecommendSentence(Long id){
		super(id);
	}



}
