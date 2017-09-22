package com.borrow.supermarket.controller;

import com.borrow.supermarket.model.SafeModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.service.SafeService;
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

@RequestMapping({"/admin/safe"})
@Controller
@Api(value = "/admin/safe", hidden = true)
public class AdminSafeController
{

  @Autowired
  private SafeService safeServiceI;

  @RequestMapping({"/safeManager.json"})
  public ModelAndView safeManager(HttpServletRequest request, HttpServletResponse response, SafeModel safeModel)
    throws Exception
  {
    ModelAndView mv = new ModelAndView();
    PageParameter pageView = null;
    String pageNow = request.getParameter("pageNow");
    if (((pageNow == null) || "".equals(pageNow)))
      pageView = new PageParameter();
    else {
      pageView = new PageParameter(Integer.parseInt(pageNow));
    }
    mv.addObject("safeList", this.safeServiceI.selectSafeList(safeModel, pageView));
    mv.addObject("safeModel", safeModel);
    mv.addObject("pageView", pageView);
    mv.setViewName("thirdParty/safe/safeManager");
    return mv;
  }

  @RequestMapping({"/safeAddPage.json"})
  public ModelAndView safeAddPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView("thirdParty/safe/safeAdd");
  }

  @RequestMapping(value={"/safeAdd.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String safeAdd(MultipartFile file, HttpServletRequest request, HttpServletResponse response, SafeModel safeModel) throws Exception {
    Map returnResult = ImageUtil.imagePath2(file, "lend", request, response);
    boolean flag = ((Boolean)returnResult.get("flag")).booleanValue();
    if (!flag) {
      return "redirect:safeAddPage.json";
    }
    safeModel.setSafePicUrl(String.valueOf(returnResult.get("path")));
    boolean returnFlag = this.safeServiceI.saveSafe(safeModel);
    if (returnFlag) {
      return "forward:safeManager.json";
    }
    return "redirect:safeAddPage.json";
  }

  @RequestMapping({"/safeUpdatePage.json"})
  public ModelAndView safeUpdatePage(HttpServletRequest request, HttpServletResponse response, SafeModel safeModel) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("thirdParty/safe/safeUpdate");
    mv.addObject("safe", this.safeServiceI.getSafeByIdentifier(safeModel));
    return mv;
  }

  @RequestMapping(value={"/safeUpdate.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String safeUpdate(MultipartFile file, HttpServletRequest request, HttpServletResponse response, SafeModel safeModel) throws Exception {
    if (file.getSize() > 0L) {
      SafeModel safeModel2 = this.safeServiceI.getSafeByIdentifier(safeModel);
      ImageUtil.deleteFile(safeModel2.getSafePicUrl(), request);
      Map returnResult = ImageUtil.imagePath2(file, "lend", request, response);
      safeModel.setSafePicUrl(String.valueOf(returnResult.get("path")));
    }

    boolean returnFlag = this.safeServiceI.updateSafe(safeModel);
    if (returnFlag) {
      return "forward:safeManager.json?";
    }
    return "redirect:safeUpdatePage.json?identifier = " + safeModel.getIdentifier();
  }

  @RequestMapping(value={"/safeDetails.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView safeDetails(HttpServletRequest request, HttpServletResponse response, SafeModel safeModel) throws Exception
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("thirdParty/safe/safeDetail");
    mv.addObject("safe", this.safeServiceI.getSafeByIdentifier(safeModel));
    return mv;
  }

  @RequestMapping(value={"/safeStatusUpdate.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String safeStatusUpdate(HttpServletRequest request, HttpServletResponse response, SafeModel safeModel) throws Exception {
    boolean returnFlag = this.safeServiceI.updateSafeStatus(safeModel);
    return "forward:safeManager.json";
  }
}