/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.channelsharing.hongqu.oms.common.service;

import java.util.List;

import com.channelsharing.hongqu.oms.common.persistence.TreeDao;
import com.channelsharing.hongqu.oms.common.persistence.TreeEntity;
import com.channelsharing.hongqu.oms.common.utils.Reflections;
import com.channelsharing.hongqu.oms.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class TreeService<D extends TreeDao<T>, T extends TreeEntity<T>> extends CrudService<D, T> {
	
	@Transactional(readOnly = false)
	public void save(T entity) {
		
		@SuppressWarnings("unchecked")
		Class<T> entityClass = Reflections.getClassGenricType(getClass(), 1);
		
		// 如果没有设置父节点，则代表为跟节点，有则获取父节点实体
		if (entity.getParent() == null || entity.getParentId() == null
				|| entity.getParentId().equals(0L)){
			entity.setParent(null);
		}else{
			entity.setParent(super.get(entity.getParentId()));
		}
		if (entity.getParent() == null){
			T parentEntity = null;
			try {
				parentEntity = entityClass.getConstructor(Long.class).newInstance(0L);
			} catch (Exception e) {
				throw new ServiceException(e);
			}
			entity.setParent(parentEntity);
			entity.getParent().setParentIds(StringUtils.EMPTY);
		}
		
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = entity.getParentIds();
		
		// 设置新的父节点串
		entity.setParentIds(entity.getParent().getParentIds()+entity.getParent().getId().toString()+",");
		
		// 保存或更新实体
		super.save(entity);
		
		// 更新子节点 parentIds
		T o = null;
		try {
			o = entityClass.newInstance();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		o.setParentIds("%,"+entity.getId()+",%");
		List<T> list = dao.findByParentIdsLike(o);
		for (T e : list){
			if (e.getParentIds() != null && oldParentIds != null){
				e.setParentIds(e.getParentIds().replace(oldParentIds, entity.getParentIds()));
				preUpdateChild(entity, e);
				dao.updateParentIds(e);
			}
		}
		
	}
	
	/**
	 * 预留接口，用户更新子节前调用
	 * @param childEntity
	 */
	protected void preUpdateChild(T entity, T childEntity) {
	
	}

}
