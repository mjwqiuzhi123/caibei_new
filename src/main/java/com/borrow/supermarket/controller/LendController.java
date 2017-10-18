package com.borrow.supermarket.controller;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.borrow.supermarket.base.BaseController;
import com.borrow.supermarket.mq.MessageSend;
import com.borrow.supermarket.request.dto.LendDetailRequestDTO;
import com.borrow.supermarket.request.dto.LendPageRequestDTO;
import com.borrow.supermarket.response.result.LendDetailDTOResult;
import com.borrow.supermarket.response.result.LendPageDTOResult;
import com.borrow.supermarket.response.result.SendMsgDTOResult;
import com.borrow.supermarket.service.LendService;
import com.borrow.supermarket.util.AliMessageUtil;
import com.borrow.supermarket.util.PageWebDTOResult;
import com.borrow.supermarket.util.ResponseEntity;
import com.borrow.supermarket.util.ServiceCode;

@RequestMapping({"/front/lend"})
@Controller
public class LendController extends BaseController
{
	private static AtomicInteger ai = new AtomicInteger(1);

	@Autowired
	private LendService lendServiceI;

	@Autowired
	private MessageSend messageSend;

	@Autowired
	private AliMessageUtil aliMessageUtil;

	@RequestMapping(value={"/mq.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  @ResponseBody
  public String mq(HttpServletRequest request)
    throws Exception
  {
    try
    {
      SendMsgDTOResult msgEvent = new SendMsgDTOResult(1, "13761826374", "3890");
      AliMessageUtil.sendMsg(msgEvent);
      return null;
    } catch (Exception e) {
      e.printStackTrace();
    }return null;
  }
  @RequestMapping(value={"/lendPage.json"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, produces={"application/json; charset=utf-8"})
  @ResponseBody
  public String lendPage(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute("lendPage") LendPageRequestDTO lendPageRequestDTO, BindingResult bind) {
	 try { 
    	if (bind.hasErrors())
    		return getValidErrors(bind).getAsJSON();
    	PageWebDTOResult<LendPageDTOResult> pageresult = this.lendServiceI.getLendByPage(lendPageRequestDTO);
    	return pageresult.getAsJSON();
    } catch (Exception e) {
    	ResponseEntity messageResult = new ResponseEntity();
    	messageResult.setMsg(ServiceCode.EXCEPTION);
    	return messageResult.getAsJSON();
    }
  }

  @RequestMapping(value={"/lendDetail.json"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json; charset=utf-8"})
  @ResponseBody
  public String lendDetail(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute("lendDetail") LendDetailRequestDTO lendDetailRequestDTO, BindingResult bind) { 
		ResponseEntity messageResult = new ResponseEntity();
		try {
			if (bind.hasErrors()) {
				return getValidErrors(bind).getAsJSON();
			}
			this.lendServiceI.applyCount();
			LendDetailDTOResult LendDetailDTOResult = this.lendServiceI.getLendDetailByIdentifier(lendDetailRequestDTO);
			messageResult.addProperty(LendDetailDTOResult);
			return messageResult.getAsJSON();
		} catch (Exception e) {
			messageResult.setMsg(ServiceCode.EXCEPTION);
		}
		return messageResult.getAsJSON();
  }
}