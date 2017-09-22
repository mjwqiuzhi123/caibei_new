package com.borrow.supermarket.dao;

import com.borrow.supermarket.base.BaseMapper;
import com.borrow.supermarket.model.VericodesModel;

public abstract interface VericodesDao extends BaseMapper<VericodesModel>
{
  public abstract VericodesModel selectByEntity(VericodesModel paramVericodesModel);

  public abstract VericodesModel selectByEntity2(VericodesModel paramVericodesModel);

  public abstract int updateByEntity(VericodesModel paramVericodesModel);
}