/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.service;

import com.channelsharing.hongqu.oms.common.service.TreeService;
import com.channelsharing.hongqu.oms.constant.Constant;
import com.channelsharing.hongqu.oms.modules.goods.dao.GoodsCategoryDao;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsCategory;
import com.google.common.base.Joiner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分类Service
 *
 * @author Karl
 * @version 2018-06-05
 */
@Service
@Transactional(readOnly = true)
public class GoodsCategoryService extends TreeService<GoodsCategoryDao, GoodsCategory> {
	public static final String PORTAL_CACHE_PREFIX = Constant.PORTAL_CACHE_PREFIX; // 清除门户的缓存使用

	public GoodsCategory get(Long id) {
		return super.get(id);
	}

	public List<GoodsCategory> findList(GoodsCategory goodsCategory) {
		return super.findList(goodsCategory);
	}

	public List<GoodsCategory> findByIds(String ids){
		String[] idsAry = ids.split("\\|");
		return super.dao.findByIds(Joiner.on(",").skipNulls().join(idsAry));
	}

	@Transactional(readOnly = false)
	@Caching(evict = {
			@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX
					+ "allGoodsCategories", key = "#root.target.PORTAL_CACHE_PREFIX + 'allGoodsCategories'"),
			@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX
					+ "goodsCategory", key = "#root.target.PORTAL_CACHE_PREFIX + 'goodsCategory:' + #goodsCategory.id", condition = "#goodsCategory.id!=null") })
	public void save(GoodsCategory goodsCategory) {
		super.save(goodsCategory);
	}

	@Transactional(readOnly = false)
	@Caching(evict = {
			@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX
					+ "allGoodsCategories", key = "#root.target.PORTAL_CACHE_PREFIX + 'allGoodsCategories'"),
			@CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX
					+ "goodsCategory", key = "#root.target.PORTAL_CACHE_PREFIX + 'goodsCategory:' + #goodsCategory.id", condition = "#goodsCategory.id!=null") })
	public void delete(GoodsCategory goodsCategory) {
		super.delete(goodsCategory);
	}
}
