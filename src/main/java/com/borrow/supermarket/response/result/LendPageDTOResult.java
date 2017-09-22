package com.borrow.supermarket.response.result;

public class LendPageDTOResult
{
  private String identifier;
  private String lendName;
  private String lendPicUrl;
  private int platformNature;
  private int hasActivity;
  private long totalApply;
  private String lendSpecial;
  private String monthlyInterestRate;
  private Integer lendMoney;

  public String getIdentifier()
  {
    return this.identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getLendName() {
    return this.lendName;
  }

  public void setLendName(String lendName) {
    this.lendName = lendName;
  }

  public String getLendPicUrl() {
    return this.lendPicUrl;
  }

  public void setLendPicUrl(String lendPicUrl) {
    this.lendPicUrl = lendPicUrl;
  }

  public int getPlatformNature() {
    return this.platformNature;
  }

  public void setPlatformNature(int platformNature) {
    this.platformNature = platformNature;
  }

  public int getHasActivity() {
    return this.hasActivity;
  }

  public void setHasActivity(int hasActivity) {
    this.hasActivity = hasActivity;
  }

  public long getTotalApply() {
    return this.totalApply;
  }

  public void setTotalApply(long totalApply) {
    this.totalApply = totalApply;
  }

  public String getLendSpecial() {
    return this.lendSpecial;
  }

  public void setLendSpecial(String lendSpecial) {
    this.lendSpecial = lendSpecial;
  }

  public String getMonthlyInterestRate() {
    return this.monthlyInterestRate;
  }

  public void setMonthlyInterestRate(String monthlyInterestRate) {
    this.monthlyInterestRate = monthlyInterestRate;
  }

  public Integer getLendMoney() {
    return this.lendMoney;
  }

  public void setLendMoney(Integer lendMoney) {
    this.lendMoney = lendMoney;
  }
}