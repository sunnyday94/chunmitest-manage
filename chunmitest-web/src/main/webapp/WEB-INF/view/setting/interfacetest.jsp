
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div>
	<legend class="content-header">
		<h1>接口测试</h1>
		<ol class="breadcrumb text-right">
			<li><a href="#"><i class="fa fa-dashboard"></i> 当前位置</a></li>
			<li><a href="#">配置</a></li>
			<li class="active">接口测试</li>
		</ol>
	</legend>
	<section class="content">
		<div class="callout callout-info">
			<h4>注意</h4>
			<ul>
				<li>URL和期望结果为必填项，其他为选填项</li>
				<li>请求参数为json格式的字符串</li>
				<li>比较结果:是期望结果和测试结果作对比</li>
			</ul>
		</div>
		<div class="filter_area">
			<form id="addParamsConfigForm" method="post" action="<%=basePath %>setting/recordsearch.do" role="form" class="form-inline">
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon">接口名称</span> 
						<input type="text" class="form-control" id="interfaceName" name="interfaceName" placeholder="输入接口名称" value ="${setting.interfaceName }">
					</div>
				</div>
				<div class="form-group" style="margin-left: 10px;">
					<div class="input-group">
					    <span class="input-group-addon">URL</span> 
						<input type="text" class="form-control" id="requestUrl" name="requestUrl" placeholder="输入URL" value ="${setting.requestUrl }"> 
					</div>
				</div>
				<div class="form-group" style="margin-left: 10px;">
					<div class="input-group">
						<span class="input-group-addon">请求方式</span> 
						<select class="form-control"  name="requestMode" id="requestMode">
							<option value="0" <c:if test="${not empty setting.requestMode && setting.requestMode==0 }"> selected="selected"</c:if>>GET</option>
							<option value="1" <c:if test="${not empty setting.requestMode && setting.requestMode==1 }"> selected="selected"</c:if>>POST</option>
						</select>
					</div>
				</div>
				<div class="form-group" style="margin-left: 10px;">
					<div class="input-group">
						<span class="input-group-addon">请求参数</span> 
						<input type="text" class="form-control" id="requestParams" name="requestParams" placeholder="输入请求参数" value ="${setting.requestParams}">
					</div>
				</div>
				<br/>
				<div class="form-group" style="margin-top: 10px;">
					<div class="input-group">
						<span class="input-group-addon">Token</span> 
						<input type="text" class="form-control" id="token" name="token" placeholder="输入Token" value ="${setting.token }">
					</div>
				</div>
				<div class="form-group" style="margin-left: 10px;margin-top: 10px;">
					<div class="input-group">
						<span class="input-group-addon">是否验签</span> 
						<select class="form-control "  name="isNeedSign" id="isNeedSign">
							<option value="0" <c:if test="${not empty setting.isNeedSign && setting.isNeedSign==0 }"> selected="selected"</c:if>>否</option>
							<option value="1" <c:if test="${not empty setting.isNeedSign && setting.isNeedSign==1 }"> selected="selected"</c:if>>是</option>
						</select>
					</div>
				</div>
				<div class="form-group" style="margin-left: 10px;margin-top: 10px;">
					<div class="input-group">
						<span class="input-group-addon">sign生成规则</span> 
						<select class="form-control "  name="signType" id="signType">
							<option value="0" <c:if test="${not empty setting.signType && setting.signType==0 }"> selected="selected"</c:if>>默认</option>
						</select>
					</div>
				</div>
				<%-- 
				<div class="form-group" style="margin-top: 10px;margin-left: 10px;">
					<div class="input-group">
						<span class="input-group-addon">并发数</span> 
						<input type="text" class="form-control" id="concurrentNum" name="concurrentNum" placeholder="输入并发数" value ="${setting.concurrentNum }">
					</div>
				</div>
				--%>
				<div class="form-group" style="margin-top: 10px;margin-left: 10px;">
					<div class="input-group">
						<span class="input-group-addon">期望结果</span> 
						<input type="text" class="form-control" id="expectedResult" name="expectedResult" placeholder="输入期望结果" value ="${setting.expectedResult }">
					</div>
				</div>
				<%-- 
				<div class="form-group" style="margin-top: 10px;margin-left: 10px;">
					<div class="input-group">
						<span class="input-group-addon">用例描述</span> 
						<input type="text" class="form-control" id="testCaseDes" name="testCaseDes" placeholder="输入用例描述" value ="${setting.testCaseDes }">
					</div>
				</div>
				--%>
				<div class="box-header with-border" id="addParamsConfigDiv">
					<button type="button" class="btn btn-primary" data-toggle="modal" style="margin-left: -10px;"
					 onclick="executeTest();">
						生成测试结果
					</button>
				</div>
				<input id="mark" name="mark" type="hidden" value="${mark }"/>
			</form>
		</div>
		<div id="listdata" class="dataTables_wrapper form-inline dt-bootstrap"></div>
	</section>
</div>
<script type="text/javascript">
	$(function(){
		siderBar("requestParamsConfig");
		loadingPage();
	});
	
	function loadingPage(){
		$("#addParamsConfigForm").ajaxSubmit(function(data) {
			layer.closeAll();
			$("#listdata").html(data);
		});		
	}
	
	
	
	//检查输入的配置信息(url和并发数)
	function checkConfig(url,concurrentNum,expectedResult){
		var regUrl = /^((ht|f)tps?):\/\/[\w\-]+(\.[\w\-]+)+([\w\-\.,@?^=%&:\/~\+#]*[\w\-\@?^=%&\/~\+#])?$/;
		var regConcurrentNum = /^\+?[1-9]\d*$/;
		if(url == null || url == ''){
			layer.msg("URL不能为空", {time : 1000});
			return;			
		}else if(expectedResult==null || expectedResult == ''){
			layer.msg("期望结果不能为空",{time : 1000});
			return;
		}else if(url!=null && url!=''){
			if(!regUrl.test(url)){
				 layer.msg("这网址不是以http://https://开头，或者不是网址！",{time:1000});
				 return;
			}else if(concurrentNum !=null && concurrentNum !=''){
				if(!regConcurrentNum.test(concurrentNum)){
					layer.msg("并发数只能输入正整数!",{time:1000});
					return;
				}else{
					layer.load(0);
					loadingPage();
				}				
			}else {
				layer.load(0); //可选范围(0-9)0表示等返回结果后关闭，1-9表示自动关闭时间(s)
				loadingPage();
			}
		}
	}
	
	
	//生成测试结果
	function executeTest(){
		var url = $('#requestUrl').val();     // url
		var concurrentNum = $('#concurrentNum').val();   // 并发数 
		var expectedResult = $('#expectedResult').val();  //期望结果
		$('#mark').val("1");
		//测试前验证配置参数
		checkConfig(url,concurrentNum,expectedResult);
	}
	
</script>
