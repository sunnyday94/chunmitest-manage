<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<section class="content-header">
	<h1>接口配置信息</h1>
	<ol class="breadcrumb text-right">
		<li><a href="#"><i class="fa fa-dashboard"></i>当前位置</a></li>
		<li><a href="#">记录</a></li>
		<li class="active">接口配置信息</li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-body">
					<form id="searchForm" method="get"
						action="<%=basePath %>setting/settinglist.do" role="form" class="form-inline">
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon">接口名称</span> <input type="text"
									class="form-control" id="interfaceName" name="interfaceName" value="${testSetting.interfaceName }">
							</div>
						</div>
						
						<div class="form-group" style="margin-left: 5px;">
							<div class="input-group">
								<span class="input-group-addon">请求地址</span> <input type="text"
									class="form-control" id="requestUrl" name="requestUrl" value="${testSetting.requestUrl }">
							</div>
						</div>
						
						<div class="form-group" style="margin-left: 5px;">
							<div class="input-group">
								<span class="input-group-addon">请求方式</span> <select
									class="form-control" name="requestMode">
									<option value="">全部</option>
									<option value="0" <c:if test="${not empty testSetting.requestMode && testSetting.requestMode==0}">selected="selected"</c:if>>get</option>
									<option value="1" <c:if test="${not empty testSetting.requestMode && testSetting.requestMode==1}">selected="selected"</c:if>>post</option>
								</select>
							</div>
						</div>

						<div class="form-group" style="margin-left: 5px;">
							<input type="button" class="btn btn-primary" onclick="allRegressionTesting('all')" 
							<c:if test="${fn:length(pb.list)==0 }">disabled="disabled"</c:if> value="一键回归测试"/>
						</div>
						
						<div class="form-group" style="margin-left: 5px;">
							<input type="submit" class="btn btn-primary" value="搜索" <c:if test="${fn:length(pb.list)==0 }">disabled="disabled"</c:if>/>
						</div>
						
						<div class="form-group" style="margin-left: 5px;">
							<button type="button" class="btn btn-info"
								onclick="delSetting();" <c:if test="${fn:length(pb.list)==0 }">disabled="disabled"</c:if>>
								<i class="glyphicon glyphicon-trash"></i> 删除
							</button >
						</div>
						<input name="pageNo" id="pageNo" value="1" type="hidden">
						<input name="pageSize" id="pageSize" value="10" type="hidden">
					</form>

				</div>
				<div class="box-body table-responsive no-padding">
					<table class="table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox"
									onclick="allunchecked(this,'id');"></th>
								<%-- <th width="5%">ID</th> --%>
								<th width="10%">接口名称</th>
								<th width="30%">请求地址</th>
								<th width="10%">请求方式</th>
								<th width="15%">请求参数</th>
								<th width="35%">期望结果</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${fn:length(pb.list)==0 }">
								<td colspan="7" align="center" style="font-weight: bold;">暂无可用数据</td>
							</c:if>

							<c:forEach items="${pb.list}" var="setting">
								<tr>
									<td><input type="checkbox" name="id" value="${setting.id}" /></td>
									<%-- <td>${setting.id}</td> --%>
									<td>${setting.interfaceName}</td>
									<td>${setting.requestUrl}</td>
									<td><c:if test="${setting.requestMode == '0'}">get</c:if>
										<c:if test="${setting.requestMode == '1'}">post</c:if>
									</td>
									<td>${setting.requestParams}</td>
									<td>
										<div class="btnGroup">
											<p>
												<button type="button" class="btn btn-primary"
													onclick="showSetting(${setting.id});">查看详情</button>
												<button type="button" class="btn btn-primary"
													onclick="editSetting(${setting.id});">编辑</button>
											</p>
											<p>
												<button type="button" class="btn btn-primary"
													onclick="showResult(${setting.id});">查看结果</button>
												<button type="button" class="btn btn-primary"
													onclick="regressionTesting(${setting.id},'single');">回归测试</button>
											</p>
										</div>

									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
				
				<jsp:include page="../page/paging.jsp"></jsp:include>
			</div>
		</div>
	</div>
</section>
	<div class="modal fade" id="showResult" role="dialog">
		<div class="modal-dialog modal-lg" style="margin:30px auto 0;">
			<div class="modal-content">
				<div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title" id="settingTitle">测试结果与回归测试结果列表</h4>
               	</div>
               	<div class="modal-body clearfix" id="readResults">
               		 
               	</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="showSetting" role="dialog">
		<div class="modal-dialog modal-lg" style="margin:30px auto 0;">
			<div class="modal-content">
				<div class="modal-header">
                   <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                   <h4 class="modal-title" id="settingTitle">查看配置详情</h4>
               	</div>
               	<div class="modal-body" id="showSettingBody">
               		 
               	</div>
			</div>
		</div>
	</div>
    
 <script id="resultList" type="text/x-template">
   <table  class=" table-striped table-hover table-bordered pull-left" style="width:49%;word-break:break-all;">
        <tr style="line-height:30px;">
          <td width ="400px"  style="padding-left:8px;">测试结果记录</td>
        </tr>
		{{if data}}
			{{if data.results}}
				{{each data.results result index}}
					<tr  style="line-height:1.4;">
						<td style="padding:8px;">{{result}}</td>
					</tr>
				{{/each}}
			{{/if}}
		{{/if}}
  </table>
   <table  class=" table-striped table-hover table-bordered pull-right" style="width:49%;word-break:break-all;">
        <tr style="line-height:30px;">
          <td width ="400px" style="padding-left:8px;">回归测试记录</td>
        </tr>
		{{if data}}
			{{if data.regressions}}
				{{each data.regressions regression index}}
					<tr  style="line-height:1.4;">
						<td style="padding:8px;">{{regression}}</td>
					</tr>
				{{/each}}
			{{/if}}
		{{/if}}
  </table>

</script>
<script id="showSettingTemplate" type="text/x-template">
	<table class="table table-striped table-hover table-bordered" style="margin-bottom:0px;">
		{{if data }}
				 
					<tr>
						 <td width ="100px">接口名称</td><td width ="300px">{{data.interfaceName }}</td>
						 <td width ="100px">接口地址</td><td width ="300px">{{data.requestUrl }}</td>
					</tr>
					 <tr>
						 <td width ="100px">并发数</td> <td width ="300px">{{data.concurrentNum }}</td>
						 <td width ="100px">请求方式</td>
						 <td width ="300px">{{if data.requestMode == 0 }}get{{/if}} 
							 {{if data.requestMode == 1 }}post{{/if}}
						 </td>
					</tr>
					<tr>
						 <td width ="100px">token</td><td width ="300px">{{data.token }}</td>
						 <td width ="100px">请求参数</td> <td width ="300px">{{data.requestParams }}</td>
					</tr>
					<tr>
						<td width ="100px">测试用例描述</td><td colspan="3"><div style="height:200px;overflow-y:auto;word-break: break-all;">{{data.testCaseDes }}</div></td>
					</tr>
					<tr>
				 		<td width ="100px">期望结果</td> <td colspan="3"><div style="height:200px;overflow-y:auto;word-break: break-all;">{{data.expectedResult }}</div></td>
					</tr>
					<tr>
						<td width ="100px">备注</td><td colspan="3">{{data.Remark}}</td>
					</tr>			  
					 
			 
		{{/if}}
  </table>

</script>
    


<script type="text/javascript">
	$(function(){
		siderBar("settinglist");
	});
	
	function loadingPage(pageNo, pageSize) {
		$("#pageNo").val(pageNo);
		$("#pageSize").val(pageSize);
		$("#searchForm").submit();
	}
	
	
	function delSetting(){
		var ids = new Array();
		$("input[name='id']:checked").each(function(i){
			ids[i]=$(this).val();
			
		})
		console.log(ids.length);
		 if(ids.length == 0){
			 layer.msg('请选择一行', {time: 1000, icon:5});
		}else{
			layer.confirm('确定要删除吗？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					  $.ajax({
						type : "post",
						 url : "delSetting.do?ids="+ids,
					  success: function (data) {
						   layer.msg('删除成功', {icon: 6});
						    location.reload();
						 }
					});  
				  
				}, function(){
				   
				});
		}
		
		
		
		
	}
	
	function showResult(id){
		$("#readResults").html("");
		$.ajax({
			type : "get",
			url : "showResultById.do?id="+id,
		    success: function (data) {
				var html = template('resultList', {
						data : data
					});
	   		      $("#readResults").append(html);
	   		      $("#showResult").modal("show");
			}
		});
		
	}
	
	function editSetting(id){
		 window.location.href = "<%=basePath%>setting/requestParamsConfig.do?id="+id+"&mark=0";
	}
	 
	function showSetting(id){
		$.ajax({
			type : "get",
			url : "showSettingById.do?id="+id,
		    success: function (data) {
				console.log(data);
				console.log("data type is "+typeof(data));
				var html = template('showSettingTemplate', {
						data : data
					});
	   		      $("#showSettingBody").html(html);
	   		      $("#showSetting").modal("show");
			}
		});
		
	}
	
	//回归测试(单个接口)
	function regressionTesting(id,testType){
		layer.load(0);
		$.post('regressionTesting.do',{"id":id,"testType":testType},function(data){
			layer.closeAll();
			if(data==1){
				 layer.msg('回归测试成功!', {time:1000});
			}else{
				layer.msg('回归测试失败!', {time:1000});
			}
		});
	}
	
	
	//一键回归测试(所有接口)
	function allRegressionTesting(testType){
		layer.load(0);
		$.post('allRegressionTesting.do',{"testType":testType},function(msg){
			layer.closeAll();
			if(msg == "1"){
				layer.msg("一键回归测试成功!",{time:1000});
			}else{
				layer.msg("一键回归测试失败!",{time:1000});
			}
		});
	}
</script>
