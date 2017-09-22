package com.borrow.supermarket.condition;

public class BaseConditionModel
{
  private int value;
  private String des;

  public int getValue()
  {
    return this.value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getDes() {
    return this.des;
  }

  public void setDes(String des) {
    this.des = des;
  }

  public BaseConditionModel(int value, String des)
  {
    this.value = value;
    this.des = des;
  }

  public BaseConditionModel()
  {
  }
}