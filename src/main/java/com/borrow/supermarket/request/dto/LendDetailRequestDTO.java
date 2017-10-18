package com.borrow.supermarket.request.dto;

import javax.validation.constraints.NotNull;

public class LendDetailRequestDTO
{

	  @NotNull(message="借款产品编号不能为空")
	  private String identifier;

	  public String getIdentifier()
	  {
	    return this.identifier;
	  }

	  public void setIdentifier(String identifier) {
	    this.identifier = identifier;
	  }

	  public LendDetailRequestDTO(String identifier)
	  {
	    this.identifier = identifier;
	  }

	  public LendDetailRequestDTO()
	  {
	  }
}