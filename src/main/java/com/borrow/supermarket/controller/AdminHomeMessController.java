package com.borrow.supermarket.controller;

import com.borrow.supermarket.model.CreditModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.service.CreditService;
import com.borrow.supermarket.util.ImageUtil;
import com.wordnik.swagger.annotations.Api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"/admin/home"})
@Controller
public class AdminHomeMessController
{

  @Autowired
  private CreditService creditServiceI;

  // use
  @RequestMapping({"/homeMessageManager.json"})
  public ModelAndView creditManager(HttpServletRequest request, HttpServletResponse response)
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
    mv.addObject("homeMessageList", this.creditServiceI.selectHomeMessageList(pageView));
    mv.addObject("pageView", pageView);
    mv.setViewName("home/homeMessageManager");
    return mv;
  }
  
  // use
  @RequestMapping({"/homeMessageAddPage.json"})
  public ModelAndView creditAddPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView("home/homeMessageAdd");
  }
  
  // use modify by mjw
  @RequestMapping(value={"/homeMessageAdd.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String creditAdd(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String homeMessage) throws Exception {
	if(homeMessage != null && !homeMessage.equals("")){
		boolean returnFlag = this.creditServiceI.saveHomeMessage(homeMessage);
		if (returnFlag) {
		      return "forward:homeMessageManager.json";
		    }
	}
    return "redirect:homeMessageAddPage.json";
  }

  @RequestMapping({"/creditUpdatePage.json"})
  public ModelAndView creditUpdatePage(HttpServletRequest request, HttpServletResponse response, CreditModel creditModel) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("thirdParty/credit/creditUpdate");
    mv.addObject("cridit", this.creditServiceI.getCreditByIdentifier(creditModel));
    return mv;
  }

  @RequestMapping(value={"/creditUpdate.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String creditUpdate(MultipartFile file, HttpServletRequest request, HttpServletResponse response, CreditModel creditModel) throws Exception {
    if (file.getSize() > 0L) {
      CreditModel creditModel2 = this.creditServiceI.getCreditByIdentifier(creditModel);
      Map returnResult = ImageUtil.imagePath2(file, "lend", request, response);
      ImageUtil.deleteFile(creditModel2.getCriditPicUrl(), request);
      creditModel.setCriditPicUrl(String.valueOf(returnResult.get("path")));
    }
    boolean returnFlag = this.creditServiceI.updateCredit(creditModel);
    if (returnFlag) {
      return "forward:creditManager.json?identifier = 121212";
    }
    return "redirect:creditUpdatePage.json?identifier = " + creditModel.getIdentifier();
  }

  @RequestMapping(value={"/creditDetails.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView creditDetails(HttpServletRequest request, HttpServletResponse response, CreditModel creditModel) throws Exception
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("thirdParty/credit/creditDetail");
    mv.addObject("cridit", this.creditServiceI.getCreditByIdentifier(creditModel));
    return mv;
  }

  @RequestMapping(value={"/creditStatusUpdate.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String creditStatusUpdate(HttpServletRequest request, HttpServletResponse response, CreditModel creditModel) throws Exception {
    boolean returnFlag = this.creditServiceI.updateCreditStatus(creditModel);
    return "forward:creditManager.json";
  }

  @RequestMapping(value={"/creditDelete.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String creditDelete(HttpServletRequest request, HttpServletResponse response, CreditModel creditModel) throws Exception
  {
    return "forward:creditManager.json";
  }
}