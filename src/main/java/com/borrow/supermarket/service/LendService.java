package com.borrow.supermarket.service;

import com.borrow.supermarket.dao.LendDao;
import com.borrow.supermarket.model.LendModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.request.dto.LendDetailRequestDTO;
import com.borrow.supermarket.request.dto.LendPageRequestDTO;
import com.borrow.supermarket.response.result.LendDetailDTOResult;
import com.borrow.supermarket.response.result.LendPageDTOResult;
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
public class LendService
{
	  private static final Logger logger = LoggerFactory.getLogger(LendService.class);

	  @Autowired
	  private LendDao lendDaoI;

	  public boolean saveLend(LendModel lendModel) { try { lendModel.setIdentifier(CommonUtil.getUUID());

	      return this.lendDaoI.save(new LendModel().getLendModel(lendModel)).intValue() > 0;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方借贷平台录入保存操作异常---原因是-----:" + e.getMessage());
	    }return false; }

	  public boolean updateLend(LendModel lendModel)
	  {
	    try
	    {
	      return this.lendDaoI.update(new LendModel().getLendModel(lendModel)).intValue() > 0;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方借贷平台修改操作异常---原因是-----:" + e.getMessage());
	    }return false;
	  }

	  public LendModel getLendByIdentifier(LendModel lendModel)
	  {
	    try {
	      return (LendModel)this.lendDaoI.getInfo(lendModel);
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方借贷平台查看详情操作异常---原因是-----:" + e.getMessage());
	    }return null;
	  }

	  public boolean updateLendStatus(LendModel lendModel)
	  {
	    try {
	      return this.lendDaoI.updateLendStatus(lendModel) > 0;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方借贷平台修改上线状态操作异常---原因是-----:" + e.getMessage());
	    }return false;
	  }

	  public List<LendModel> selectLendList(LendModel lendModel, PageParameter parameter)
	  {
	    try {
	      Map map = new HashMap();
	      map.put("t", lendModel);
	      map.put("page", parameter);
	      return this.lendDaoI.getByPage(map);
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方借贷平台列表展示操作异常---原因是-----:" + e.getMessage());
	    }return null;
	  }

	  public PageWebDTOResult<LendPageDTOResult> getLendByPage(LendPageRequestDTO lendPageRequestDTO)
	  {
	    PageParameter pageweb = new PageParameter(lendPageRequestDTO.getNumber().intValue());
	    Map parameterMap = new HashMap();
	    parameterMap.put("t", lendPageRequestDTO);
	    parameterMap.put("page", pageweb);
	    List lendList = this.lendDaoI.getLendByPage(parameterMap);
	    pageweb.setRecords(lendList);
	    PageWebDTOResult pageWebDTOResult = new PageWebDTOResult(pageweb.getPageSize(), pageweb.getPageNow(), pageweb.getRowCount(), pageweb.getPageCount(), lendList);
	    return pageWebDTOResult;
	  }

	  public synchronized void applyCount()
	  {
	    try {
	      this.lendDaoI.updateApplyCount();
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("更新请求次数失败---原因是-----:" + e.getMessage());
	    }
	  }
	  
	  public LendDetailDTOResult getLendDetailByIdentifier(LendDetailRequestDTO lendDetailRequestDTO)
	  {
	    try {
	      return this.lendDaoI.getLendDetailByIdentifier(lendDetailRequestDTO);
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("第三方借贷平台查看详情操作异常---原因是-----:" + e.getMessage());
	    }return null;
	  }

	  public boolean updatetotalApply(Integer id)
	  {
	    try {
	      return this.lendDaoI.updatetotalApply(id).intValue() > 0;
	    } catch (Exception e) {
	      e.printStackTrace();
	      logger.error("借贷平台总申请数修改操作异常---原因是-----:" + e.getMessage());
	    }return false;
	  }
}