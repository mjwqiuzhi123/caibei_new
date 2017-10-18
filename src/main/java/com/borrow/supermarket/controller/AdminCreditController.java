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

@Api(value = "/admin/credit", hidden = true)
@RequestMapping({"/admin/credit"})
@Controller
public class AdminCreditController
{

  @Autowired
  private CreditService creditServiceI;

  @RequestMapping({"/creditManager.json"})
  public ModelAndView creditManager(HttpServletRequest request, HttpServletResponse response, CreditModel creditModel)
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
    mv.addObject("creditList", this.creditServiceI.selectCreditList(creditModel, pageView));
    mv.addObject("creditModel", creditModel);
    mv.addObject("pageView", pageView);
    mv.setViewName("thirdParty/credit/creditManager");
    return mv;
  }

  @RequestMapping({"/creditAddPage.json"})
  public ModelAndView creditAddPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView("thirdParty/credit/creditAdd");
  }

  @RequestMapping(value={"/creditAdd.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String creditAdd(MultipartFile file, HttpServletRequest request, HttpServletResponse response, CreditModel creditModel) throws Exception {
    Map returnResult = ImageUtil.imagePath2(file, "lend", request, response);
    boolean flag = ((Boolean)returnResult.get("flag")).booleanValue();
    if (!flag) {
      return "redirect:creditAddPage.json";
    }
    creditModel.setCriditPicUrl(String.valueOf(returnResult.get("path")));
    boolean returnFlag = this.creditServiceI.saveCredit(creditModel);
    if (returnFlag) {
      return "forward:creditManager.json";
    }
    return "redirect:creditAddPage.json";
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