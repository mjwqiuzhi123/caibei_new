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
    <li><a href="#">订单管理</a></li>
    <li><a href="#">借款订单管理</a></li>
    <li><a href="#">订单详情查看</a></li>
    </ul>
    </div>
    <form  method="post">
	    <div class="formbody">
	    <div class="formtitle"><span>基本信息</span></div>
	    <ul class="forminfo">
	    <li><label>订单编号</label><input readonly="readonly" name="identifier" type="text" class="dfinput" value="${order.identifier}"/></li>
	    <li><label>用户手机号</label><input type="text" readonly="readonly" name="criditPicUrl" value="${order.userPhone}"/></li>
	    <li><label>第三方平台</label><input name="criditUrl" type="text" readonly="readonly" class="dfinput" value="${order.lendName}"/></li>
	    <li><label>产品类型</label>
	        <c:if test="${order.type eq 1}">现金贷</c:if> 
		    <c:if test="${order.type eq 2}">信誉贷</c:if>
		    <c:if test="${order.type eq 3}">车贷</c:if>
		    <c:if test="${order.type eq 4}">房贷</c:if> 
	    </li>
	    
	    <li><label>参与返现</label>
	         <c:if test="${order.cashback eq 1}">未参与</c:if> 
		     <c:if test="${order.cashback eq 2}"><span style="font-weight: bold;color: red;">已参与</span></c:if> 
		     <c:if test="${order.cashback eq 4}">审核未通过</c:if>
		     <c:if test="${order.cashback eq 3}">审核通过</c:if>
	    </li>
	    <c:if test="${order.cashback eq 2 or order.cashback eq 3 or order.cashback eq 4}">
	     <li><label>返现充值手机号</label>
	      <input readonly="readonly" name="phone" type="text" class="dfinput" value="${order.phone}"/>
	     </li>
	      <li><label>订单截图</label></li>
	           <li>
		           <img src="${order.orderPictureOne}" alt="" /><br /><hr />
		           <img src="${order.orderPictureTwo}" alt="" /><br /><hr />
		           <img src="${order.orderPictureThree}" alt="" /><br /><hr />
	           </li>
	    </c:if> 
	    <li><label>&nbsp;</label>
	        <input name="" onclick="javascript:history.go(-1)" type="button" class="btn" value="返回"/>
	        <input name="" onclick="javascript:window.location.href='${pageContext.servletContext.contextPath}/admin/order/orderStatusUpdate.json?id=${order.id}&cashback=3'" type="button" class="btn" value="审核通过"/>
	        <input name="" onclick="javascript:window.location.href='${pageContext.servletContext.contextPath}/admin/order/orderStatusUpdate.json?id=${order.id}&cashback=4'" type="button" class="btn" value="审核不通过"/>
	    </li>
	    </ul>
	    </div>
    </form>
</body>
</html>