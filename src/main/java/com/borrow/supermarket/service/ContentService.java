package com.borrow.supermarket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borrow.supermarket.dao.ContentDao;
import com.borrow.supermarket.model.ContentModel;
import com.borrow.supermarket.mybatis.PageParameter;

@Service
public class ContentService {
	private static final Logger logger = LoggerFactory.getLogger(ContentService.class);

	@Autowired
	private ContentDao contentDaoI;

	// 添加首页信息
	public boolean saveContent(ContentModel model) {
		try {
			if (this.contentDaoI.hasContent(model).intValue() > 0)
				return this.contentDaoI.updateContent(model)
						.intValue() > 0;
			return this.contentDaoI.saveContent(model).intValue() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("第三方信用卡平台录入保存操作异常---原因是-----:" + e.getMessage());
		}
		return false;
	}

	// 获取首页信息
	public List<String> selectContentList(PageParameter parameter, ContentModel model) {
		try {
			Map map = new HashMap();
			map.put("page", parameter);
			return this.contentDaoI.getContent(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("第三方信用卡平台列表展示操作异常---原因是-----:" + e.getMessage());
		}
		return null;
	}
}