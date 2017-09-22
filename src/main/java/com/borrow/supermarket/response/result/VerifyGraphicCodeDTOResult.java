package com.borrow.supermarket.response.result;

import com.borrow.supermarket.util.CommonMsg;

public class VerifyGraphicCodeDTOResult extends CommonMsg
{
  private static final long serialVersionUID = 1L;
  public int remainCount;
  public String token;

  public int getRemainCount()
  {
    return this.remainCount;
  }

  public void setRemainCount(int remainCount) {
    this.remainCount = remainCount;
  }

  public String getToken() {
    return this.token;
  }

  public VerifyGraphicCodeDTOResult(int remainCount)
  {
    this.remainCount = remainCount;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public VerifyGraphicCodeDTOResult(int remainCount, String token)
  {
    this.remainCount = remainCount;
    this.token = token;
  }

  public VerifyGraphicCodeDTOResult()
  {
  }
}