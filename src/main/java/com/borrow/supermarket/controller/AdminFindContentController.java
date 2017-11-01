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

@RequestMapping({"/admin/find"})
@Controller
public class AdminFindContentController
{

  @Autowired
  private ContentService contentServiceI;

  // use
  @RequestMapping({"/findContentManager.json"})
  public ModelAndView findContentManager(HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    ModelAndView mv = new ModelAndView();
    PageParameter pageView = null;
    ContentModel model = new ContentModel();
    model.setType(2);
    String pageNow = request.getParameter("pageNow");
    if (("".equals(pageNow)) || (pageNow == null))
      pageView = new PageParameter();
    else {
      pageView = new PageParameter(Integer.parseInt(pageNow));
    }
    mv.addObject("findContentList", this.contentServiceI.selectContentList(pageView, model));
    mv.addObject("pageView", pageView);
    mv.setViewName("find/findContentManager");
    return mv;
  }
  
  // use
  @RequestMapping({"/findContentAddPage.json"})
  public ModelAndView findContentAddPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    return new ModelAndView("find/findContentAdd");
  }
  
  // use modify by mjw
  @RequestMapping(value={"/findContentAdd.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String findContentAdd(MultipartFile file, HttpServletRequest request, HttpServletResponse response, String findContent) throws Exception {
	if(findContent != null && !findContent.equals("")){
		ContentModel model = new ContentModel();
		model.setContent(findContent);
		model.setType(2);
		boolean returnFlag = this.contentServiceI.saveContent(model);
		if (returnFlag) {
		      return "forward:findContentManager.json";
		    }
	}
    return "redirect:findContentAddPage.json";
  }
}