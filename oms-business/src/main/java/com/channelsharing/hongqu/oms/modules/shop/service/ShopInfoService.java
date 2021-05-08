/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.shop.service;

import java.util.List;

import com.google.common.base.Joiner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.shop.entity.ShopInfo;
import com.channelsharing.hongqu.oms.modules.shop.dao.ShopInfoDao;

/**
 * 店铺信息Service
 * @author Karl
 * @version 2018-08-27
 */
@Service
@Transactional(readOnly = true)
public class ShopInfoService extends CrudService<ShopInfoDao, ShopInfo> {

	public ShopInfo get(Long id) {
		return super.get(id);
	}

	public List<ShopInfo> findList(ShopInfo shopInfo) {
		return super.findList(shopInfo);
	}

	public Page<ShopInfo> findPage(Page<ShopInfo> page, ShopInfo shopInfo) {
		return super.findPage(page, shopInfo);
	}

	public List<ShopInfo> findByIds(String ids){
		String[] idsAry = ids.split("\\|");
		return super.dao.findByIds(Joiner.on(",").skipNulls().join(idsAry));
	}

	@Transactional(readOnly = false)
	public void save(ShopInfo shopInfo) {
		super.save(shopInfo);
	}

	@Transactional(readOnly = false)
	public void delete(ShopInfo shopInfo) {
		super.delete(shopInfo);
	}

}
