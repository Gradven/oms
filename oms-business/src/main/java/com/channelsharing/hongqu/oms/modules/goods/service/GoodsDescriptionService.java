/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsDescription;
import com.channelsharing.hongqu.oms.modules.goods.dao.GoodsDescriptionDao;

/**
 * 商品介绍详情Service
 * @author Karl
 * @version 2018-06-08
 */
@Service
@Transactional(readOnly = true)
public class GoodsDescriptionService extends CrudService<GoodsDescriptionDao, GoodsDescription> {

	public GoodsDescription get(Long id) {
		return super.get(id);
	}

	public List<GoodsDescription> findList(GoodsDescription goodsDescription) {
		return super.findList(goodsDescription);
	}

	public Page<GoodsDescription> findPage(Page<GoodsDescription> page, GoodsDescription goodsDescription) {
		return super.findPage(page, goodsDescription);
	}

	@Transactional(readOnly = false)
	public void save(GoodsDescription goodsDescription) {
		String contentParsed = StringEscapeUtils.unescapeHtml4(goodsDescription.getContent());
		goodsDescription.setContent(contentParsed);
		super.save(goodsDescription);
	}

	@Transactional(readOnly = false)
	public void delete(GoodsDescription goodsDescription) {
		super.delete(goodsDescription);
	}

}
