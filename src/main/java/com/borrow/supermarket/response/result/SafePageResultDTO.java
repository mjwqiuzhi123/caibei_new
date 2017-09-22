package com.borrow.supermarket.response.result;

public class SafePageResultDTO
{
  private String identifier;
  private String safeName;
  private String safePicUrl;
  private String safeUrl;
  private String safeSpecial;

  public String getIdentifier()
  {
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

  public String getSafeSpecial() {
    return this.safeSpecial;
  }

  public void setSafeSpecial(String safeSpecial) {
    this.safeSpecial = safeSpecial;
  }

  public SafePageResultDTO(String identifier, String safeName, String safePicUrl, String safeUrl, String safeSpecial)
  {
    this.identifier = identifier;
    this.safeName = safeName;
    this.safePicUrl = safePicUrl;
    this.safeUrl = safeUrl;
    this.safeSpecial = safeSpecial;
  }

  public SafePageResultDTO()
  {
  }
}