package com.borrow.supermarket.request.dto;

import javax.validation.constraints.NotNull;

public class SafePageRequestDTO
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

  public SafePageRequestDTO(Integer number)
  {
    this.number = number;
  }

  public SafePageRequestDTO()
  {
  }
}