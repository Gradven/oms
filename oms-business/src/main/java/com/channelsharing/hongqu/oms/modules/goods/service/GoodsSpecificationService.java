/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.goods.dao.GoodsSpecificationDao;

/**
 * 商品对应规格表值表Service
 * @author Karl
 * @version 2018-06-06
 */
@Service
@Transactional(readOnly = true)
public class GoodsSpecificationService extends CrudService<GoodsSpecificationDao, GoodsSpecification> {

	public GoodsSpecification get(Long id) {
		return super.get(id);
	}

	public List<GoodsSpecification> findList(GoodsSpecification goodsSpecification) {
		return super.findList(goodsSpecification);
	}
	public List<GoodsSpecification> findByIds(HashSet<String> sets) {
		GoodsSpecification query = new GoodsSpecification();
		StringBuilder specsBuilder = new StringBuilder();
		Iterator<String> it = sets.iterator();
		while (it.hasNext()){
			specsBuilder.append(it.next());
			specsBuilder.append(",");
		}
		String idsStr = specsBuilder.toString();
		if(idsStr.endsWith(",")){
			idsStr = idsStr.substring(0,idsStr.length()-1);
		}
		query.setSpecificationIds(idsStr);
		return super.dao.findByIds(query);
	}


	public Page<GoodsSpecification> findPage(Page<GoodsSpecification> page, GoodsSpecification goodsSpecification) {
		return super.findPage(page, goodsSpecification);
	}

	@Transactional(readOnly = false)
	public void save(GoodsSpecification goodsSpecification) {
		super.save(goodsSpecification);
	}

	@Transactional(readOnly = false)
	public void delete(GoodsSpecification goodsSpecification) {
		super.delete(goodsSpecification);
	}

}
