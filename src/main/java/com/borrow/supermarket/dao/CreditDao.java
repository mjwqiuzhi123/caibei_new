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
}