package com.borrow.supermarket.dao;

import com.borrow.supermarket.base.BaseMapper;
import com.borrow.supermarket.model.UserModel;

public abstract interface UserDao extends BaseMapper<UserModel>
{
  public abstract UserModel searchUserByNameAndPwd(UserModel paramUserModel);

  public abstract boolean saveUser(UserModel paramUserModel);

  public abstract UserModel searcUserByPhone(UserModel paramUserModel);
  //public abstract UserModel searcUserByPhone(String phone);

  public abstract Integer updateByEntity(UserModel paramUserModel);

  public abstract UserModel searchUserByUserIdentifier(String paramString);
}