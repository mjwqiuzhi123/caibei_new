package com.borrow.supermarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.enums.VeriCode;
import com.borrow.supermarket.enums.VeriCodeNum;
import com.borrow.supermarket.request.dto.CheckIsPhoneRequestDTO;
import com.borrow.supermarket.request.dto.SendRequestDTO;
import com.borrow.supermarket.request.dto.VerifyCodeRequestDTO;
import com.borrow.supermarket.service.UserService;
import com.borrow.supermarket.service.VericodesService;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;

@RequestMapping({"/front/veriCodes"})
@RestController
public class VeriCodesController extends BaseController
{

  @Autowired
  private VericodesService vericodesServiceImpl;

  @Autowired
  private UserService userServiceI;

  @RequestMapping(value={"/send.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity send(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("contentModel") SendRequestDTO sendRequestDTO, BindingResult bind)
    throws Exception
  {
    if (bind.hasErrors()) {
      return getValidErrors(bind);
    }
    ResponseEntity messageResult = new ResponseEntity();
    boolean flag = this.userServiceI.searcUserByPhone(new CheckIsPhoneRequestDTO(sendRequestDTO));
    if ((sendRequestDTO.getType() == VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.SignUp).intValue()) && (!flag)) {
      messageResult = new ResponseEntity();
      messageResult.setMsg(ServiceCode.CHECKCELLPHONE_TWO);
      return messageResult;
    }

    if ((sendRequestDTO.getType() == VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.ResetLoginPassword).intValue()) && (flag)) {
      messageResult = new ResponseEntity();
      messageResult.setMsg(ServiceCode.CHECKCELLPHONE_ONE);
      return messageResult;
    }

    messageResult = this.vericodesServiceImpl.sendWithTokenAsync(sendRequestDTO.getPhone(), null, sendRequestDTO.getType());
    return messageResult;
  }

  @RequestMapping(value={"/verifyCode.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity verifyCode(HttpServletRequest request, @Valid VerifyCodeRequestDTO verifyCodeRequestDTO, BindingResult bind)
    throws Exception
  {
    if (bind.hasErrors()) {
      return getValidErrors(bind);
    }
    ResponseEntity messageResult = this.vericodesServiceImpl.verifyVeriAsync(verifyCodeRequestDTO.getPhone(), verifyCodeRequestDTO.getCode(), verifyCodeRequestDTO.getType());
    return messageResult;
  }
}