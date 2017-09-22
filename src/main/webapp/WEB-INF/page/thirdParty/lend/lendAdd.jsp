<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%-- ${pageContext.request.contextPath} --%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/admin/css/select.css" rel="stylesheet" type="text/css" />
<style  type="text/css">
#thdiv th{text-align: center;} 
#divtr td{text-align: center;}
</style>
<script language="JavaScript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/cloud.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/js/select-ui.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/admin/editor/kindeditor.js"></script> --%>
<!-- 富文本编译器【开始】 -->
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/admin/kindeditor-4.1.10/themes/default/default.css" />
<script src="${pageContext.servletContext.contextPath}/admin/kindeditor-4.1.10/kindeditor.js"></script>
<script src="${pageContext.servletContext.contextPath}/admin/kindeditor-4.1.10/lang/zh_CN.js"></script>
<!-- 富文本便编译器【结束】 -->
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">平台管理</a></li>
    <li><a href="#">借贷管理</a></li>
    <li><a href="#">借贷平台录入</a></li>
    </ul>
    </div>
    <form action="${pageContext.servletContext.contextPath}/admin/lend/lendAdd.json" method="post" enctype="multipart/form-data">
	    <div class="formbody">
	    <div class="formtitle"><span>基本信息</span></div>
	    <ul class="forminfo">
	    <li><label>平台名称</label><input name="lendName" type="text" class="dfinput" /></li>
	    <li><label>平台图标</label><input type="file" name="file"/></li>
	    <li><label>平台URL地址</label><input name="lendUrl" type="text" class="dfinput" /></li>
	    <li><label>平台性质</label>
		    <cite>
		       <input name="platformNature" type="radio" value="1" checked="checked" />现金贷&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="platformNature" type="radio" value="2" />信誉带&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="platformNature" type="radio" value="3" />房贷&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="platformNature" type="radio" value="4" />车贷&nbsp;&nbsp;&nbsp;&nbsp;
		    </cite>
	    </li>
	    <li><label>上线时间</label>
		    <cite>
		       <input name="lendOnlineTime" type="radio" value="1" checked="checked" />3个月&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendOnlineTime" type="radio" value="2" />6个月&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendOnlineTime" type="radio" value="3" />1年&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendOnlineTime" type="radio" value="4" />2年&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendOnlineTime" type="radio" value="5" />3年以上&nbsp;&nbsp;&nbsp;&nbsp;
		    </cite>
	    </li>
	    <li><label>信用情况</label>
		    <cite>
		       <input name="creditStanding" type="radio" value="1" checked="checked" />不限&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="creditStanding" type="radio" value="2" />无逾期&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="creditStanding" type="radio" value="3" />1年逾期小于3次&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="creditStanding" type="radio" value="4" />1年逾期超过3次&nbsp;&nbsp;&nbsp;&nbsp;
		    </cite>
	    </li>
	    <li><label>有无信用卡</label>
		    <cite>
		       <input name="hasCredit" type="radio" value="1" checked="checked" />有信用卡&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="hasCredit" type="radio" value="2" />无信用卡&nbsp;&nbsp;&nbsp;&nbsp;
		    </cite>
	    </li>
	    <li><label>借款额度</label>
		    <cite>
		       <input name="lendMoney" type="radio" value="1" checked="checked" />500-1000&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendMoney" type="radio" value="2" />1000-2000&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendMoney" type="radio" value="3" />2000-3000&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendMoney" type="radio" value="4" />3000-5000&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendMoney" type="radio" value="5" />5000-8000&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendMoney" type="radio" value="6" />8000-10000&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendMoney" type="radio" value="7" />10000以上&nbsp;&nbsp;&nbsp;&nbsp;
		    </cite>
	    </li>
	    <li><label>借款期限</label>
		    <cite>
		       <input name="lendPeriod" type="radio" value="1" checked="checked" />7-14天&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendPeriod" type="radio" value="2" />14-21天&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendPeriod" type="radio" value="3" />21-28天&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendPeriod" type="radio" value="4" />30天&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="lendPeriod" type="radio" value="5" />30天以上&nbsp;&nbsp;&nbsp;&nbsp;
		    </cite>
	    </li>
	    <li><label>是否有活动</label>
		    <cite>
		       <input name="hasActivity" type="radio" value="1" checked="checked" />活动进行中&nbsp;&nbsp;&nbsp;&nbsp;
		       <input name="hasActivity" type="radio" value="2" />无任何活动&nbsp;&nbsp;&nbsp;&nbsp;
		    </cite>
	    </li>
	    <li><label>平台特点</label><input name="lendSpecial" type="text" class="dfinput" value="" /></li>
	    <li><label>月利率</label><input name="monthlyInterestRate" type="text" class="dfinput" value="" /></li>
	    <li><label>放款时间</label><input name="loanTime" type="text" class="dfinput" /></li>
	    <li><label>通过率</label><input name="throughputRate" type="text" class="dfinput" /></li>
	    <li><label>审核时间【返现】</label><input name="returnCycle" type="text" class="dfinput" /></li>
	    <li><label>申请条件<b>*</b></label>
	    <textarea name="requirements" cols="" rows="" class="textinput"></textarea>
	    </li>
	    <li><label>认证材料<b>*</b></label>
	       <textarea name="certificationMaterials" cols="" rows="" class="textinput"></textarea>
	    </li>
	    <li><label>申请建议<b>*</b></label>
	       <textarea name="applyForAdvice" cols="" rows="" class="textinput"></textarea>
	    </li>
	     <li><label>逾期惩罚<b>*</b></label>
	       <textarea name="overduePunishment" cols="" rows="" class="textinput"></textarea>
	    </li>
	     <li>
	         <label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/>
	         <label>&nbsp;</label><input name="" onclick="javascript:history.go(-1)" type="button" class="btn" value="返回"/>
	         </li>
	    </ul>
	    </div>
    </form>
</body>
</html>