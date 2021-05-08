/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.resource.entity;

import com.channelsharing.hongqu.oms.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 图片资源管理Entity
 * @author Karl
 * @version 2018-06-08
 */
@Data
public class ResInfo extends DataEntity<ResInfo> {

	private static final long serialVersionUID = 1L;
	@Length(min=0, max=255, message="资源地址长度必须介于 0 和 255 之间")
	private String uri;		// 资源地址

	@Length(min=0, max=100, message="资源key长度必须介于 0 和 100 之间")
	private String resKey;		// 资源key

	@NotNull(message="文件类型不能为空")
	private Integer type;		// 文件类型

	@Length(min=1, max=50, message="文件标题长度必须介于 1 和 50 之间")
	private String title;		// 文件标题

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;		// 创建时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;		// 更新时间


	public ResInfo() {
		super();
	}

	public ResInfo(Long id){
		super(id);
	}



}
