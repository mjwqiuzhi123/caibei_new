package com.borrow.supermarket.model;

import java.io.Serializable;
import java.util.Date;

public class CreditModel
  implements Serializable
{
  private Integer id;
  private String identifier;
  private String criditName;
  private String criditPicUrl;
  private String criditUrl;
  private Integer status;
  private Date createDate;
  private Date updateDate;
  private String criditSpecial;
  private long totalApply;

  public CreditModel(Integer id, String identifier, String criditName, String criditPicUrl, String criditUrl, Integer status, Date createDate, Date updateDate, String criditSpecial, long totalApply)
  {
    this.id = id;
    this.identifier = identifier;
    this.criditName = criditName;
    this.criditPicUrl = criditPicUrl;
    this.criditUrl = criditUrl;
    this.status = status;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.criditSpecial = criditSpecial;
    this.totalApply = totalApply;
  }

  public CreditModel()
  {
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

  public String getCriditName() {
    return this.criditName;
  }

  public void setCriditName(String criditName) {
    this.criditName = criditName;
  }

  public String getCriditPicUrl() {
    return this.criditPicUrl;
  }

  public void setCriditPicUrl(String criditPicUrl) {
    this.criditPicUrl = criditPicUrl;
  }

  public String getCriditUrl() {
    return this.criditUrl;
  }

  public void setCriditUrl(String criditUrl) {
    this.criditUrl = criditUrl;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateDate() {
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

  public String getCriditSpecial() {
    return this.criditSpecial;
  }

  public void setCriditSpecial(String criditSpecial) {
    this.criditSpecial = criditSpecial;
  }

  public long getTotalApply() {
    return this.totalApply;
  }

  public void setTotalApply(long totalApply) {
    this.totalApply = totalApply;
  }
}