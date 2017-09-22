package com.borrow.supermarket.controller;

import com.borrow.supermarket.condition.LendManagerCondition;
import com.borrow.supermarket.model.OrderModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.service.OrderService;
import com.wordnik.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"/admin/order"})
@Controller
@Api(value = "/admin/order", hidden = true)
public class AdminOrderController
{

  @Autowired
  private OrderService orderServiceI;

  @RequestMapping({"/orderManager.json"})
  public ModelAndView orderManager(HttpServletRequest request, HttpServletResponse response, OrderModel orderModel)
    throws Exception
  {
    ModelAndView mv = new ModelAndView();
    PageParameter pageView = null;
    String pageNow = request.getParameter("pageNow");
    if (("".equals(pageNow)) || (pageNow == null))
      pageView = new PageParameter();
    else {
      pageView = new PageParameter(Integer.parseInt(pageNow));
    }
    mv.addObject("orderList", this.orderServiceI.selectOrderList(orderModel, pageView));
    mv.addObject("cashbackList", LendManagerCondition.getCashback());
    mv.addObject("orderModel", orderModel);
    mv.addObject("pageView", pageView);
    mv.setViewName("order/orderManager");
    return mv;
  }

  @RequestMapping({"/orderStatusUpdatePage.json"})
  public ModelAndView orderStatusUpdatePage(HttpServletRequest request, HttpServletResponse response, OrderModel orderModel) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("order/orderUpdate");
    mv.addObject("order", this.orderServiceI.getOrderById(orderModel));
    return mv;
  }

  @RequestMapping(value={"/orderStatusUpdate.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String orderStatusUpdate(HttpServletRequest request, HttpServletResponse response, OrderModel orderModel) throws Exception {
    boolean returnFlag = this.orderServiceI.updateCreditStatus(orderModel);
    if (returnFlag) {
      return "forward:orderManager.json?identifier = 121212";
    }
    return "redirect:orderStatusUpdatePage.json?identifier = " + orderModel.getId();
  }

  @RequestMapping(value={"/orderDetails.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView orderDetails(HttpServletRequest request, HttpServletResponse response, OrderModel orderModel) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("order/orderDetail");
    mv.addObject("order", this.orderServiceI.getOrderById(orderModel));
    return mv;
  }
}