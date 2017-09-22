package com.borrow.supermarket.intercept;

import com.borrow.supermarket.base64.Base64Server;
import com.borrow.supermarket.service.UserService;
import com.borrow.supermarket.util.DateUtil;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CheckCookieInterceptor extends HandlerInterceptorAdapter
{
  private static final Logger log = Logger.getLogger(CheckCookieInterceptor.class);

  @Autowired
  private UserService userServiceI;

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
  {
    try {
      String cookieName = null;

      Cookie[] cookies = request.getCookies();

      if (cookies == null) {
        throw new Exception();
      }

      for (Cookie cook : cookies) {
        if (cook.getName().equals("CBDC")) {
          cookieName = cook.getValue();
          break;
        }

      }

      if ((cookieName == null) || ("".equals(cookieName))) {
        throw new Exception();
      }

      Base64Server server = new Base64Server();
      Map deEncrypResult = server.deEncrypted(cookieName);

      String dateTimeStr = String.valueOf(deEncrypResult.get("dateTime"));
      Date dateTimeDe = DateUtil.returnDateTime(dateTimeStr, "yyyy-MM-dd HH:mm:ss");

      if (!dateTimeDe.after(new Date())) {
        ResponseEntity result = new ResponseEntity();
        result.setMsg(ServiceCode.SING_USER_COOKIE_NAME_CODE);
        log.error("request failure invalid: " + result.getAsJSON());
        response.getWriter().print(result.getAsJSON());
        return false;
      }

      request.setAttribute("usersInfo", String.valueOf(deEncrypResult.get("uuid")));

      return true;
    }
    catch (Exception e) {
      ResponseEntity result = new ResponseEntity();
      result.setMsg(ServiceCode.SING_USER_COOKIE_NAME_CODE);
      log.error("request failure exception: " + result.getAsJSON() + e.getMessage());
      response.getWriter().print(result.getAsJSON());
    }return false;
  }
}