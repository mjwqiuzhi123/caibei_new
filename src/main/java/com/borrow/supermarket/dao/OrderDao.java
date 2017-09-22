package com.borrow.supermarket.dao;

import com.borrow.supermarket.base.BaseMapper;
import com.borrow.supermarket.model.OrderModel;
import com.borrow.supermarket.request.dto.GetNewOrderRequestDTO;
import com.borrow.supermarket.response.result.GetUserOrdersResultDTO;
import java.util.List;

public abstract interface OrderDao extends BaseMapper<OrderModel>
{
  public abstract int updateOrderStatus(OrderModel paramOrderModel);

  public abstract List<GetUserOrdersResultDTO> getUserOrders(String paramString);

  public abstract GetNewOrderRequestDTO getNewOrderInfo();

  public abstract GetNewOrderRequestDTO getTotalLendMoneyInfo();

  public abstract GetNewOrderRequestDTO getTotalLendPersonInfo();
}