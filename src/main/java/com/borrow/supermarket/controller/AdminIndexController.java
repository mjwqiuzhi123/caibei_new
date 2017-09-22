package com.borrow.supermarket.controller;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.model.UserModel;
import com.borrow.supermarket.request.dto.LoginOnDTO;
import com.borrow.supermarket.service.UserService;
import com.wordnik.swagger.annotations.Api;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"/cbdc"})
@Controller
@Api(value = "/cbdc", hidden = true)
public class AdminIndexController extends BaseController
{

	  @Autowired
	  private UserService userService;

	  @RequestMapping({"/login.json"})
	  public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response)
	  {
	    return new ModelAndView("login");
	  }

	  @RequestMapping({"/loginOn.json"})
	  public ModelAndView loginOn(HttpServletRequest request, HttpServletResponse response, ModelMap model, @Valid @ModelAttribute("loginOn") LoginOnDTO loginDTO, BindingResult bind)
	  {
	    UserModel adminModel = (UserModel)request.getSession().getAttribute("adminInfo");
	    if (adminModel != null) {
	      request.getSession().setAttribute("adminInfo", adminModel);
	      return new ModelAndView("forward:main.json");
	    }

	    ModelAndView mv = new ModelAndView();
	    if (loginDTO == null) {
	      mv.addObject("error", "账号和密码不能为空");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    if ((loginDTO.getAdminName() == null || (loginDTO.getAdminName().trim().equals("")))) {
	      mv.addObject("error", "管理员名称不能为空");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    if ((loginDTO.getAdminPwd() == null) || (loginDTO.getAdminPwd().trim().equals(""))) {
	      mv.addObject("error", "管理员密码不能为空");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    if (!loginDTO.getAdminName().trim().equals("guochaojun")) {
	      mv.addObject("error", "Sorry 你的账号不是指定账号请联系管理员");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    UserModel userModel = this.userService.searchAdminByNameAndPwd(loginDTO);
	    if (userModel == null) {
	      mv.addObject("error", "管理员账号密码错误");
	      mv.setViewName("forward:login.json");
	      return mv;
	    }

	    request.getSession().setAttribute("adminInfo", userModel);
	    return new ModelAndView("forward:main.json");
	  }

	  @RequestMapping({"/main.json"})
	  public ModelAndView main(HttpServletRequest request, HttpServletResponse response)
	  {
	    return new ModelAndView("main");
	  }

	  @RequestMapping({"/loginOut.json"})
	  public String loginOut(HttpServletRequest request, HttpServletResponse response) {
	    UserModel adminModel = (UserModel)request.getSession().getAttribute("adminInfo");
	    if (adminModel != null) {
	      request.getSession().setAttribute("adminInfo", null);
	    }
	    return "forward:login.json";
	  }

	  @RequestMapping({"/businessManager.json"})
	  public ModelAndView businessManager(HttpServletRequest request, HttpServletResponse response)
	  {
	    return new ModelAndView("business/businessManager");
	  }

	  @RequestMapping({"/ftpTest.json"})
	  public ModelAndView ftpTest(HttpServletRequest request, HttpServletResponse response)
	  {
	    return new ModelAndView("ftp/ftptest");
	  }

	  @RequestMapping({"/doftpTest.json"})
	  public ModelAndView doftpTest(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception
	  {
	    try {
	      String fileName = file.getOriginalFilename();
	      String realPath = request.getSession().getServletContext().getRealPath("/");

	      String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;

	      String path = realPath + "resource\\" + trueFileName;
	      System.out.println("存放图片文件的路径:" + path);

	      File file2 = new File(path);
	      file.transferTo(new File(path));
	    }
	    catch (IllegalStateException|IOException e)
	    {
	      e.printStackTrace();
	    }
	    return new ModelAndView("ftp/ftptest");
	  }
}