<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品信息管理</title>
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
            // $('input[id=coverImgFile]').change(function() {
            //     var f = document.getElementById("coverImgFile").files;
            //     $('#beautyFile').val(f[0].name);
            // });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/goods/goodsInfo/">商品信息列表</a></li>
		<li class="active"><a href="${ctx}/goods/goodsInfo/form?id=${goodsInfo.id}">商品信息<shiro:hasPermission name="goods:goodsInfo:edit">${not empty goodsInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="goods:goodsInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="goodsInfo" action="${ctx}/goods/goodsInfo/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">商品SN：</label>
			<div class="controls">
				<form:input path="sn" htmlEscape="false" maxlength="128" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类：</label>
			<div class="controls">
                <sys:treeselect id="categoryId" name="categoryId" value="${goodsInfo.categoryId}" labelName="categoryName" labelValue="${goodsInfo.categoryName}"
                                title="商品分类" url="/goods/goodsCategory/treeData"  cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">图片：</label>
			<img style="width:80px;height:80px;margin-left:20px" src=${goodsInfo.cover} >

			<%--<c:forEach items="${goodsInfo.picList}" var="pic" varStatus="i">--%>
				<%--<img style="width:80px;height:80px;margin-left:20px" src=${pic} >--%>
			<%--</c:forEach>--%>
			<%--<div class="controls">--%>
				<%--<input id="coverImgFile" name="coverImgFile" type="file" style="display:none" value="${imageUri}"/>--%>
				<%--<div class="input-append">--%>
					<%--<form:input path="picUrls" htmlEscape="false" maxlength="128" id="beautyFile" class="input-xlarge"/>--%>
					<%--<a class="btn" onclick="$('input[id=coverImgFile]').click();">图片上传</a>--%>
				<%--</div>--%>
				<%--<c:if test="${not empty goodsInfo.picUrls}">--%>
					<%--<a class="btn" target="_blank" href="${goodsInfo.picUrls}" style="margin-left:20px">查看</a>--%>
				<%--</c:if>--%>
			<%--</div>--%>
		</div>
		<div class="control-group">
			<label class="control-label">供应商编号：</label>
			<div class="controls">
				<form:input path="supplierId" htmlEscape="false" maxlength="11" class="input-xlarge  digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-xlarge required " readonly="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品关键字：</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售量：</label>
			<div class="controls">
				<form:input path="salesVolume" htmlEscape="false" maxlength="11" class="input-xlarge  digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">零售价：</label>
			<div class="controls">
				<form:input path="retailPrice" htmlEscape="false" class="input-xlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*最大利润的产品的零售价</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">总库存：</label>
			<div class="controls">
				<form:input path="storeNumber" htmlEscape="false" maxlength="8" class="input-xlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*各规格产品的库存总和</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单价：</label>
			<div class="controls">
				<form:input path="unitPrice" htmlEscape="false" class="input-xlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*最大利润的产品的出厂价</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品单位：</label>
			<div class="controls">
				<form:input path="unit" htmlEscape="false" maxlength="24" class="input-xlarge " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">利润：</label>
			<div class="controls">
				<form:input path="profit" htmlEscape="false" class="input-xlarge required" readonly="true"/>
				<span class="help-inline"><font color="red">*取该商品下，各产品利润的最大值</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否上架：</label>
			<div class="controls">
				<form:select path="onSaleFlag" class="input-medium ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('on_sale_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品简介：</label>
			<div class="controls">
				<form:input path="shortDescription" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商创建者编号：</label>
			<div class="controls">
				<form:input path="createSuId" htmlEscape="false" maxlength="11" class="input-xlarge  digits" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商修改者编号：</label>
			<div class="controls">
				<form:input path="updateSuId" htmlEscape="false" maxlength="11" class="input-xlarge  digits" readonly="true"/>
			</div>
		</div>
        <div class="control-group">
            <label class="control-label">审核状态：</label>
            <div class="controls">
                <form:select path="approveStatus" class="input-medium required">
                    <form:option value="" label=""/>
                    <form:options items="${fns:getDictList('approve_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font> </span>
            </div>
        </div>
        <c:if test="${not empty goodsInfo.description}">
        <div class="control-group">
            <label class="control-label">介绍详情图文：</label>
            <div class="controls">
                <form:textarea id="description" htmlEscape="true" path="description.content" rows="4" maxlength="4000" class="input-xxlarge" />
                <sys:ckeditor replace="description" uploadPath="goods/description" />
				<form:hidden path="description.id"/>
            </div>
        </div>
        </c:if>
		<div class="form-actions">
			<shiro:hasPermission name="goods:goodsInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
