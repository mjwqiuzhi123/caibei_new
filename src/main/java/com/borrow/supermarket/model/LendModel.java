package com.borrow.supermarket.model;

import com.borrow.supermarket.request.dto.GetsaveOrderRequestDTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LendModel
  implements Serializable
{
  private Integer id;
  private String identifier;
  private String lendName;
  private String lendPicUrl;
  private String lendUrl;
  private Integer platformNature;
  private Integer lendOnlineTime;
  private Integer creditStanding;
  private Integer hasCredit;
  private Integer lendMoney;
  private Integer lendPeriod;
  private String lendSpecial;
  private String monthlyInterestRate;
  private String loanTime;
  private String throughputRate;
  private Integer returnCycle;
  private String requirements;
  private String certificationMaterials;
  private String applyForAdvice;
  private String overduePunishment;
  private Integer status;
  private Date createDate;
  private Date updateDate;
  private int hasActivity;
  private long totalApply;
  private BigDecimal lendMoneyBegin;
  private BigDecimal lendMoneyEnd;
  private int lendSpecialBegin;
  private int lendSpecialEnd;
  private int applyCount;

	public int getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(int applyCount) {
		this.applyCount = applyCount;
	}

	public BigDecimal getLendMoneyBegin() {
		return this.lendMoneyBegin;
	}
	
  public void setLendMoneyBegin(BigDecimal lendMoneyBegin) {
    this.lendMoneyBegin = lendMoneyBegin;
  }

  public BigDecimal getLendMoneyEnd() {
    return this.lendMoneyEnd;
  }

  public void setLendMoneyEnd(BigDecimal lendMoneyEnd) {
    this.lendMoneyEnd = lendMoneyEnd;
  }

  public int getLendSpecialBegin() {
    return this.lendSpecialBegin;
  }

  public void setLendSpecialBegin(int lendSpecialBegin) {
    this.lendSpecialBegin = lendSpecialBegin;
  }

  public int getLendSpecialEnd() {
    return this.lendSpecialEnd;
  }

  public void setLendSpecialEnd(int lendSpecialEnd) {
    this.lendSpecialEnd = lendSpecialEnd;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIdentifier() {
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

  public String getLendUrl() {
    return this.lendUrl;
  }

  public void setLendUrl(String lendUrl) {
    this.lendUrl = lendUrl;
  }

  public Integer getPlatformNature() {
    return this.platformNature;
  }

  public void setPlatformNature(Integer platformNature) {
    this.platformNature = platformNature;
  }

  public Integer getLendOnlineTime() {
    return this.lendOnlineTime;
  }

  public void setLendOnlineTime(Integer lendOnlineTime) {
    this.lendOnlineTime = lendOnlineTime;
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

  public Integer getLendMoney() {
    return this.lendMoney;
  }

  public void setLendMoney(Integer lendMoney) {
    this.lendMoney = lendMoney;
  }

  public Integer getLendPeriod() {
    return this.lendPeriod;
  }

  public void setLendPeriod(Integer lendPeriod) {
    this.lendPeriod = lendPeriod;
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

  public String getLoanTime() {
    return this.loanTime;
  }

  public void setLoanTime(String loanTime) {
    this.loanTime = loanTime;
  }

  public String getThroughputRate() {
    return this.throughputRate;
  }

  public void setThroughputRate(String throughputRate) {
    this.throughputRate = throughputRate;
  }

  public Integer getReturnCycle() {
    return this.returnCycle;
  }

  public void setReturnCycle(Integer returnCycle) {
    this.returnCycle = returnCycle;
  }

  public String getRequirements() {
    return this.requirements;
  }

  public void setRequirements(String requirements) {
    this.requirements = requirements;
  }

  public String getCertificationMaterials() {
    return this.certificationMaterials;
  }

  public void setCertificationMaterials(String certificationMaterials) {
    this.certificationMaterials = certificationMaterials;
  }

  public String getApplyForAdvice() {
    return this.applyForAdvice;
  }

  public void setApplyForAdvice(String applyForAdvice) {
    this.applyForAdvice = applyForAdvice;
  }

  public String getOverduePunishment() {
    return this.overduePunishment;
  }

  public void setOverduePunishment(String overduePunishment) {
    this.overduePunishment = overduePunishment;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateDate()
  {
    return this.createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return this.updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
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

  public LendModel()
  {
  }

  public LendModel(Integer id, String identifier, String lendName, String lendPicUrl, String lendUrl, Integer platformNature, Integer lendOnlineTime, Integer creditStanding, Integer hasCredit, Integer lendMoney, Integer lendPeriod, String lendSpecial, String monthlyInterestRate, String loanTime, String throughputRate, Integer returnCycle, String requirements, String certificationMaterials, String applyForAdvice, String overduePunishment, Integer status, Date createDate, Date updateDate, int hasActivity, long totalApply)
  {
    this.id = id;
    this.identifier = identifier;
    this.lendName = lendName;
    this.lendPicUrl = lendPicUrl;
    this.lendUrl = lendUrl;
    this.platformNature = platformNature;
    this.lendOnlineTime = lendOnlineTime;
    this.creditStanding = creditStanding;
    this.hasCredit = hasCredit;
    this.lendMoney = lendMoney;
    this.lendPeriod = lendPeriod;
    this.lendSpecial = lendSpecial;
    this.monthlyInterestRate = monthlyInterestRate;
    this.loanTime = loanTime;
    this.throughputRate = throughputRate;
    this.returnCycle = returnCycle;
    this.requirements = requirements;
    this.certificationMaterials = certificationMaterials;
    this.applyForAdvice = applyForAdvice;
    this.overduePunishment = overduePunishment;
    this.status = status;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.hasActivity = hasActivity;
    this.totalApply = totalApply;
  }

  public LendModel(GetsaveOrderRequestDTO getsaveOrderRequestDTO) {
    this.identifier = getsaveOrderRequestDTO.getIdentifier();
  }

  public LendModel getLendModel(LendModel lendModel)
  {
    int lendMoney = lendModel.getLendMoney().intValue();
    switch (lendMoney) {
    case 1:
      lendModel.setLendMoneyBegin(new BigDecimal(500));
      lendModel.setLendMoneyEnd(new BigDecimal(1000));
      break;
    case 2:
      lendModel.setLendMoneyBegin(new BigDecimal(1000));
      lendModel.setLendMoneyEnd(new BigDecimal(2000));
      break;
    case 3:
      lendModel.setLendMoneyBegin(new BigDecimal(2000));
      lendModel.setLendMoneyEnd(new BigDecimal(3000));
      break;
    case 4:
      lendModel.setLendMoneyBegin(new BigDecimal(3000));
      lendModel.setLendMoneyEnd(new BigDecimal(5000));
      break;
    case 5:
      lendModel.setLendMoneyBegin(new BigDecimal(5000));
      lendModel.setLendMoneyEnd(new BigDecimal(8000));
      break;
    case 6:
      lendModel.setLendMoneyBegin(new BigDecimal(8000));
      lendModel.setLendMoneyEnd(new BigDecimal(10000));
      break;
    default:
      lendModel.setLendMoneyBegin(new BigDecimal(10000));
      lendModel.setLendMoneyEnd(new BigDecimal(100000000));
    }

    int lendPeriod = lendModel.getLendPeriod().intValue();

    switch (lendPeriod) {
    case 1:
      lendModel.setLendSpecialBegin(7);
      lendModel.setLendSpecialEnd(14);
      break;
    case 2:
      lendModel.setLendSpecialBegin(14);
      lendModel.setLendSpecialEnd(21);
      break;
    case 3:
      lendModel.setLendSpecialBegin(21);
      lendModel.setLendSpecialEnd(28);
      break;
    case 4:
      lendModel.setLendSpecialBegin(28);
      lendModel.setLendSpecialEnd(30);
      break;
    default:
      lendModel.setLendSpecialBegin(30);
      lendModel.setLendSpecialEnd(300000);
    }

    return lendModel;
  }
}