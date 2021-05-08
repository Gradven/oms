<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>查看管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate({
			submitHandler : function(form) {
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer : "#messageBox",
			errorPlacement : function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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
		<li><a href="${ctx}/goods/goodsCategory/">查看列表</a></li>
		<li class="active"><a
			href="${ctx}/goods/goodsCategory/form?id=${goodsCategory.id}&parent.id=${goodsCategoryparent.id}">查看<shiro:hasPermission
					name="goods:goodsCategory:edit">${not empty goodsCategory.id?'修改':'添加'}</shiro:hasPermission> <shiro:lacksPermission
					name="goods:goodsCategory:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="goodsCategory" action="${ctx}/goods/goodsCategory/save" method="post"
		enctype="multipart/form-data" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">上级父节点:</label>
			<div class="controls">
				<sys:treeselect id="parent" name="parent.id" value="${goodsCategory.parent.id}" labelName="parent.name"
					labelValue="${goodsCategory.parent.name}" title="父节点" url="/goods/goodsCategory/treeData"
					extId="${goodsCategory.id}" cssClass="" allowClear="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类编码：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="16" class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类图片：</label>
			<div class="controls">
				<img id="picUrl" name="picUrl"
					src="<c:if test='${not empty goodsCategory.picUrl}'>${goodsCategory.picUrl}</c:if><c:if test='${empty goodsCategory.picUrl}'>https://photo.16pic.com/00/21/44/16pic_2144587_b.jpg</c:if>"
					style="width: 64px; height: 64px" onClick="selectFile()" /> <input type="file" name="picFile" id="picFile"
					onchange="previewHandle(this, 'picUrl')" style="filter: alpha(opacity = 0); opacity: 0; width: 0; height: 0;" />
				<script type='text/javascript'>
					function selectFile() {
						$("#picFile").trigger("click");
					}

					function previewHandle(fileDOM, targetImg) {
						var file = fileDOM.files[0], // 获取文件
						imageType = /^image\//, reader = '';

						// 文件是否为图片
						if (!imageType.test(file.type)) {
							alert("请选择图片！");
							return;
						}
						// 判断是否支持FileReader    
						if (window.FileReader) {
							reader = new FileReader();
						}
						// IE9及以下不支持FileReader
						else {
							alert("您的浏览器不支持图片预览功能，如需该功能请升级您的浏览器！");
							return;
						}

						// 读取完成    
						reader.onload = function(event) {
							// 获取图片DOM
							var img = document.getElementById(targetImg);
							// 图片路径设置为读取的图片    
							img.src = event.target.result;
						};
						reader.readAsDataURL(file);
					}
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge " />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="goods:goodsCategory:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>
