/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.product.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.product.entity.ProductInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.product.dao.ProductInfoDao;

/**
 * 产品信息Service
 * @author Karl
 * @version 2018-06-11
 */
@Service
@Transactional(readOnly = true)
public class ProductInfoService extends CrudService<ProductInfoDao, ProductInfo> {

	public ProductInfo get(Long id) {
		return super.get(id);
	}

	public List<ProductInfo> findList(ProductInfo productInfo) {
		return super.findList(productInfo);
	}

	public Page<ProductInfo> findPage(Page<ProductInfo> page, ProductInfo productInfo) {
		return super.findPage(page, productInfo);
	}

	public ProductInfo goodsStatistics(long goodsId){
		return super.dao.goodsStatistics(goodsId);
	}
	@Transactional(readOnly = false)
	public void save(ProductInfo productInfo) {
		super.save(productInfo);
	}

	@Transactional(readOnly = false)
	public void delete(ProductInfo productInfo) {
		super.delete(productInfo);
	}

}
