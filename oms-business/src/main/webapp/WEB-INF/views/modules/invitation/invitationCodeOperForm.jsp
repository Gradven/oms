<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>运营申请邀请码管理</title>
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
		<li><a href="${ctx}/invitation/invitationCodeOper/">运营申请邀请码列表</a></li>
		<li class="active"><a href="${ctx}/invitation/invitationCodeOper/form?id=${invitationCodeOper.id}">运营申请邀请码<shiro:hasPermission name="invitation:invitationCodeOper:edit">${not empty invitationCodeOper.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="invitation:invitationCodeOper:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="invitationCodeOper" action="${ctx}/invitation/invitationCodeOper/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">申请邀请码个数：</label>
			<div class="controls">
				<form:input path="codeNumber" htmlEscape="false" maxlength="10" class="input-large  digits"/>  一次最多申请10个
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="invitation:invitationCodeOper:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
