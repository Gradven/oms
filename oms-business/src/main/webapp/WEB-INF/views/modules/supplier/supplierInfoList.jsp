<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息管理</title>
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
		<li class="active"><a href="${ctx}/supplier/supplierInfo/">供应商信息列表</a></li>
		<shiro:hasPermission name="supplier:supplierInfo:edit"><li><a href="${ctx}/supplier/supplierInfo/form">供应商信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="supplierInfo" action="${ctx}/supplier/supplierInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>企业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>企业编号</th>
				<th>企业名称</th>
				<th>统一社会信用代码</th>
				<th>企业法人</th>
				<th>登记机关所在省份</th>
				<th>地址</th>
				<th>联系人</th>
				<th>联系人手机号码</th>
				<th>状态</th>
				<shiro:hasPermission name="supplier:supplierInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="supplierInfo">
			<tr>
				<td><a href="${ctx}/supplier/supplierInfo/form?id=${supplierInfo.id}">
					${supplierInfo.id}
				</a></td>
				<td>
					${supplierInfo.name}
				</td>
				<td>
					${supplierInfo.creditCode}
				</td>
				<td>
					${supplierInfo.legalRepresentative}
				</td>
				<td>
					${supplierInfo.regProvince}
				</td>
				<td>
					${supplierInfo.address}
				</td>
				<td>
					${supplierInfo.contact}
				</td>
				<td>
					${supplierInfo.mobile}
				</td>
				<td>
					${fns:getDictLabel(supplierInfo.status, 'supplier_status', '')}
				</td>
				<shiro:hasPermission name="supplier:supplierInfo:edit"><td>
    				<a href="${ctx}/supplier/supplierInfo/form?id=${supplierInfo.id}">修改</a>
					<a href="${ctx}/supplier/supplierInfo/delete?id=${supplierInfo.id}" onclick="return confirmx('确认要删除该供应商信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>