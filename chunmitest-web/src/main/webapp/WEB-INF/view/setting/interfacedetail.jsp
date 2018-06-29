<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>接口参数详情</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">body{background:none;}</style>
  </head>
  <body>
       <div class="mb20 container-fluid">
              <div class="row">
                <div class="col-sm-8">
                  <p>接口名称：${interfaceDetail.interfaceName }</p>
                </div>
                <div class="col-sm-8">
                  <p>URL：${interfaceDetail.requestUrl }</p>
                </div>
                <div class="col-sm-8">
                  <p>请求方式：
                  	<c:if test="${interfaceDetail.requestMode eq '0' }">
                  		GET
                  	</c:if>
                  	<c:if test="${interfaceDetail.requestMode eq '1'} }">
                  		POST
                  	</c:if>
                  </p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-8">
                  <p>token: ${interfaceDetail.token }	</p>
                </div>
                <div class="col-sm-8">
                  <p>sign: ${interfaceDetail.sign }</p>
                </div>
                <div class="col-sm-8">
                  <p>请求参数：${interfaceDetail.requestParams }</p>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-8">
                  <p>并发数：${interfaceDetail.concurrentNum }</p>
                </div>
                <div class="col-sm-8">
                  <p>期望结果：${interfaceDetail.expectedResult }</p>
                </div>
                <div class="col-sm-8">
                  <p>测试用例：${interfaceDetail.testCaseDes }</p>
                </div>
              </div>
      	</div>
  </body>
    <script src="<%=basePath %>js/jquery.min.js"></script>
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=basePath %>js/language/bootstrap-datetimepicker.zh-CN.js"></script>
</html>
          