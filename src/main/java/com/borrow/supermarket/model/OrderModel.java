package com.borrow.supermarket.model;

import com.borrow.supermarket.request.dto.GetsaveOrderRequestDTO;
import com.borrow.supermarket.util.CommonUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderModel
  implements Serializable
{
  private int id;
  private String identifier;
  private int userId;
  private int productId;
  private int type;
  private int cashback;
  private Date createDate;
  private Date updateDate;
  private String phone;
  private String orderPictureOne;
  private String orderPictureTwo;
  private String orderPictureThree;
  private String userPhone;
  private String lendName;
  private String orderIdentifier;
  private String orderPhone;
  private Date cashbackDate;
  private BigDecimal lendMoney;
  private long lendPerid;

  public String getOrderIdentifier()
  {
    return this.orderIdentifier;
  }

  public void setOrderIdentifier(String orderIdentifier) {
    this.orderIdentifier = orderIdentifier;
  }

  public String getOrderPhone() {
    return this.orderPhone;
  }

  public void setOrderPhone(String orderPhone) {
    this.orderPhone = orderPhone;
  }

  public String getLendName() {
    return this.lendName;
  }

  public void setLendName(String lendName) {
    this.lendName = lendName;
  }

  public String getUserPhone() {
    return this.userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getProductId() {
    return this.productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getCashback() {
    return this.cashback;
  }

  public void setCashback(int cashback) {
    this.cashback = cashback;
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

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getOrderPictureOne() {
    return this.orderPictureOne;
  }

  public void setOrderPictureOne(String orderPictureOne) {
    this.orderPictureOne = orderPictureOne;
  }

  public String getOrderPictureTwo() {
    return this.orderPictureTwo;
  }

  public void setOrderPictureTwo(String orderPictureTwo) {
    this.orderPictureTwo = orderPictureTwo;
  }

  public String getOrderPictureThree() {
    return this.orderPictureThree;
  }

  public void setOrderPictureThree(String orderPictureThree) {
    this.orderPictureThree = orderPictureThree;
  }

  public OrderModel(int id, String identifier, int userId, int productId, int type, int cashback, Date createDate, Date updateDate, String phone, String orderPictureOne, String orderPictureTwo, String orderPictureThree)
  {
    this.id = id;
    this.identifier = identifier;
    this.userId = userId;
    this.productId = productId;
    this.type = type;
    this.cashback = cashback;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.phone = phone;
    this.orderPictureOne = orderPictureOne;
    this.orderPictureTwo = orderPictureTwo;
    this.orderPictureThree = orderPictureThree;
  }

  public OrderModel()
  {
  }

  public OrderModel(int id, String identifier, int userId, int productId, int type, int cashback, Date createDate, Date updateDate, String phone, String orderPictureOne, String orderPictureTwo, String orderPictureThree, String userPhone, String lendName)
  {
    this.id = id;
    this.identifier = identifier;
    this.userId = userId;
    this.productId = productId;
    this.type = type;
    this.cashback = cashback;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.phone = phone;
    this.orderPictureOne = orderPictureOne;
    this.orderPictureTwo = orderPictureTwo;
    this.orderPictureThree = orderPictureThree;
    this.userPhone = userPhone;
    this.lendName = lendName;
  }

  public Date getCashbackDate() {
    return this.cashbackDate;
  }

  public void setCashbackDate(Date cashbackDate) {
    this.cashbackDate = cashbackDate;
  }

  public BigDecimal getLendMoney() {
    return this.lendMoney;
  }

  public void setLendMoney(BigDecimal lendMoney) {
    this.lendMoney = lendMoney;
  }

  public long getLendPerid() {
    return this.lendPerid;
  }

  public void setLendPerid(long lendPerid) {
    this.lendPerid = lendPerid;
  }

  public OrderModel(int id, String identifier, int userId, int productId, int type, int cashback, Date createDate, Date updateDate, String phone, String orderPictureOne, String orderPictureTwo, String orderPictureThree, String userPhone, String lendName, String orderIdentifier, String orderPhone, Date cashbackDate, BigDecimal lendMoney, long lendPerid)
  {
    this.id = id;
    this.identifier = identifier;
    this.userId = userId;
    this.productId = productId;
    this.type = type;
    this.cashback = cashback;
    this.createDate = createDate;
    this.updateDate = updateDate;
    this.phone = phone;
    this.orderPictureOne = orderPictureOne;
    this.orderPictureTwo = orderPictureTwo;
    this.orderPictureThree = orderPictureThree;
    this.userPhone = userPhone;
    this.lendName = lendName;
    this.orderIdentifier = orderIdentifier;
    this.orderPhone = orderPhone;
    this.cashbackDate = cashbackDate;
    this.lendMoney = lendMoney;
    this.lendPerid = lendPerid;
  }

  public OrderModel(UserModel userModel, LendModel lendModel, GetsaveOrderRequestDTO getsaveOrderRequestDTO) {
    this.identifier = ("Lend" + CommonUtil.getUUID());
    this.userId = userModel.getId();
    this.productId = lendModel.getId().intValue();
    this.lendMoney = getsaveOrderRequestDTO.getLendTotalMoney();
    this.lendPerid = getsaveOrderRequestDTO.getLendTotalPerid().intValue();
    this.type = getsaveOrderRequestDTO.getType().intValue();
  }
}