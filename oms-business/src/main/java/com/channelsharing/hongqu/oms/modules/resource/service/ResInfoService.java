/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.resource.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.resource.entity.ResInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.resource.dao.ResInfoDao;

/**
 * 图片资源管理Service
 * @author Karl
 * @version 2018-06-08
 */
@Service
@Transactional(readOnly = true)
public class ResInfoService extends CrudService<ResInfoDao, ResInfo> {

	public ResInfo get(Long id) {
		return super.get(id);
	}

	public List<ResInfo> findList(ResInfo resInfo) {
		return super.findList(resInfo);
	}

	public Page<ResInfo> findPage(Page<ResInfo> page, ResInfo resInfo) {
		return super.findPage(page, resInfo);
	}

	@Transactional(readOnly = false)
	public void save(ResInfo resInfo) {
		super.save(resInfo);
	}

	@Transactional(readOnly = false)
	public void delete(ResInfo resInfo) {
		super.delete(resInfo);
	}

}
