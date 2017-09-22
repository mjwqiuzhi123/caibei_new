package com.borrow.supermarket.request.dto;

import com.borrow.supermarket.base.BaseDTO;

public class MsgAuthDTO extends BaseDTO
{
  private String code;
  private String minutes;

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMinutes() {
    return this.minutes;
  }

  public void setMinutes(String minutes) {
    this.minutes = minutes;
  }

  public MsgAuthDTO(String type, String phone, String code, String minutes) {
    super(type, phone);
    this.code = code;
    this.minutes = minutes;
  }

  public MsgAuthDTO()
  {
  }
}