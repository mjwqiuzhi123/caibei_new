package com.borrow.supermarket.controller;

import com.borrow.supermarket.condition.LendManagerCondition;
import com.borrow.supermarket.model.LendModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.service.LendService;
import com.borrow.supermarket.util.ImageUtil;
import com.wordnik.swagger.annotations.Api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping({"/admin/lend"})
@Controller
@Api(value = "/admin/lend", hidden = true)
public class AdminLendController
{

  @Autowired
  private LendService lendServiceI;

  @RequestMapping({"/lendManager.json"})
  public ModelAndView lendManager(HttpServletRequest request, HttpServletResponse response, LendModel lendModel)
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

    mv.addObject("lendList", this.lendServiceI.selectLendList(lendModel, pageView));
    mv.addObject("lendModel", lendModel);
    mv.addObject("pageView", pageView);
    mv.addObject("platformNatureList", LendManagerCondition.getPlatformNature());
    mv.addObject("lendMoneyList", LendManagerCondition.getLendMoney());
    mv.setViewName("thirdParty/lend/lendManager");
    return mv;
  }

  @RequestMapping({"/lendAddPage.json"})
  public ModelAndView lendAddPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView("thirdParty/lend/lendAdd");
  }

  @RequestMapping(value={"/lendAdd.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String lendAdd(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("lendAdd")LendModel lendModel) throws Exception
  {
    Map returnResult = ImageUtil.imagePath2(file, "lend", request, response);
    boolean flag = ((Boolean)returnResult.get("flag")).booleanValue();
    if (!flag) {
      return "redirect:lendAddPage.json";
    }

    lendModel.setLendPicUrl(String.valueOf(returnResult.get("path")));
    boolean returnFlag = this.lendServiceI.saveLend(lendModel);
    if (returnFlag) {
      return "forward:lendManager.json";
    }
    return "redirect:lendAddPage.json";
  }

  @RequestMapping({"/lendUpdatePage.json"})
  public ModelAndView lendUpdatePage(HttpServletRequest request, HttpServletResponse response, LendModel lendModel) throws Exception {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("thirdParty/lend/lendUpdate");
    mv.addObject("lendModel", this.lendServiceI.getLendByIdentifier(lendModel));
    mv.addObject("platformNatureList", LendManagerCondition.getPlatformNature());
    mv.addObject("lendMoneyList", LendManagerCondition.getLendMoney());
    mv.addObject("creditStandingList", LendManagerCondition.getCreditStanding());
    mv.addObject("hasActivityList", LendManagerCondition.getHasActivity());
    mv.addObject("lendOnlineTimeList", LendManagerCondition.getLendOnlineTime());
    mv.addObject("lendPeriodList", LendManagerCondition.getLendPeriod());
    mv.addObject("hasCreditList", LendManagerCondition.getHasCredit());
    return mv;
  }

  @RequestMapping(value={"/lendUpdate.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String lendUpdate(MultipartFile file, HttpServletRequest request, HttpServletResponse response, LendModel lendModel) throws Exception {
    if (file.getSize() > 0L) {
      LendModel lendModel2 = this.lendServiceI.getLendByIdentifier(lendModel);
      ImageUtil.deleteFile(lendModel2.getLendPicUrl(), request);
      Map returnResult = ImageUtil.imagePath2(file, "lend", request, response);
      lendModel.setLendPicUrl(String.valueOf(returnResult.get("path")));
    }
    boolean returnFlag = this.lendServiceI.updateLend(lendModel);
    if (returnFlag) {
      return "forward:lendManager.json?identifier = 121212";
    }
    return "redirect:lendUpdatePage.json?identifier = " + lendModel.getIdentifier();
  }

  @RequestMapping(value={"/lendDetails.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public ModelAndView lendDetails(HttpServletRequest request, HttpServletResponse response, LendModel lendModel) throws Exception
  {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("thirdParty/lend/lendDetail");
    mv.addObject("lendModel", this.lendServiceI.getLendByIdentifier(lendModel));
    mv.addObject("platformNatureList", LendManagerCondition.getPlatformNature());
    mv.addObject("lendMoneyList", LendManagerCondition.getLendMoney());
    mv.addObject("creditStandingList", LendManagerCondition.getCreditStanding());
    mv.addObject("hasActivityList", LendManagerCondition.getHasActivity());
    mv.addObject("lendOnlineTimeList", LendManagerCondition.getLendOnlineTime());
    mv.addObject("lendPeriodList", LendManagerCondition.getLendPeriod());
    mv.addObject("hasCreditList", LendManagerCondition.getHasCredit());
    return mv;
  }

  @RequestMapping(value={"/lendStatusUpdate.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String lendStatusUpdate(HttpServletRequest request, HttpServletResponse response, LendModel lendModel) throws Exception {
    boolean returnFlag = this.lendServiceI.updateLendStatus(lendModel);
    return "forward:lendManager.json";
  }
}