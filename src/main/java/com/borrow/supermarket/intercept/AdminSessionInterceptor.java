package com.borrow.supermarket.intercept;

import com.borrow.supermarket.model.UserModel;
import com.borrow.supermarket.service.UserService;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminSessionInterceptor extends HandlerInterceptorAdapter
{
  private static final Logger log = Logger.getLogger(CheckCookieInterceptor.class);

  @Autowired
  private UserService userServiceI;

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    try {
      UserModel adminModel = (UserModel)request.getSession().getAttribute("adminInfo");
      if (adminModel == null) {
        ResponseEntity result = new ResponseEntity();
        result.setMsg(ServiceCode.SING_USER_COOKIE_NAME_CODE);
        response.getWriter().print(result.getAsJSON());
        return false;
      }
      return true;
    } catch (Exception e) {
      ResponseEntity result = new ResponseEntity();
      result.setMsg(ServiceCode.SING_USER_COOKIE_NAME_CODE);
      log.error("request failure exception: " + result.getAsJSON() + e.getMessage());
      response.getWriter().print(result.getAsJSON());
    }return false;
  }
}