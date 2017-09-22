package com.borrow.supermarket.util;

import java.util.List;

public class PageWebDTOResult<T> extends ResponseEntity
{
  private long pageSize;
  private long pageNumber;
  private long totalCount;
  private long pageCount;
  private List<T> listData;

  public PageWebDTOResult(long pageSize, long pageNumber, long totalCount, long pageCount, List<T> listData)
  {
    this.pageSize = pageSize;
    this.pageNumber = pageNumber;
    this.totalCount = totalCount;
    this.pageCount = pageCount;
    this.listData = listData;
  }
  public long getPageSize() {
    return this.pageSize;
  }
  public void setPageSize(long pageSize) {
    this.pageSize = pageSize;
  }
  public long getPageNumber() {
    return this.pageNumber;
  }
  public void setPageNumber(long pageNumber) {
    this.pageNumber = pageNumber;
  }
  public long getTotalCount() {
    return this.totalCount;
  }
  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }
  public long getPageCount() {
    return this.pageCount;
  }
  public void setPageCount(long pageCount) {
    this.pageCount = pageCount;
  }
  public List<T> getListData() {
    return this.listData;
  }
  public void setListData(List<T> listData) {
    this.listData = listData;
  }
}