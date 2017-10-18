package com.borrow.supermarket.controller;

import com.borrow.supermarket.condition.LendManagerCondition;
import com.borrow.supermarket.model.OrderModel;
import com.borrow.supermarket.model.UserModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.service.OrderService;
import com.borrow.supermarket.service.UserService;
import com.wordnik.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"/admin/users"})
@Controller
@Api(value = "/admin/users", hidden = true)
public class AdminUsersInfoController
{

  @Autowired
  private UserService userServiceI;

  @Autowired
  private OrderService orderServiceI;

  @RequestMapping({"/userManager.json"})
  public ModelAndView safeManager(HttpServletRequest request, HttpServletResponse response, UserModel userModel)
    throws Exception
  {
    ModelAndView mv = new ModelAndView();
    PageParameter pageView = null;
    String pageNow = request.getParameter("pageNow");
    if ((pageNow == null) || ("".equals(pageNow)))
      pageView = new PageParameter();
    else {
      pageView = new PageParameter(Integer.parseInt(pageNow));
    }
    mv.addObject("userList", this.userServiceI.selectUserList(userModel, pageView));
    mv.addObject("userModel", userModel);
    mv.addObject("pageView", pageView);
    mv.setViewName("user/userManager");
    return mv;
  }

  @RequestMapping({"/userOrderManager.json"})
  public ModelAndView userOrderManager(HttpServletRequest request, HttpServletResponse response, OrderModel orderModel) throws Exception {
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
    mv.setViewName("user/userOrderManager");
    return mv;
  }
}