package com.borrow.supermarket.controller;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.jedis.CurrentUser;
import com.borrow.supermarket.request.dto.GetNewOrderRequestDTO;
import com.borrow.supermarket.request.dto.GetsaveOrderRequestDTO;
import com.borrow.supermarket.service.OrderService;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping({"/front/order/"})
@RestController
public class OrderController extends BaseController
{

  @Autowired
  private OrderService orderServiceI;

  @RequestMapping(value={"/orderPage.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity getUserOrders(HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      ResponseEntity re = new ResponseEntity();

      CurrentUser currentUser = getCurrentUser(request);
      List userOrderList = this.orderServiceI.getUserOrders(currentUser.getUseridentifier());
      re.setProperties(userOrderList);
      return re;
    } catch (Exception e) {
      ResponseEntity messageResult = new ResponseEntity();
      messageResult.setMsg(ServiceCode.EXCEPTION);
      return messageResult;
    }
  }

  @RequestMapping(value={"/lendApply.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity saveUserOrder(HttpServletRequest request, HttpServletResponse response, ModelMap model, @Valid @ModelAttribute("saveUserOrder") GetsaveOrderRequestDTO getsaveOrderRequestDTO, BindingResult bind) {
    ResponseEntity re = new ResponseEntity();
    try {
      if (bind.hasErrors())
        return getValidErrors(bind);
      CurrentUser currentUser = getCurrentUser(request);
      return this.orderServiceI.saveUserOrder(getsaveOrderRequestDTO, currentUser.getUseridentifier());
    } catch (Exception e) {
      re.setMsg(ServiceCode.EXCEPTION);
    }return re;
  }

  @RequestMapping(value={"/newOrderInfo.json"}, produces={"application/json; charset=utf-8"})
  public ResponseEntity getNewOrder(HttpServletRequest request, HttpServletResponse response)
  {
    ResponseEntity re = new ResponseEntity();
    try {
      GetNewOrderRequestDTO newOrderInfo = this.orderServiceI.newOrderInfo();
      re.addProperty(newOrderInfo);
      return re;
    } catch (Exception e) {
      re.setMsg(ServiceCode.EXCEPTION);
    }return re;
  }
}