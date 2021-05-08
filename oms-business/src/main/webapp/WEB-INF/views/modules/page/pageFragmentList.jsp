<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>页面区块配置管理</title>
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
		<li class="active"><a href="${ctx}/page/pageFragment/">页面区块配置列表</a></li>
		<shiro:hasPermission name="page:pageFragment:edit"><li><a href="${ctx}/page/pageFragment/form">页面区块配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pageFragment" action="${ctx}/page/pageFragment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>页面id：</label>
				<form:input path="pageId" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>关键字：</label>
				<form:input path="keyword" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>页面id</th>
				<th>关键字key</th>
				<th>区块名</th>
				<th>区块值</th>
				<th>备注</th>
				<shiro:hasPermission name="page:pageFragment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pageFragment">
			<tr>
				<td><a href="${ctx}/page/pageFragment/form?id=${pageFragment.id}">
					${pageFragment.id}
				</a></td>
				<td>
					${pageFragment.pageId}
				</td>
				<td>
					${pageFragment.keyword}
				</td>
				<td>
					${pageFragment.name}
				</td>
				<td>
					${pageFragment.value}
				</td>
				<td>
					${pageFragment.remark}
				</td>
				<shiro:hasPermission name="page:pageFragment:edit"><td>
    				<a href="${ctx}/page/pageFragment/form?id=${pageFragment.id}">修改</a>
					<!--<a href="${ctx}/page/pageFragment/delete?id=${pageFragment.id}" onclick="return confirmx('确认要删除该页面区块配置吗？', this.href)">删除</a>-->
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
