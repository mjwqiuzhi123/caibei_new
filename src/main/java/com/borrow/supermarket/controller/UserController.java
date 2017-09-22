package com.borrow.supermarket.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.base64.Base64Server;
import com.borrow.supermarket.enums.VeriCode;
import com.borrow.supermarket.enums.VeriCodeNum;
import com.borrow.supermarket.jedis.CurrentUser;
import com.borrow.supermarket.model.VericodesModel;
import com.borrow.supermarket.request.dto.CheckIsPhoneRequestDTO;
import com.borrow.supermarket.request.dto.ResetLoginPasswordRequestDTO;
import com.borrow.supermarket.request.dto.UserLoginRequestDTO;
import com.borrow.supermarket.request.dto.UserRegisterRequestDTO;
import com.borrow.supermarket.response.result.UseVeriCodeResultDTO;
import com.borrow.supermarket.service.UserService;
import com.borrow.supermarket.service.VericodesService;
import com.borrow.supermarket.util.DateUtil;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;

@RequestMapping({"/front"})
@RestController
public class UserController extends BaseController
{

  @Autowired
  private UserService userServiceI;

  @Autowired
  private VericodesService vericodesServiceImpl;

  @RequestMapping(value={"/login.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity userLogin(HttpServletRequest request, HttpServletResponse response, @Valid UserLoginRequestDTO userLoginRequestDTO, BindingResult bind)
  {
    ResponseEntity responseEntity = new ResponseEntity();
    try {
      if (bind.hasErrors()) {
        return getValidErrors(bind);
      }
      CurrentUser currentUser = getCurrentUser(request);
      StringBuilder userIdentifier = new StringBuilder();
      StringBuilder cellphone = new StringBuilder();
      ResponseEntity result = this.userServiceI.searchUserByNameAndPwd(userLoginRequestDTO, userIdentifier, cellphone);
      if (!result.getResultCode().equals("0000")) {
        if (currentUser != null) {
          Cookie[] cookies = request.getCookies();
          if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
              if (cookies[i].getName().equals("CBDC")) {
                cookies[i].setMaxAge(0);
                cookies[i].setPath("/");
                response.addCookie(cookies[i]);
                break;
              }
            }
          }
        }

        return result;
      }
      int expireMinutes = 30;
      String clientID = request.getHeader("ClientID");
      if ((clientID != null) && (!clientID.isEmpty()))
        expireMinutes = 10080;
      Cookie cookie = new Cookie("CBDC", new Base64Server().encrypted(cellphone.toString(), userIdentifier.toString(), DateUtil.dateMinutesAdd(new Date(), expireMinutes)));
      cookie.setMaxAge((expireMinutes + 10) * 60);
      cookie.setPath("/");
      response.addCookie(cookie);
      response.addHeader("P3P", "CP=CAO PSA OUR");
      return responseEntity;
    } catch (Exception e) {
      responseEntity.setMsg(ServiceCode.EXCEPTION);
    }return responseEntity;
  }

  @RequestMapping(value={"/register.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=utf-8"})
  public ResponseEntity userRegister(HttpServletRequest request, HttpServletResponse response, @Valid UserRegisterRequestDTO userRegisterRequestDTO, BindingResult bind)
  {
    ResponseEntity responseEntity = new ResponseEntity();
    try {
      if (bind.hasErrors()) {
        return getValidErrors(bind);
      }

      responseEntity = this.vericodesServiceImpl.searchByIndentFierAndType(new VericodesModel(userRegisterRequestDTO.getToken(), VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.SignUp)));
      if (!responseEntity.getResultCode().equals("0000")) {
        return responseEntity;
      }

      UseVeriCodeResultDTO useVeriCodeResultDTO = (UseVeriCodeResultDTO)responseEntity.getDTO(UseVeriCodeResultDTO.class);

      boolean isExit = this.userServiceI.searcUserByPhone(new CheckIsPhoneRequestDTO(useVeriCodeResultDTO));
      if (!isExit) {
        responseEntity.setMsg(ServiceCode.REGISTER_ONE);
        return responseEntity;
      }

      boolean userModel = this.userServiceI.saveUser(userRegisterRequestDTO, useVeriCodeResultDTO);
      if (!userModel) {
        responseEntity.setMsg(ServiceCode.ERROR);
        return responseEntity;
      }
      return responseEntity;
    } catch (Exception e) {
      responseEntity.setMsg(ServiceCode.EXCEPTION);
    }return responseEntity;
  }

  @RequestMapping(value={"/checkIsPhone.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=utf-8"})
  public ResponseEntity checkIsPhone(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("checkIsPhone") CheckIsPhoneRequestDTO checkIsPhoneRequestDTO, BindingResult bind)
  {
    ResponseEntity responseEntity = new ResponseEntity();
    try {
      if (bind.hasErrors()) {
        return getValidErrors(bind);
      }
      boolean returnResult = this.userServiceI.searcUserByPhone(checkIsPhoneRequestDTO);
      if (returnResult) {
        responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_ONE);
        return responseEntity;
      }
      responseEntity.setMsg(ServiceCode.SING_IN_REPONSE_FIVE);
      return responseEntity;
    }
    catch (Exception e) {
      responseEntity.setMsg(ServiceCode.EXCEPTION);
    }return responseEntity;
  }

  @RequestMapping(value={"/loginOut.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=utf-8"})
  public ResponseEntity loginOut(HttpServletRequest request, HttpServletResponse response)
  {
    Cookie[] cookies = request.getCookies();

    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals("CBDC")) {
          cookies[i].setMaxAge(0);
          cookies[i].setPath("/");
          response.addCookie(cookies[i]);
          break;
        }
      }
    }

    ResponseEntity messageResult = new ResponseEntity();
    return messageResult;
  }

  @RequestMapping(value={"/resetPassword.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  public ResponseEntity resetLoginPassword(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("resetLoginPassword") ResetLoginPasswordRequestDTO resetLoginPasswordRequestDTO, BindingResult bind)
    throws Exception
  {
    if (bind.hasErrors()) {
      return getValidErrors(bind);
    }

    ResponseEntity messageResult = this.vericodesServiceImpl.searchByIndentFierAndType(new VericodesModel(resetLoginPasswordRequestDTO.getToken(), VeriCodeNum.ToCodeType(VeriCode.VeriCodeType.ResetLoginPassword)));
    if (!messageResult.getResultCode().equals("0000")) {
      return messageResult;
    }

    UseVeriCodeResultDTO useVeriCodeResultDTO = (UseVeriCodeResultDTO)messageResult.getDTO(UseVeriCodeResultDTO.class);
    ResponseEntity messageResult1 = this.userServiceI.resetLoginPassword(useVeriCodeResultDTO.getPhone(), resetLoginPasswordRequestDTO.getPassword(), resetLoginPasswordRequestDTO.getToken());

    return messageResult1;
  }
}