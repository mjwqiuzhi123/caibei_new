package com.borrow.supermarket.dao;

import com.borrow.supermarket.base.BaseMapper;
import com.borrow.supermarket.model.SafeModel;
import com.borrow.supermarket.response.result.SafePageResultDTO;
import java.util.List;
import java.util.Map;

public abstract interface SafeDao extends BaseMapper<SafeModel>
{
  public abstract int updateSafeStatus(SafeModel paramSafeModel);

  public abstract List<SafePageResultDTO> getSafeByPage(Map<String, Object> paramMap);
}