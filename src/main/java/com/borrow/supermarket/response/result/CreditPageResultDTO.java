package com.borrow.supermarket.response.result;

public class CreditPageResultDTO
{
  private String identifier;
  private String criditName;
  private String criditPicUrl;
  private String criditUrl;
  private String criditSpecial;

  public String getIdentifier()
  {
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

  public String getCriditSpecial() {
    return this.criditSpecial;
  }

  public void setCriditSpecial(String criditSpecial) {
    this.criditSpecial = criditSpecial;
  }

  public CreditPageResultDTO(String identifier, String criditName, String criditPicUrl, String criditUrl, String criditSpecial)
  {
    this.identifier = identifier;
    this.criditName = criditName;
    this.criditPicUrl = criditPicUrl;
    this.criditUrl = criditUrl;
    this.criditSpecial = criditSpecial;
  }

  public CreditPageResultDTO()
  {
  }
}