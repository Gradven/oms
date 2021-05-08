/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.sensitive.entity;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 敏感词管理Entity
 * @author Karl
 * @version 2018-06-12
 */
@Data
public class SensitiveWord extends DataEntity<SensitiveWord> {

	private static final long serialVersionUID = 1L;
	@Length(min=1, max=255, message="敏感词长度必须介于 1 和 255 之间")
	private String word;		// 敏感词


	public SensitiveWord() {
		super();
	}

	public SensitiveWord(Long id){
		super(id);
	}



}
