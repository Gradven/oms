<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格维度信息管理</title>
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
		<li class="active"><a href="${ctx}/specification/specificationInfo/">规格维度信息列表</a></li>
		<shiro:hasPermission name="specification:specificationInfo:edit"><li><a href="${ctx}/specification/specificationInfo/form">规格维度信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="specificationInfo" action="${ctx}/specification/specificationInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label style="width: 100px">规格维度名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>规格维度名称</th>
				<th>排序</th>
				<shiro:hasPermission name="specification:specificationInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="specificationInfo">
			<tr>
				<td><a href="${ctx}/specification/specificationInfo/form?id=${specificationInfo.id}">
					${specificationInfo.id}
				</a></td>
				<td>
					${specificationInfo.name}
				</td>
				<td>
					${specificationInfo.sortOrder}
				</td>
				<shiro:hasPermission name="specification:specificationInfo:edit"><td>
    				<a href="${ctx}/specification/specificationInfo/form?id=${specificationInfo.id}">修改</a>
					<a href="${ctx}/specification/specificationInfo/delete?id=${specificationInfo.id}" onclick="return confirmx('确认要删除该规格维度信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>