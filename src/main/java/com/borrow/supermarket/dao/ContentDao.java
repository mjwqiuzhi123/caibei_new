package com.borrow.supermarket.dao;

import java.util.List;

import com.borrow.supermarket.base.BaseMapper;
import com.borrow.supermarket.model.ContentModel;

public abstract interface ContentDao extends BaseMapper<ContentModel>
{ 
  public abstract Integer saveContent(ContentModel model);// add by mjw 添加首页信息
  
  public abstract Integer updateContent(ContentModel model);// add by mjw 更新首页信息
  
  public abstract Integer hasContent(ContentModel model);// add by mjw 获取首页信息数量
  
  public abstract List<String> getContent(ContentModel model);// add by mjw 获取全部首页信息
}