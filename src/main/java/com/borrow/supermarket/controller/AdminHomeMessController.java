package com.borrow.supermarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.borrow.supermarket.model.ContentModel;
import com.borrow.supermarket.mybatis.PageParameter;
import com.borrow.supermarket.service.ContentService;

@RequestMapping({"/admin/home"})
@Controller
public class AdminHomeMessController
{

  @Autowired
  private ContentService contentServiceI;

  // use
  @RequestMapping({"/homeMessageManager.json"})
  public ModelAndView homeContentManager(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ModelAndView mv = new ModelAndView();
    PageParameter pageView = null;
    ContentModel model = new ContentModel();
    model.setType(1);
    String pageNow = request.getParameter("pageNow");
    if (("".equals(pageNow)) || (pageNow == null))
      pageView = new PageParameter();
    else {
      pageView = new PageParameter(Integer.parseInt(pageNow));
    }
    mv.addObject("homeMessageList", this.contentServiceI.selectContentList(pageView,model));
    mv.addObject("pageView", pageView);
    mv.setViewName("home/homeMessageManager");
    return mv;
  }
  
  // use
  @RequestMapping({"/homeMessageAddPage.json"})
  public ModelAndView homeContentAddPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView("home/homeMessageAdd");
  }
  
  // use modify by mjw
  @RequestMapping(value={"/homeMessageAdd.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String homeContentAdd(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String homeContent) throws Exception {
	if(homeContent != null && !homeContent.equals("")){
		ContentModel model = new ContentModel();
		model.setContent(homeContent);
		model.setType(1);
		boolean returnFlag = this.contentServiceI.saveContent(model);
		if (returnFlag) {
		      return "forward:homeMessageManager.json";
		    }
	}
    return "redirect:homeMessageAddPage.json";
  }
}