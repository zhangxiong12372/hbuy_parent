package com.java.web.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.service.BaseService;
import com.java.web.provider.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseService<T>{
	
	protected Map<String, Object> map = new HashMap<String, Object>();
	
	protected List<T> list = new ArrayList<T>();
	
	//操作成功的常量
	protected static final String SUCCESS = "success";
	//删除操作成功的常量
	protected static final String DELSUCCESS = "delSuccess";
	//修改操作成功的常量
	protected static final String UPDSUCCESS = "updSuccess";
	//添加操作成功的常量
	protected static final String SAVESUCCESS = "saveSuccess";
	//操作成功的常量
	protected static final String FAIL = "fail";
	
	@Autowired
	protected BaseMapper<T> baseMapper;
	
    //添加
	@Override
	public String save(T t) throws Exception {
		if(baseMapper.insert(t)>0){
			return SAVESUCCESS;
		}else{
			return FAIL;
		}
	}

	//修改
	@Override
	public String upd(T t) throws Exception {
		if(baseMapper.update(t)>0){
			return UPDSUCCESS;
		}else{
			return FAIL;
		}
	}

	//根据条件单个删除
	@Override
	public String remove(T t) throws Exception {
		if(baseMapper.delete(t)>0){
			return DELSUCCESS;
		}else{
			return FAIL;
		}
	}

	//批量删除
	@Override
	public String removeBatch(Integer[] ids) throws Exception {
		if(baseMapper.deleteBatch(ids)>0){
			return DELSUCCESS;
		}else{
			return FAIL;
		}
	}
	
	//根据条件查询单个结果
	@Override
	public T findObjectByPramas(T t) throws Exception {
		
		return baseMapper.queryObjectByPramas(t);
	}

	//根据其它条件查询多个结果
	@Override
	public List<T> findManyByOtherPramas(Object obj) throws Exception {
		
		return baseMapper.queryManyByOtherPramas(obj);
	}
	
	//根据条件（无条件）查询多个结果
	@Override
	public List<T> findManyByPramas(T t) throws Exception {
		
		return baseMapper.queryManyByPramas(t);
	}

	//根据条件(无条件)分页查询layui的table
	@Override
	public Map<String, Object> findListByPramas(Integer page, Integer limit, T t) throws Exception {
		PageHelper.startPage(page,limit);
		PageInfo<T> pageInfo = new PageInfo<T>(baseMapper.queryListByPramas(t));
		map.put("count",pageInfo.getTotal());
		map.put("data",pageInfo.getList());
		return map;
	}
	
	//获取表的数据记录条数
	@Override
	public Long getTotalByPramas(T t) throws Exception {
		
		return baseMapper.queryTotalByPramas(t);
	}

	//根据条件分页查询，一般分页
	@Override
	public PageInfo<T> findPageByPramas(Integer page, Integer limit, T t) throws Exception {
		PageHelper.startPage(page,limit);
		PageInfo<T> pageInfo = new PageInfo<T>(baseMapper.queryListByPramas(t));
		return new PageInfo<T>(baseMapper.queryListByPramas(t));
	}

	//获取数据页数
	@Override
	public Integer getTotalPageByPramas(Integer pageSize, T t) throws Exception {
		Long totalRecord = baseMapper.queryTotalByPramas(t);
		if(totalRecord%pageSize==0){
			return (int) (totalRecord/pageSize);
		}else{
			return (int) (totalRecord/pageSize + 1);
		}
		
	}

	//查询所有
	@Override
	public List<T> findAll() throws Exception {
		
		return baseMapper.queryAll();
	}

	
	
}
