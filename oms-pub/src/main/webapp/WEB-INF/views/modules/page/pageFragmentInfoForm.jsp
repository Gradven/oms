<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>页面区块信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});


	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/page/pageFragmsupplierInfo/">页面区块信息列表</a></li>
		<li class="active"><a href="${ctx}/page/pageFragmsupplierInfo/form?id=${pageFragmsupplierInfo.id}">页面区块信息<shiro:hasPermission name="page:pageFragmsupplierInfo:edit">${not empty pageFragmsupplierInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="page:pageFragmsupplierInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pageFragmsupplierInfo" action="${ctx}/page/pageFragmsupplierInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">页面id：</label>
			<!--  <div class="controls">
				<form:input path="pageId" htmlEscape="false" maxlength="20" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>-->

			<div class="controls">
				<sys:treeselect id="parent" name="pageId" value="${pageFragmsupplierInfo.pageId}" labelName="parent.name" labelValue="${pageFragmsupplierInfo.pageId}"
					title="父节点id" url="/page/pageInfo/treeData" extId="${pageFragmsupplierInfo.pageId}" cssClass="" allowClear="true"/>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">区块KEY：</label>
			<div class="controls">
				<form:input path="fragmentKey" htmlEscape="false" maxlength="50" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区块名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">区块类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('fragment_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">相关区块：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="2000" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>

		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="page:pageFragmsupplierInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
