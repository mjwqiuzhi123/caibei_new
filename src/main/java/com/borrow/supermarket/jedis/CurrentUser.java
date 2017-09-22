package com.borrow.supermarket.jedis;

import com.borrow.supermarket.model.UserModel;
import com.borrow.supermarket.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser
{

  @Resource
  JedisUtil jedisUtil;

  @Autowired
  private UserService usersServiceI;
  private String useridentifier;
  private String phone;

  public String getUseridentifier()
  {
    return this.useridentifier;
  }

  public void setUseridentifier(String useridentifier) {
    this.useridentifier = useridentifier;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public CurrentUser(UserModel user) {
    this.phone = user.getPhone();
    this.useridentifier = user.getUseridentifier();
  }

  public CurrentUser()
  {
  }

  public void initUser(UserModel user)
  {
    CurrentUser currentUser = new CurrentUser(user);
    String sign = getSign(user.getUseridentifier());
    ObjectMapper mapper = new ObjectMapper();
    Map hash = (Map)mapper.convertValue(currentUser, Map.class);
    this.jedisUtil.initBaseData(sign, hash);
  }

  public CurrentUser getUser(String userIdentifier)
  {
    try {
      String sign = getSign(userIdentifier);
      Map hash = this.jedisUtil.getObjectHash(sign);
      if ((hash == null) || (hash.isEmpty())) {
        initUserFromDatabase(userIdentifier);
        hash = this.jedisUtil.getObjectHash(sign);
      }
      ObjectMapper mapper = new ObjectMapper();
      return (CurrentUser)mapper.convertValue(hash, CurrentUser.class);
    }
    catch (Exception e) {
      e.printStackTrace();
    }return null;
  }

  public void initUserFromDatabase(String userIdentifier)
  {
    UserModel user = this.usersServiceI.searchUserByUserIdentifier(userIdentifier);
    initUser(user);
  }

  private String getSign(String userIdentifier) {
    return "user:" + userIdentifier;
  }
}