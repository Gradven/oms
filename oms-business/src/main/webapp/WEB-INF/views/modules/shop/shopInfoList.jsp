<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店铺信息管理</title>
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
		<li class="active"><a href="${ctx}/shop/shopInfo/">店铺信息列表</a></li>
		<shiro:hasPermission name="shop:shopInfo:edit"><li><a href="${ctx}/shop/shopInfo/form">店铺信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="shopInfo" action="${ctx}/shop/shopInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>店铺名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>店铺名称</th>
				<th>店铺logo</th>
				<th>介绍</th>
				<th>背景图地址</th>
				<th>是否认证,0:未认证，1:已认证</th>
				<th>缴费标志：0:未缴费，1：已缴费</th>
				<th>到期时间</th>
				<shiro:hasPermission name="shop:shopInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shopInfo">
			<tr>
				<td><a href="${ctx}/shop/shopInfo/form?id=${shopInfo.id}">
					${shopInfo.name}
				</a></td>
				<td>
					${shopInfo.logo}
				</td>
				<td>
					${shopInfo.description}
				</td>
				<td>
					${shopInfo.backgroundUrl}
				</td>
				<td>
					${fns:getDictLabel(shopInfo.certificateFlag, 'certificateFlag', '')}
				</td>
				<td>
					${fns:getDictLabel(shopInfo.payFeeFlag, 'payFeeFlag', '')}
				</td>
				<td>
					<fmt:formatDate value="${shopInfo.expireTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="shop:shopInfo:edit"><td>
    				<a href="${ctx}/shop/shopInfo/form?id=${shopInfo.id}">修改</a>
					<a href="${ctx}/shop/shopInfo/delete?id=${shopInfo.id}" onclick="return confirmx('确认要删除该店铺信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>