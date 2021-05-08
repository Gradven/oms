<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图片资源管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/resource/resInfo/">图片资源管理列表</a></li>
		<shiro:hasPermission name="resource:resInfo:edit"><li><a href="${ctx}/resource/resInfo/form">图片资源管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="resInfo" action="${ctx}/resource/resInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>资源编号</th>
				<th>资源地址</th>
				<th>文件类型</th>
				<th>文件标题</th>
				<th>备注</th>
				<shiro:hasPermission name="resource:resInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="resInfo">
			<tr>
				<td><a href="${ctx}/resource/resInfo/form?id=${resInfo.id}">
					${resInfo.id}
				</a></td>
				<td>
					${resInfo.uri}
				</td>
				<td>
					${fns:getDictLabel(resInfo.type, 'resourceType', '')}
				</td>
				<td>
					${resInfo.title}
				</td>
				<td>
					${resInfo.remark}
				</td>
				<shiro:hasPermission name="resource:resInfo:edit"><td>
					<c:if test="${ not empty selectEnable}"> <a>选中</a> </c:if>>
    				<a href="${ctx}/resource/resInfo/form?id=${resInfo.id}">修改</a>
					<a href="${ctx}/resource/resInfo/delete?id=${resInfo.id}" onclick="return confirmx('确认要删除该图片资源管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
