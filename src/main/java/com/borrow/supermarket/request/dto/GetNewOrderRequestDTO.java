package com.borrow.supermarket.request.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GetNewOrderRequestDTO
{
  private Date createDate;
  private String phone;
  private BigDecimal lendMoney;
  private long servicePersonTime;
  private BigDecimal totalLendMoney;

  public GetNewOrderRequestDTO(Date createDate, String phone, BigDecimal lendMoney, long servicePersonTime, BigDecimal totalLendMoney)
  {
    this.createDate = createDate;
    this.phone = phone;
    this.lendMoney = lendMoney;
    this.servicePersonTime = servicePersonTime;
    this.totalLendMoney = totalLendMoney;
  }

  public Date getCreateDate()
  {
    return this.createDate;
  }

  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public BigDecimal getLendMoney()
  {
    return this.lendMoney;
  }

  public void setLendMoney(BigDecimal lendMoney)
  {
    this.lendMoney = lendMoney;
  }

  public long getServicePersonTime()
  {
    return this.servicePersonTime;
  }

  public void setServicePersonTime(long servicePersonTime)
  {
    this.servicePersonTime = servicePersonTime;
  }

  public BigDecimal getTotalLendMoney()
  {
    return this.totalLendMoney;
  }

  public void setTotalLendMoney(BigDecimal totalLendMoney)
  {
    this.totalLendMoney = totalLendMoney;
  }

  public GetNewOrderRequestDTO()
  {
  }
}