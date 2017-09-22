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
<title>异常404页面</title>
<link href="${pageContext.request.contextPath}/admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/admin/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/admin/js/cloud.js" type="text/javascript"></script>
<script type="text/javascript"> 
if (top.location !== self.location) { 
    top.location=self.location; 
} 
</script>
<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 
<script language="javaScript">
	$(document).ready(function(){  
		 $(function(){  
		   $('input:reset').click(function(){  
		     $('.input').val("");  
		    });  
		  });  
		  }); 
</script>
</head>

   <body style="background-color:#1c77ac; background-image:url(${pageContext.request.contextPath}/admin/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

   <div>
     <center><span style="font-size: 60px;font-weight: bold;color: red;padding-top: 20%;">404</span></center>
   </div>  
   <div class="loginbm">版权所有  2017  <a href="http://www.uimaker.com">http://www.icaibei.com</a> 采贝贷款超市平台业务系统</div>
</body>

</html>