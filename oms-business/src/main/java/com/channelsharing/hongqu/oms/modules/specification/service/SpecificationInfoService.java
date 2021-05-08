/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.specification.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.specification.entity.SpecificationInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.specification.dao.SpecificationInfoDao;

/**
 * 规格维度信息Service
 * @author Karl
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class SpecificationInfoService extends CrudService<SpecificationInfoDao, SpecificationInfo> {

	public SpecificationInfo get(Long id) {
		return super.get(id);
	}

	public List<SpecificationInfo> findList(SpecificationInfo specificationInfo) {
		return super.findList(specificationInfo);
	}

	public Page<SpecificationInfo> findPage(Page<SpecificationInfo> page, SpecificationInfo specificationInfo) {
		return super.findPage(page, specificationInfo);
	}

	@Transactional(readOnly = false)
	public void save(SpecificationInfo specificationInfo) {
		super.save(specificationInfo);
	}

	@Transactional(readOnly = false)
	public void delete(SpecificationInfo specificationInfo) {
		super.delete(specificationInfo);
	}

}
