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
function lendAdd(){

// alert("${pageContext.request.contextPath}/lend/lendAddPage.json");
window.location.href = "${pageContext.request.contextPath}/admin/lend/lendAddPage.json";
}
</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">平台管理</a></li>
    <li><a href="#">借贷列表</a></li>
    </ul>
    </div>
    <form action="${pageContext.request.contextPath}/admin/lend/lendManager.json" method="post"  id="fenye" name="fenye">
    <div class="rightinfo">
    <div class="tools">
    <ul class="seachform">
    <li><label>平台名称</label><input name="lendName" type="text" value="${lendModel.lendName}" class="scinput"></li>
    <li><label>平台性质</label>  
    <div class="vocation">
    <select class="select3" style="width: 100px;" name="platformNature">
     <option value="0">--全部--</option>
     <c:forEach items="${platformNatureList}" var="platform">
      <option value="${platform.value}" <c:if test="${platform.value eq lendModel.platformNature}">selected</c:if>>${platform.des}</option>
     </c:forEach>
    </select>
    </div>
    </li>
    
    <li><label>额度</label>  
    <div class="vocation">
    <select class="select3" style="width: 100px;" name="lendMoney">
    <option value="0">---全部---</option>
    <c:forEach items="${lendMoneyList}" var="lendMoney">
       <option value="${lendMoney.value}" <c:if test="${lendMoney.value eq lendModel.lendMoney}">selected</c:if>>${lendMoney.des}</option>
     </c:forEach>
    </select>
    </div>
    </li>
    <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询"></li>
    <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="平台添加" onclick="lendAdd()"></li>
    </ul>
    </div>
    
    
    <table class="imgtable">
    
    <thead>
    <tr id="thdiv">
    <th width="100px;">平台缩略图</th>
    <th>平台名称</th>
    <th>平台性质</th>
    <th>平台特点</th>
    <th>额度</th>
    <th>利率</th>
    <th>申请人数</th>
    <th>是否活动</th>
    <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="lendModel" items="${lendList}">
	    <tr id="divtr">
		    <td class="imgtd"><img src="${lendModel.lendPicUrl}" width="50px" height="50px"/></td>
		    <td><a href="#">${lendModel.lendName}</a>
		            <p>发布时间：
		                <fmt:formatDate value="${lendModel.createDate}" pattern="yyyy-MM-dd H:m:s"/>
		             </p>
		    </td>
		    <td>
		      <c:if test="${lendModel.platformNature eq 1}">
		                现金贷
		      </c:if>
		       <c:if test="${lendModel.platformNature eq 2}">
		                信誉贷
		      </c:if>
		       <c:if test="${lendModel.platformNature eq 3}">
		                房贷
		      </c:if>
		       <c:if test="${lendModel.platformNature eq 4}">
		               车贷
		      </c:if>
		     
		    </td>
		    <td>${lendModel.lendSpecial}</td>
		    <td>
		     <c:if test="${lendModel.lendMoney eq 1}">
		     500-1000
		     </c:if>
		     <c:if test="${lendModel.lendMoney eq 2}">
		     1000-2000
		     </c:if>
		     <c:if test="${lendModel.lendMoney eq 3}">
		     2000-3000
		     </c:if>
		     <c:if test="${lendModel.lendMoney eq 4}">
		     3000-5000
		     </c:if>
		     <c:if test="${lendModel.lendMoney eq 5}">
		     5000-8000
		     </c:if>
		     <c:if test="${lendModel.lendMoney eq 6}">
		     8000-10000
		     </c:if>
		     <c:if test="${lendModel.lendMoney eq 7}">
		     10000以上
		     </c:if>
		     </td>
		    <td>${lendModel.monthlyInterestRate}/月</td>
		    <td>${lendModel.totalApply}</td>
		    <td>
		         <c:if test="${lendModel.hasActivity eq 1}">
		          <span style="color: red;font-weight: bold;">
			                     活动进行中
			                     </span>
		        </c:if>
		        <c:if test="${lendModel.hasActivity eq 2}">
			       
			            无         
			        
		        </c:if>
		        </td>
		   <td>
			    <center>
			      <a href="${pageContext.request.contextPath}/admin/lend/lendDetails.json?identifier=${lendModel.identifier}" class="tablelink">详情</a>     
			      <c:if test="${lendModel.status eq 1 or lendModel.status eq 3}">
			      <a href="${pageContext.request.contextPath}/admin/lend/lendStatusUpdate.json?identifier=${lendModel.identifier}&status=2" class="tablelink">上线</a>
			      </c:if>
			      <c:if test="${lendModel.status eq 2}">
			      <a href="${pageContext.request.contextPath}/admin/lend/lendStatusUpdate.json?identifier=${lendModel.identifier}&status=3" class="tablelink">下线</a>
			      </c:if>
			      <c:if test="${lendModel.status eq 1 or lendModel.status eq 3}">
			        <a href="${pageContext.request.contextPath}/admin/lend/lendUpdatePage.json?identifier=${lendModel.identifier}" class="tablelink">编辑</a>
			      </c:if>
			     </center>
		   </td>
	    </tr>
    </c:forEach>
    </tbody>
    </table>
    <div class="pagin">
    	<div class="message">
    	<jsp:include page="../../../common/pager/page/jsp/webfenye3.jsp"/>
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
<jsp:include page="../../../common/pager/page/jsp/common-js.jsp"/>
<jsp:include page="../../../common/pager/page/jsp/common-css.jsp"/>
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