/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.common.persistence;

import java.util.Date;

import com.channelsharing.hongqu.oms.modules.sys.utils.UserUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.channelsharing.hongqu.oms.modules.sys.entity.User;

/**
 * 数据Entity类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Data
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	protected String remark;	// 备注
	protected User createBy;	// 创建者
	protected User updateBy;	// 更新者
	protected Date createTime;	// 创建日期
	protected Date updateTime;	// 更新日期
	protected String delFlag; 	// 删除标记（0：正常；1：删除；）
	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntity(Long id) {
		super(id);
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			//从uuid方式改为null的原因是，mysql的插入方式：如果id为null，那么数据使用id自增长的方式插入数据库
			//modified by liuhangjun 2017-01-05
			//setId(IdGen.uuid());
			setId(null);
		}
		User user = UserUtils.getUser();
		if (user.getId() != null){
			this.updateBy = user;
			this.createBy = user;
		}
		this.updateTime = new Date();
		this.createTime = this.updateTime;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		User user = UserUtils.getUser();
		if (user.getId() != null){
			this.updateBy = user;
		}
		this.updateTime = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@JsonIgnore
	@JSONField(serialize = false)
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JsonIgnore
	@JSONField(serialize = false)
	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@JsonIgnore
	@Length(min=1, max=1)
	@JSONField(serialize = false)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
