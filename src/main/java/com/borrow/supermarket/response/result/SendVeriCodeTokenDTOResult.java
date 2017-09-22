package com.borrow.supermarket.response.result;

import com.borrow.supermarket.util.CommonMsg;

public class SendVeriCodeTokenDTOResult extends CommonMsg
{
  private int remainCount;

  public int getRemainCount()
  {
    return this.remainCount;
  }

  public void setRemainCount(int remainCount) {
    this.remainCount = remainCount;
  }

  public SendVeriCodeTokenDTOResult()
  {
  }

  public SendVeriCodeTokenDTOResult(int remainCount)
  {
    this.remainCount = remainCount;
  }
}