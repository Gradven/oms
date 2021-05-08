<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>页面区块信息管理</title>
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
		<li class="active"><a href="${ctx}/page/pageFragmsupplierInfo/">页面区块信息列表</a></li>
		<shiro:hasPermission name="page:pageFragmsupplierInfo:edit"><li><a href="${ctx}/page/pageFragmsupplierInfo/form">页面区块信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pageFragmsupplierInfo" action="${ctx}/page/pageFragmsupplierInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>页面id：</label>
				<form:input path="pageId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>区块名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>页面名称</th>
				<th>区块类型</th>
				<th>区块KEY</th>
				<th>区块名称</th>
				<th>区块值</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="page:pageFragmsupplierInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="pageFragmsupplierInfo">
			<tr>
				<td><a href="${ctx}/page/pageFragmsupplierInfo/form?id=${pageFragmsupplierInfo.id}">
					${pageFragmsupplierInfo.id}
				</a></td>
				<td>
					${pageFragmsupplierInfo.pageId}
				</td>
				<td>
					${pageFragmsupplierInfo.pageName}
				</td>
				<td>
					${fns:getDictLabel(pageFragmsupplierInfo.type, 'fragment_type', '')}
				</td>
				<td>
					${pageFragmsupplierInfo.fragmentKey}
				</td>
				<td>
					${pageFragmsupplierInfo.name}
				</td>
				<td>
					${pageFragmsupplierInfo.value}
				</td>
				<td>
					${pageFragmsupplierInfo.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${pageFragmsupplierInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pageFragmsupplierInfo.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${pageFragmsupplierInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${pageFragmsupplierInfo.remark}
				</td>
				<shiro:hasPermission name="page:pageFragmsupplierInfo:edit"><td>
    				<a href="${ctx}/page/pageFragmsupplierInfo/form?id=${pageFragmsupplierInfo.id}">修改</a>
					<a href="${ctx}/page/pageFragmsupplierInfo/delete?id=${pageFragmsupplierInfo.id}" onclick="return confirmx('确认要删除该页面区块信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
