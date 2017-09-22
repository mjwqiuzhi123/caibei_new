package com.borrow.supermarket.request.dto;

import javax.validation.constraints.NotNull;

public class CreditPageRequestDTO
{

  @NotNull
  private Integer number;

  public Integer getNumber()
  {
    return this.number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public CreditPageRequestDTO(Integer number)
  {
    this.number = number;
  }

  public CreditPageRequestDTO()
  {
  }
}