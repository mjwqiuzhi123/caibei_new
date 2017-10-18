package com.borrow.supermarket.service;

import com.borrow.supermarket.dao.SafeDao;
import com.borrow.supermarket.model.SafeModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.request.dto.SafePageRequestDTO;
import com.borrow.supermarket.response.result.SafePageResultDTO;
import com.borrow.supermarket.util.CommonUtil;
import com.borrow.supermarket.util.PageWebDTOResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SafeService
{
	  private static final Logger logger = LoggerFactory.getLogger(SafeService.class);

	  @Autowired
	  private SafeDao safeDaoI;

	  public boolean saveSafe(SafeModel safeModel) { try { safeModel.setIdentifier(CommonUtil.getUUID());
	      return this.safeDaoI.save(safeModel).intValue() > 0;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方保险平台录入保存操作异常---原因是-----:" + e.getMessage());
	    }return false; }

	  public boolean updateSafe(SafeModel safeModel)
	  {
	    try
	    {
	      return this.safeDaoI.update(safeModel).intValue() > 0;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方保险平台修改操作异常---原因是-----:" + e.getMessage());
	    }return false;
	  }

	  public SafeModel getSafeByIdentifier(SafeModel safeModel)
	  {
	    try {
	      return (SafeModel)this.safeDaoI.getInfo(safeModel);
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方保险平台查看详情操作异常---原因是-----:" + e.getMessage());
	    }return null;
	  }

	  public boolean updateSafeStatus(SafeModel safeModel)
	  {
	    try {
	      return this.safeDaoI.updateSafeStatus(safeModel) > 0;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方保险平台修改上线状态操作异常---原因是-----:" + e.getMessage());
	    }return false;
	  }

	  public List<SafeModel> selectSafeList(SafeModel safeModel, PageParameter parameter)
	  {
	    try {
	      Map map = new HashMap();
	      map.put("t", safeModel);
	      map.put("page", parameter);
	      return this.safeDaoI.getByPage(map);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方保险平台列表展示操作异常---原因是-----:" + e.getMessage());
	    }return null;
	  }

	  public PageWebDTOResult<SafePageResultDTO> getSafeByPage(SafePageRequestDTO safePageRequestDTO)
	  {
	    PageParameter pageweb = new PageParameter(safePageRequestDTO.getNumber().intValue());
	    Map parameterMap = new HashMap();
	    parameterMap.put("t", safePageRequestDTO);
	    parameterMap.put("page", pageweb);
	    List lendList = this.safeDaoI.getSafeByPage(parameterMap);
	    pageweb.setRecords(lendList);
	    PageWebDTOResult pageWebDTOResult = new PageWebDTOResult(pageweb.getPageSize(), pageweb.getPageNow(), pageweb.getRowCount(), pageweb.getPageCount(), lendList);
	    return pageWebDTOResult;
	  }
}