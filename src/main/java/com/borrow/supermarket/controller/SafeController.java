package com.borrow.supermarket.controller;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.request.dto.SafePageRequestDTO;
import com.borrow.supermarket.service.SafeService;
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

@RequestMapping({"/front/safe"})
@Controller
public class SafeController extends BaseController
{

  @Autowired
  private SafeService safeServiceI;

  @RequestMapping(value={"/safePage.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  @ResponseBody
  public String safePage(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute("safePage") SafePageRequestDTO safePageRequestDTO, BindingResult bind)
  {
    try
    {
      if (bind.hasErrors())
        return getValidErrors(bind).getAsJSON();
      PageWebDTOResult pageresult = this.safeServiceI.getSafeByPage(safePageRequestDTO);
      return pageresult.getAsJSON();
    } catch (Exception e) {
      ResponseEntity messageResult = new ResponseEntity();
      messageResult.setMsg(ServiceCode.EXCEPTION);
      return messageResult.getAsJSON();
    }
  }
}