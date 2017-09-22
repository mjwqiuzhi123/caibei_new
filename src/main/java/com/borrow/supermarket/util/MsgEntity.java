package com.borrow.supermarket.util;

public class MsgEntity extends CommonMsg
{
  private static final long serialVersionUID = 1L;
  private String name;
  private String password;
  private String mobs;
  private String msg;

  public String getName()
  {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPassword() {
    return this.password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getMobs() {
    return this.mobs;
  }
  public void setMobs(String mobs) {
    this.mobs = mobs;
  }
  public String getMsg() {
    return this.msg;
  }
  public void setMsg(String msg) {
    this.msg = msg;
  }

  public MsgEntity(String mobs, String msg) {
    this.name = "yj";
    this.password = "berekyj!";
    this.mobs = mobs;
    this.msg = msg;
  }

  public MsgEntity()
  {
  }
}