/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.config.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import com.channelsharing.hongqu.oms.common.persistence.DataEntity;

/**
 * 系统配置项管理Entity
 * @author Karl
 * @version 2018-06-12
 */
@Data
public class ConfigParam extends DataEntity<ConfigParam> {

	private static final long serialVersionUID = 1L;
	@Length(min=0, max=64, message="配置项的key长度必须介于 0 和 64 之间")
	private String configKey;		// 配置项的key

	@Length(min=0, max=2000, message="配置项的value长度必须介于 0 和 2000 之间")
	private String configValue;		// 配置项的value

	@Length(min=0, max=4, message="系统模块 参考数据字典（sys_modules）长度必须介于 0 和 4 之间")
	private String moduleId;		// 系统模块 参考数据字典（sys_modules）

	@Length(min=0, max=200, message="描述长度必须介于 0 和 200 之间")
	private String description;		// 描述

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;		// 创建时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;		// update_time


	public ConfigParam() {
		super();
	}

	public ConfigParam(Long id){
		super(id);
	}



}
