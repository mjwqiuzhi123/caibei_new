package com.borrow.supermarket.dao;

import com.borrow.supermarket.base.BaseMapper;
import com.borrow.supermarket.model.CreditModel;
import com.borrow.supermarket.response.result.CreditPageResultDTO;

import java.util.List;
import java.util.Map;

public abstract interface CreditDao extends BaseMapper<CreditModel>
{
  public abstract int updateCreditStatus(CreditModel paramCreditModel);

  public abstract List<CreditPageResultDTO> getCreditByPage(Map<String, Object> paramMap);
  
  public abstract Integer saveHomeMessage(String param);// add by mjw 添加首页信息
  
  public abstract List<String> getHomeMessageByPage(Map<String, Object> paramMap);// add by mjw 获取全部首页信息
}