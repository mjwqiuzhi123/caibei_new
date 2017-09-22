package com.borrow.supermarket.service;

import com.borrow.supermarket.dao.CreditDao;
import com.borrow.supermarket.model.CreditModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.request.dto.CreditPageRequestDTO;
import com.borrow.supermarket.response.result.CreditPageResultDTO;
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
public class CreditService
{
  private static final Logger logger = LoggerFactory.getLogger(CreditService.class);

  @Autowired
  private CreditDao creditDaoI;

  public boolean saveCredit(CreditModel creditModel) {
    try { creditModel.setIdentifier(CommonUtil.getUUID());
      return this.creditDaoI.save(creditModel).intValue() > 0;
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("第三方信用卡平台录入保存操作异常---原因是-----:" + e.getMessage());
    }return false;
  }

  public boolean updateCredit(CreditModel creditModel)
  {
    try {
      return this.creditDaoI.update(creditModel).intValue() > 0;
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("第三方信用卡平台修改操作异常---原因是-----:" + e.getMessage());
    }return false;
  }

  public CreditModel getCreditByIdentifier(CreditModel creditModel)
  {
    try {
      return (CreditModel)this.creditDaoI.getInfo(creditModel);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("第三方信用卡平台查看详情操作异常---原因是-----:" + e.getMessage());
    }return null;
  }

  public boolean updateCreditStatus(CreditModel creditModel)
  {
    try {
      return this.creditDaoI.updateCreditStatus(creditModel) > 0;
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("第三方信用卡平台修改上线状态操作异常---原因是-----:" + e.getMessage());
    }return false;
  }

  public List<CreditModel> selectCreditList(CreditModel creditModel, PageParameter parameter)
  {
    try {
      Map map = new HashMap();
      map.put("t", creditModel);
      map.put("page", parameter);
      return this.creditDaoI.getByPage(map);
    }
    catch (Exception e) {
      e.printStackTrace();
      logger.error("第三方信用卡平台列表展示操作异常---原因是-----:" + e.getMessage());
    }return null;
  }

  public PageWebDTOResult<CreditPageResultDTO> getSafeByPage(CreditPageRequestDTO creditPageRequestDTO)
  {
    PageParameter pageweb = new PageParameter(creditPageRequestDTO.getNumber().intValue());
    Map parameterMap = new HashMap();
    parameterMap.put("t", creditPageRequestDTO);
    parameterMap.put("page", pageweb);
    List lendList = this.creditDaoI.getCreditByPage(parameterMap);
    pageweb.setRecords(lendList);
    PageWebDTOResult pageWebDTOResult = new PageWebDTOResult(pageweb.getPageSize(), pageweb.getPageNow(), pageweb.getRowCount(), pageweb.getPageCount(), lendList);
    return pageWebDTOResult;
  }
}