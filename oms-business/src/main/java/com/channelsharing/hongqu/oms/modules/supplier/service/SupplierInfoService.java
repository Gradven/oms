/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.supplier.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.supplier.entity.SupplierInfo;
import com.channelsharing.hongqu.oms.modules.supplier.dao.SupplierInfoDao;

/**
 * 供应商信息Service
 * @author Karl
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class SupplierInfoService extends CrudService<SupplierInfoDao, SupplierInfo> {

	public SupplierInfo get(Long id) {
		return super.get(id);
	}

	public List<SupplierInfo> findList(SupplierInfo supplierInfo) {
		return super.findList(supplierInfo);
	}

	public Page<SupplierInfo> findPage(Page<SupplierInfo> page, SupplierInfo supplierInfo) {
		return super.findPage(page, supplierInfo);
	}

	@Transactional(readOnly = false)
	public void save(SupplierInfo supplierInfo) {
		super.save(supplierInfo);
	}

	@Transactional(readOnly = false)
	public void delete(SupplierInfo supplierInfo) {
		super.delete(supplierInfo);
	}

}
