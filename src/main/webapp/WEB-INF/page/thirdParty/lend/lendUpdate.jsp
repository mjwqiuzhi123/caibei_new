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
    <li><a href="#">借贷平台信息修改</a></li>
    </ul>
    </div>
    <form action="${pageContext.servletContext.contextPath}/admin/lend/lendUpdate.json" method="post" enctype="multipart/form-data">
	    <div class="formbody">
	    <div class="formtitle"><span>基本信息</span></div>
	    <ul class="forminfo">
	    <li><label>平台名称</label>
	    <input  name="lendName" type="text" class="dfinput" value="${lendModel.lendName}"/>
	    <li><label>平台图标</label><input type="file" name="file" value="${lendModel.lendPicUrl}"/>
	    <img src="${lendModel.lendPicUrl}" alt="" />
	    <input type="hidden" value="${lendModel.lendPicUrl}" name="lendPicUrl"/>
	    </li>
	    <input type="hidden" name="identifier" value="${lendModel.identifier}"/>
	    </li>
<%-- 	    <li><label>平台图标</label><input type="text"  name="lendPicUrl" value="${lendModel.lendPicUrl}"/></li> --%>
	    <li><label>平台URL地址</label><input name="lendUrl" type="text"  class="dfinput" value="${lendModel.lendUrl}"/></li>
	    <li><label>平台性质</label>
		    <cite>
		    <c:forEach items="${platformNatureList}" var="plat">
		     <input name="platformNature"  <c:if test="${plat.value eq lendModel.platformNature}">checked</c:if> type="radio" value="${plat.value}"/>${plat.des}&nbsp;&nbsp;&nbsp;&nbsp;
		    </c:forEach>
		    </cite>
	    </li>
	    <li><label>上线时间</label>
		    <cite>
		       <c:forEach items="${lendOnlineTimeList}" var="onLine">
		       <input name="lendOnlineTime"  type="radio" value="${onLine.value}" <c:if test="${onLine.value eq lendModel.lendOnlineTime}">checked</c:if> />${onLine.des}&nbsp;&nbsp;&nbsp;&nbsp;
                </c:forEach>
		    </cite>
	    </li>
	    <li><label>信用情况</label>
		    <cite>
		     <c:forEach items="${creditStandingList}" var="credit">
		       <input name="creditStanding"  type="radio" value="${credit.value}" <c:if test="${credit.value eq lendModel.creditStanding}">checked</c:if> />${credit.des}&nbsp;&nbsp;&nbsp;&nbsp;
             </c:forEach>
		    </cite>
	    </li>
	    <li><label>有无信用卡</label>
		    <cite>
		     <c:forEach items="${hasCreditList}" var="h">
		       <input name="hasCredit"  type="radio" value="${h.value}" <c:if test="${h.value eq lendModel.hasCredit}">checked</c:if> />${h.des}&nbsp;&nbsp;&nbsp;&nbsp;
             </c:forEach>
		    </cite>
	    </li>
	    <li><label>借款额度</label>
		    <cite>
		     <c:forEach items="${lendMoneyList}" var="money">
		       <input name="lendMoney" type="radio"  value="${money.value}" <c:if test="${money.value eq lendModel.lendMoney}">checked</c:if> />${money.des}&nbsp;&nbsp;&nbsp;&nbsp;
             </c:forEach>
		    </cite>
	    </li>
	    <li><label>借款期限</label>
		    <cite>
		      <c:forEach items="${lendPeriodList}" var="per">
		       <input name="lendPeriod" type="radio"  value="${per.value}" <c:if test="${per.value eq lendModel.lendPeriod}">checked</c:if> />${per.des}&nbsp;&nbsp;&nbsp;&nbsp;
             </c:forEach>
		    </cite>
	    </li>
	    <li><label>是否有活动</label>
		    <cite>
		     <c:forEach items="${hasActivityList}" var="hasActivity">
		       <input name="hasActivity" type="radio"  value="${hasActivity.value}" <c:if test="${hasActivity.value eq lendModel.hasActivity}">checked</c:if> />${hasActivity.des}&nbsp;&nbsp;&nbsp;&nbsp;
             </c:forEach>
		    </cite>
	    </li>
	    <li><label>平台特点</label><input name="lendSpecial"  type="text" class="dfinput"   value="${lendModel.lendSpecial}"/></li>
	    <li><label>月利率</label><input name="monthlyInterestRate"   type="text" class="dfinput"  value="${lendModel.monthlyInterestRate}"/></li>
	    <li><label>放款时间</label><input name="loanTime" type="text"   class="dfinput" value="${lendModel.loanTime}"/></li>
	    <li><label>通过率</label><input name="throughputRate" type="text"  class="dfinput" value="${lendModel.throughputRate}"/></li>
	    <li><label>审核时间【返现】</label><input name="returnCycle" type="text"   class="dfinput" value="${lendModel.returnCycle}"/></li>
	    <li><label>申请条件<b>*</b></label>
	    <textarea name="requirements" cols="" rows=""   class="textinput" >${lendModel.requirements}</textarea>
	    </li>
	    <li><label>认证材料<b>*</b></label>
	       <textarea name="certificationMaterials" cols=""  rows="" class="textinput">${lendModel.certificationMaterials}</textarea>
	    </li>
	    <li><label>申请建议<b>*</b></label>
	       <textarea name="applyForAdvice" cols="" rows=""   class="textinput">${lendModel.applyForAdvice}</textarea>
	    </li>
	     <li><label>逾期惩罚<b>*</b></label>
	       <textarea name="overduePunishment" cols="" rows=""   class="textinput">${lendModel.overduePunishment}</textarea>
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