package com.borrow.supermarket.dao;

import com.borrow.supermarket.base.BaseMapper;
import com.borrow.supermarket.model.LendModel;
import com.borrow.supermarket.request.dto.LendDetailRequestDTO;
import com.borrow.supermarket.response.result.LendDetailDTOResult;
import com.borrow.supermarket.response.result.LendPageDTOResult;
import java.util.List;
import java.util.Map;

public abstract interface LendDao extends BaseMapper<LendModel>
{
  public abstract int updateLendStatus(LendModel paramLendModel);

  public abstract List<LendPageDTOResult> getLendByPage(Map<String, Object> paramMap);

  public abstract LendDetailDTOResult getLendDetailByIdentifier(LendDetailRequestDTO paramLendDetailRequestDTO);

  public abstract Integer updatetotalApply(Integer paramInteger);

  public abstract int updateApplyCount();
}