<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品对应规格表值表管理</title>
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
		<li><a href="${ctx}/goods/goodsSpecification/">商品规格值列表</a></li>
		<li class="active"><a href="${ctx}/goods/goodsSpecification/form?id=${goodsSpecification.id}">商品规格值列表<shiro:hasPermission name="goods:goodsSpecification:edit">${not empty goodsSpecification.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="goods:goodsSpecification:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="goodsSpecification" action="${ctx}/goods/goodsSpecification/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<sys:treeselect id="goodsId" name="goodsId" value="${goodsSpecification.goodsId}" labelName="goodsName" labelValue="${ goodsSpecification.goodsName}"
								title="商品列表" url="/goods/goodsInfo/treeData" extId="${goodsSpecification.goodsId}" cssClass="required" allowClear="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">

				<sys:treeselect id="specificationId" name="specificationId" value="${goodsSpecification.specificationId}" labelName="specificationName" labelValue="${ goodsSpecification.specificationName}"
								title="规格" url="/specification/specificationInfo/treeData" extId="${goodsSpecification.specificationId}" cssClass="required" allowClear="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>


		</div>
		<div class="control-group">
			<label class="control-label">规格值：</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<div class="controls">
				<form:input path="picUrl" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="goods:goodsSpecification:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>