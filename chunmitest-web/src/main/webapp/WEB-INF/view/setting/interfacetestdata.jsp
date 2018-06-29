<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.btnGroup{width:100%;}
.btnGroup>*{width:100%;display:block;float:none;}
.btnGroup>*:first-child{margin-bottom: 4px;}
tbody>tr>td{word-break:break-all;}
</style>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-body" style="margin-top: 15px;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th width="40%">期望结果</th>
							<th width="40%">测试结果</th>
							<th width="10%">比较结果</th>
							<th width="10%">校验JSON</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${fn:length(testRecordList)==0 }">
							<tr>
								<td colspan="4" align="center"><strong>暂无可用数据</strong></td>
							</tr>
						</c:if>
						<c:forEach items="${testRecordList }" var="testRecord">
							<tr>
								<td width="40%">${testRecord.expectedResult }</td>
								<td width="40%">${testRecord.testResult }</td>
								<c:if test="${testRecord.compareResult eq '0'}">
									<td width="10%"><p style="color: red">不一致</p></td>
								</c:if>
								<c:if test="${testRecord.compareResult eq '1' }">
									<td width="10%"><p style="color: green;">一致</p></td>
								</c:if>
								<td width="10%"><button type="button" class="btn bg-green btn-flat" onclick="javascript:window.open('http://www.bejson.com/')">点击校验</button></td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<!-- ********************** -->
<script type="text/javascript">
 
$(function () { $("[data-toggle='tooltip']").tooltip(); });
	//点击保存
	function saveRecord(compareResult,recordId){
		if(compareResult == '0'){
			    layer.confirm('当前测试结果与预期结果不一致,确认保存？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					//检查result表中的记录
					checkTestResultRecord(recordId);
				});
		}else{
			checkTestResultRecord(recordId);
		}
	}
	
	//检查result表中是否存在该记录
	function checkTestResultRecord(recordId){
		$.get('checkTestResultRecord.do?recordId='+recordId,function(data){
			if(data==0){
				//没有记录则执行保存
				executeSaveTestRecord(recordId);
			}else{
				layer.msg('该记录已存在',{time:1000});
			}
		});
	}
	
	//执行保存测试记录
	function executeSaveTestRecord(recordId){
		$.get('saveTestRecord.do?recordId='+recordId,function(data){
			if(data==1){
				layer.msg('保存成功',{time:1000});
			}else{
				layer.msg('保存失败',{time:1000});
			}
		});
	}
	
 
</script>
