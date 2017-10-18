package com.borrow.supermarket.request.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

public class GetsaveOrderRequestDTO
{
	  @NotNull(message="产品标识不能为空")
	  private String identifier;

	  @NotNull(message="借款金额不能为空")
	  private BigDecimal lendTotalMoney;

	  @NotNull(message="借款周期不能为空")
	  private Integer lendTotalPerid;

	  @NotNull(message="产品类型不能为空")
	  private Integer type;

	  public String getIdentifier()
	  {
	    return this.identifier;
	  }

	  public void setIdentifier(String identifier) {
	    this.identifier = identifier;
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

	  public Integer getType() {
	    return this.type;
	  }

	  public void setType(Integer type) {
	    this.type = type;
	  }

	  public GetsaveOrderRequestDTO(String identifier, BigDecimal lendTotalMoney, Integer lendTotalPerid, Integer type)
	  {
	    this.identifier = identifier;
	    this.lendTotalMoney = lendTotalMoney;
	    this.lendTotalPerid = lendTotalPerid;
	    this.type = type;
	  }

	  public GetsaveOrderRequestDTO()
	  {
	  }
}