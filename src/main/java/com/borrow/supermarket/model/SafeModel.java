package com.borrow.supermarket.model;

import java.io.Serializable;
import java.util.Date;

public class SafeModel
  implements Serializable
{
  private Integer id;
  private String identifier;
  private String safeName;
  private String safePicUrl;
  private String safeUrl;
  private Integer status;
  private Date createDate;
  private Date updateDate;
  private long totalApply;
  private String safeSpecial;

  public SafeModel(Integer id, String identifier, String safeName, String safePicUrl, String safeUrl, Integer status, Date createDate, Date updateDate, long totalApply, String safeSpecial)
  {
    this.id = id;
    this.identifier = identifier;
    this.safeName = safeName;
    this.safePicUrl = safePicUrl;
    this.safeUrl = safeUrl;
    this.status = status;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.totalApply = totalApply;
    this.safeSpecial = safeSpecial;
  }

  public SafeModel()
  {
  }

  public Integer getId()
  {
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

  public String getSafeName() {
    return this.safeName;
  }

  public void setSafeName(String safeName) {
    this.safeName = safeName;
  }

  public String getSafePicUrl() {
    return this.safePicUrl;
  }

  public void setSafePicUrl(String safePicUrl) {
    this.safePicUrl = safePicUrl;
  }

  public String getSafeUrl() {
    return this.safeUrl;
  }

  public void setSafeUrl(String safeUrl) {
    this.safeUrl = safeUrl;
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

  public long getTotalApply() {
    return this.totalApply;
  }

  public void setTotalApply(long totalApply) {
    this.totalApply = totalApply;
  }

  public String getSafeSpecial() {
    return this.safeSpecial;
  }

  public void setSafeSpecial(String safeSpecial) {
    this.safeSpecial = safeSpecial;
  }
}