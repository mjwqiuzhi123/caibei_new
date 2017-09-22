package com.borrow.supermarket.response.result;

import java.math.BigDecimal;
import java.util.Date;

public class GetUserOrdersResultDTO
{
  private String identifier;
  private String cashbackInfo;
  private String lendName;
  private Date cashbackDate;
  private BigDecimal lendMoney;
  private long lendPerid;

  public String getIdentifier()
  {
    return this.identifier;
  }
  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }
  public String getCashbackInfo() {
    return this.cashbackInfo;
  }
  public void setCashbackInfo(String cashbackInfo) {
    this.cashbackInfo = cashbackInfo;
  }
  public String getLendName() {
    return this.lendName;
  }
  public void setLendName(String lendName) {
    this.lendName = lendName;
  }
  public Date getCashbackDate() {
    return this.cashbackDate;
  }
  public void setCashbackDate(Date cashbackDate) {
    this.cashbackDate = cashbackDate;
  }
  public BigDecimal getLendMoney() {
    return this.lendMoney;
  }
  public void setLendMoney(BigDecimal lendMoney) {
    this.lendMoney = lendMoney;
  }
  public long getLendPerid() {
    return this.lendPerid;
  }
  public void setLendPerid(long lendPerid) {
    this.lendPerid = lendPerid;
  }

  public GetUserOrdersResultDTO(String identifier, String cashbackInfo, String lendName, Date cashbackDate, BigDecimal lendMoney, long lendPerid)
  {
    this.identifier = identifier;
    this.cashbackInfo = cashbackInfo;
    this.lendName = lendName;
    this.cashbackDate = cashbackDate;
    this.lendMoney = lendMoney;
    this.lendPerid = lendPerid;
  }

  public GetUserOrdersResultDTO()
  {
  }
}