package com.borrow.supermarket.base;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseDTO
{
  private String type;
  private String phone;

  public String getType()
  {
    return this.type;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setType(String type) {
    this.type = type;
  }
  public String toJson() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(this);
  }

  public BaseDTO(String type, String phone)
  {
    this.type = type;
    this.phone = phone;
  }

  public BaseDTO()
  {
  }
}