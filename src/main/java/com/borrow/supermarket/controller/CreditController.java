package com.borrow.supermarket.controller;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.request.dto.CreditPageRequestDTO;
import com.borrow.supermarket.service.CreditService;
import com.borrow.supermarket.util.PageWebDTOResult;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({"/front/credit"})
@Controller
public class CreditController extends BaseController
{

  @Autowired
  private CreditService creditServiceI;

  @RequestMapping(value={"/creditPage.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  @ResponseBody
  public String creditPage(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute("creditPage") CreditPageRequestDTO creditPageRequestDTO, BindingResult bind)
  {
    try
    {
      if (bind.hasErrors())
        return getValidErrors(bind).getAsJSON();
      PageWebDTOResult pageresult = this.creditServiceI.getSafeByPage(creditPageRequestDTO);
      return pageresult.getAsJSON();
    } catch (Exception e) {
      ResponseEntity messageResult = new ResponseEntity();
      messageResult.setMsg(ServiceCode.EXCEPTION);
      return messageResult.getAsJSON();
    }
  }
}