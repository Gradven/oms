/**
 * Copyright &copy; 2016-2022 <a href="http://www.xxxx.com">xxxx</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.modules.goods.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.Page;
import com.channelsharing.hongqu.oms.constant.Constant;
import com.channelsharing.hongqu.oms.modules.goods.dao.GoodsInfoDao;
import com.channelsharing.hongqu.oms.common.service.CrudService;
import com.channelsharing.hongqu.oms.modules.goods.entity.GoodsInfo;
import com.google.common.base.Joiner;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品信息Service
 *
 * @author Karl
 * @version 2018-06-07
 */
@Service
@Transactional(readOnly = true)
public class GoodsInfoService extends CrudService<GoodsInfoDao, GoodsInfo> {
    
    public static final String PORTAL_CACHE_PREFIX = Constant.PORTAL_CACHE_PREFIX;  // 清除门户的缓存使用

    public GoodsInfo get(Long id) {
        return super.get(id);
    }

    public List<GoodsInfo> findList(GoodsInfo goodsInfo) {
        return super.findList(goodsInfo);
    }

    public Page<GoodsInfo> findPage(Page<GoodsInfo> page, GoodsInfo goodsInfo) {
        return super.findPage(page, goodsInfo);
    }

    public List<GoodsInfo> findByIds(String ids){
        String[] idsAry = ids.split("\\|");
        return super.dao.findByIds(Joiner.on(",").skipNulls().join(idsAry));
    }

    @Transactional(readOnly = false)
    @CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "goodsInfo", key = "#root.target.PORTAL_CACHE_PREFIX + 'goodsInfo:id:' + #goodsInfo.id", condition = "#goodsInfo.id!=null")
    public void save(GoodsInfo goodsInfo) {
        super.save(goodsInfo);
    }
    
    @CacheEvict(cacheManager = "redisCacheManager", value = PORTAL_CACHE_PREFIX + "goodsInfo", key = "#root.target.PORTAL_CACHE_PREFIX + 'goodsInfo:id:' + #goodsInfo.id", condition = "#goodsInfo.id!=null")
    @Transactional(readOnly = false)
    public void delete(GoodsInfo goodsInfo) {
        super.delete(goodsInfo);
    }



}
