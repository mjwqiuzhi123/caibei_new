package com.borrow.supermarket.request.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

public class LendPageRequestDTO
{

  @NotNull
  private Integer number;
  private Integer lendMoney;
  private Integer lendPerid;
  private Integer monthlyInterestRate;
  private Integer onlineTime;
  private Integer creditStanding;
  private Integer hasCredit;
  private BigDecimal lendTotalMoney;
  private Integer lendTotalPerid;
  private Integer throughputRate;

  public Integer getNumber()
  {
    return this.number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Integer getLendMoney() {
    return this.lendMoney;
  }

  public void setLendMoney(Integer lendMoney) {
    this.lendMoney = lendMoney;
  }

  public Integer getLendPerid() {
    return this.lendPerid;
  }

  public void setLendPerid(Integer lendPerid) {
    this.lendPerid = lendPerid;
  }

  public Integer getMonthlyInterestRate() {
    return this.monthlyInterestRate;
  }

  public void setMonthlyInterestRate(Integer monthlyInterestRate) {
    this.monthlyInterestRate = monthlyInterestRate;
  }

  public Integer getOnlineTime() {
    return this.onlineTime;
  }

  public void setOnlineTime(Integer onlineTime) {
    this.onlineTime = onlineTime;
  }

  public Integer getCreditStanding() {
    return this.creditStanding;
  }

  public void setCreditStanding(Integer creditStanding) {
    this.creditStanding = creditStanding;
  }

  public Integer getHasCredit() {
    return this.hasCredit;
  }

  public void setHasCredit(Integer hasCredit) {
    this.hasCredit = hasCredit;
  }

  public BigDecimal getLendTotalMoney() {
    return this.lendTotalMoney;
  }

  public void setLendTotalMoney(BigDecimal lendTotalMoney) {
    this.lendTotalMoney = lendTotalMoney;
  }

  public Integer getLendTotalPerid() {
    return this.lendTotalPerid;
  }

  public void setLendTotalPerid(Integer lendTotalPerid) {
    this.lendTotalPerid = lendTotalPerid;
  }

  public Integer getThroughputRate() {
    return this.throughputRate;
  }

  public void setThroughputRate(Integer throughputRate) {
    this.throughputRate = throughputRate;
  }

  public LendPageRequestDTO(Integer number, Integer lendMoney, Integer lendPerid, Integer monthlyInterestRate, Integer onlineTime, Integer creditStanding, Integer hasCredit, BigDecimal lendTotalMoney, Integer lendTotalPerid, Integer throughputRate)
  {
    this.number = number;
    this.lendMoney = lendMoney;
    this.lendPerid = lendPerid;
    this.monthlyInterestRate = monthlyInterestRate;
    this.onlineTime = onlineTime;
    this.creditStanding = creditStanding;
    this.hasCredit = hasCredit;
    this.lendTotalMoney = lendTotalMoney;
    this.lendTotalPerid = lendTotalPerid;
    this.throughputRate = throughputRate;
  }

  public LendPageRequestDTO()
  {
  }
}