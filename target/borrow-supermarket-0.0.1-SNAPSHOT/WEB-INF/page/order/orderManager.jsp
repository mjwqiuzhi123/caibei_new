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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/editor/kindeditor.js"></script>
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
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
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
<script type="text/javascript">
function safeAdd(){

// alert("${pageContext.request.contextPath}/lend/lendAddPage.json");
window.location.href = "${pageContext.request.contextPath}/admin/safe/safeAddPage.json";
}
</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">订单管理</a></li>
    <li><a href="#">借贷订单列表</a></li>
    </ul>
    </div>
    <form action="${pageContext.request.contextPath}/admin/order/orderManager.json" method="post"  id="fenye" name="fenye">
    <div class="rightinfo">
    <div class="tools">
    <ul class="seachform">
    <li><label>订单编号</label><input name="orderIdentifier" type="text" value="${orderModel.orderIdentifier}" class="scinput"></li>
    <li><label>手机号</label><input name="orderPhone" type="text" value="${orderModel.orderPhone}" class="scinput"></li>
    <li><label>返现状态</label>  
    <div class="vocation">
    <select class="select3" style="width: 100px;" name="cashback">
    <option value="0">---全部---</option>
    <c:forEach items="${cashbackList}" var="cash">
       <option value="${cash.value}" <c:if test="${cash.value eq orderModel.cashback}">selected</c:if>>${cash.des}</option>
     </c:forEach>
    </select>
    </div>
    </li>
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="订单查询"></li>
    </ul>
    </div>
    
    
    <table class="imgtable">
    
    <thead>
    <tr id="thdiv">
    <th>订单编号</th>
    <th>用户</th>
    <th>第三方平台标识</th>
    <th>产品类型</th>
    <th>返现</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${orderList}">
	    <tr id="divtr" style="height: 35px;">
		    <td>${order.identifier}</td>
		    <td>${order.userPhone}</td>
		    <td>${order.lendName}</td>
		    <td>
		    <c:if test="${order.type eq 1}">现金贷</c:if> 
		    <c:if test="${order.type eq 2}">信誉贷</c:if>
		    <c:if test="${order.type eq 3}">车贷</c:if>
		    <c:if test="${order.type eq 4}">房贷</c:if> 
		    </td>
		    <td>
		     <c:if test="${order.cashback eq 1}">未参与</c:if> 
		     <c:if test="${order.cashback eq 2}"><span style="font-weight: bold;color: red;">已参与</span></c:if> 
		     <c:if test="${order.cashback eq 4}">审核未通过</c:if>
		     <c:if test="${order.cashback eq 3}">审核通过</c:if>
		    </td>
		    <td>
		             <a href="${pageContext.request.contextPath}/admin/order/orderDetails.json?id=${order.id}" class="tablelink">详情</a>
			         <c:if test="${order.cashback eq 2}">
			          <a href="${pageContext.request.contextPath}/admin/order/orderStatusUpdatePage.json?id=${order.id}" class="tablelink">审核</a>
			         </c:if> 
		    </td>
	    </tr>
    </c:forEach>
    </tbody>
    </table>
    <div class="pagin">
    	<div class="message">
    	<jsp:include page="../../common/pager/page/jsp/webfenye3.jsp"/>
    	</div>
    </div>
  <div>
  </div>
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${pageContext.request.contextPath}/admin/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
  </form>  
    
    
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${pageContext.request.contextPath}/admin/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
<jsp:include page="../../common/pager/page/jsp/common-js.jsp"/>
<jsp:include page="../../common/pager/page/jsp/common-css.jsp"/>
<script type="text/javascript">
	$('.imgtable tbody tr:odd').addClass('odd');
	</script>
		<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>