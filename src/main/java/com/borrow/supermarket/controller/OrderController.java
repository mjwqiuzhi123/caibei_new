package com.borrow.supermarket.controller;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.jedis.CurrentUser;
import com.borrow.supermarket.request.dto.GetNewOrderRequestDTO;
import com.borrow.supermarket.request.dto.GetsaveOrderRequestDTO;
import com.borrow.supermarket.request.dto.HomeMessDisDTO;
import com.borrow.supermarket.request.dto.LendPageRequestDTO;
import com.borrow.supermarket.request.dto.ProductDTO;
import com.borrow.supermarket.response.result.LendPageDTOResult;
import com.borrow.supermarket.service.OrderService;
import com.borrow.supermarket.util.PageWebDTOResult;
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
import org.springframework.web.bind.annotation.ResponseBody;
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
  
  /**
   * 保存用户的贷款申请订单
   * @param request
   * @param response
   * @param model
   * @param getsaveOrderRequestDTO
   * @param bind
   * @return
   */
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

  // 获取首页统计内容
  @RequestMapping(value={"/newOrderInfo1.json"}, produces={"application/json; charset=utf-8"})
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
  
	// add by mjw 首页推送信息
	@RequestMapping(value = { "/newOrderInfo.json" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST }, produces = { "application/json; charset=utf-8" })
	public String homeContentPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			PageWebDTOResult<Object> pageresult = this.orderServiceI.getContent(1);
			return pageresult.getAsJSON();
		} catch (Exception e) {
			ResponseEntity messageResult = new ResponseEntity();
			messageResult.setMsg(ServiceCode.EXCEPTION);
			return messageResult.getAsJSON();
		}
	}
  
	// add by mjw 发现内容信息
	@RequestMapping(value = { "/findOrderInfo.json" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST }, produces = { "application/json; charset=utf-8" })
	public String findContentPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			PageWebDTOResult<Object> pageresult = this.orderServiceI.getContent(2);
			return pageresult.getAsJSON();
		} catch (Exception e) {
			ResponseEntity messageResult = new ResponseEntity();
			messageResult.setMsg(ServiceCode.EXCEPTION);
			return messageResult.getAsJSON();
		}
	}
}