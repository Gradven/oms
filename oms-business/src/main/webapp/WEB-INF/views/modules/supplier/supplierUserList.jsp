<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商运营用户管理</title>
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
		<li class="active"><a href="${ctx}/supplier/supplierUser/">供应商运营用户列表</a></li>
		<shiro:hasPermission name="supplier:supplierUser:edit"><li><a href="${ctx}/supplier/supplierUser/form">供应商运营用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="supplierUser" action="${ctx}/supplier/supplierUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>用户姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>用户状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户编号</th>
				<th>企业用户姓名</th>
				<th>登录名</th>
				<th>手机号码</th>
				<th>用户状态</th>
				<th>所属供应商</th>
				<th>备注</th>
				<th>修改时间</th>
				<shiro:hasPermission name="supplier:supplierUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="supplierUser">
			<tr>
				<td><a href="${ctx}/supplier/supplierUser/form?id=${supplierUser.id}">
					${supplierUser.id}
				</a></td>
				<td>
					${supplierUser.name}
				</td>
				<td>
					${supplierUser.account}
				</td>
				<td>
					${supplierUser.mobile}
				</td>
				<td>
					${fns:getDictLabel(supplierUser.status, 'supplier_user_status', '')}
				</td>
				<td>
					${supplierUser.supplierId}
				</td>
				<td>
					${supplierUser.remark}
				</td>
				<td>
					<fmt:formatDate value="${supplierUser.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="supplier:supplierUser:edit"><td>
    				<a href="${ctx}/supplier/supplierUser/form?id=${supplierUser.id}">修改</a>
					<a href="${ctx}/supplier/supplierUser/delete?id=${supplierUser.id}" onclick="return confirmx('确认要删除该供应商运营用户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
