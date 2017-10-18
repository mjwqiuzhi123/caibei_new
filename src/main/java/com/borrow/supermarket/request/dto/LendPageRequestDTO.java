package com.borrow.supermarket.request.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

public class LendPageRequestDTO {

	@NotNull
	private Integer number;
	private Integer lendMoney;
	private Integer lendPeriod;
	private Integer monthlyInterestRate;
	private Integer lendOnlineTime;
	private Integer creditStanding;
	private Integer hasCredit;
	private BigDecimal lendTotalMoney;
	private Integer lendTotalPerid;
	private Integer throughputRate;
	private Integer lendMoneySort;// 借款额度排序标记
	private Integer lendPeriodSort;// 借款期限排序标记

	/**
	 * @return the lendMoneySort
	 */
	public Integer getLendMoneySort() {
		return lendMoneySort;
	}

	/**
	 * @param lendMoneySort
	 *            the lendMoneySort to set
	 */
	public void setLendMoneySort(Integer lendMoneySort) {
		this.lendMoneySort = lendMoneySort;
	}

	/**
	 * @return the lendPeriodSort
	 */
	public Integer getLendPeriodSort() {
		return lendPeriodSort;
	}

	/**
	 * @param lendPeriodSort
	 *            the lendPeriodSort to set
	 */
	public void setLendPeriodSort(Integer lendPeriodSort) {
		this.lendPeriodSort = lendPeriodSort;
	}

	public Integer getNumber() {
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

	public Integer getLendPeriod() {
		return this.lendPeriod;
	}

	public void setLendPeriod(Integer lendPeriod) {
		this.lendPeriod = lendPeriod;
	}

	public Integer getMonthlyInterestRate() {
		return this.monthlyInterestRate;
	}

	public void setMonthlyInterestRate(Integer monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
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

	public LendPageRequestDTO(Integer number, Integer lendMoney, Integer lendPeriod, Integer monthlyInterestRate, Integer lendOnlineTime, Integer creditStanding, Integer hasCredit, BigDecimal lendTotalMoney, Integer lendTotalPerid, Integer throughputRate) {
		this.number = number;
		this.lendMoney = lendMoney;
		this.lendPeriod = lendPeriod;
		this.monthlyInterestRate = monthlyInterestRate;
		this.lendOnlineTime = lendOnlineTime;
		this.creditStanding = creditStanding;
		this.hasCredit = hasCredit;
		this.lendTotalMoney = lendTotalMoney;
		this.lendTotalPerid = lendTotalPerid;
		this.throughputRate = throughputRate;
	}

	public LendPageRequestDTO() {
	}
}