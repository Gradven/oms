<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品对应规格表值表管理</title>
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
		<li class="active"><a href="${ctx}/goods/goodsSpecification/">商品规格值列表</a></li>
		<shiro:hasPermission name="goods:goodsSpecification:edit"><li><a href="${ctx}/goods/goodsSpecification/form">商品规格值添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="goodsSpecification" action="${ctx}/goods/goodsSpecification/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编号：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>商品编号：</label>
				<form:input path="goodsId" htmlEscape="false" maxlength="11" class="input-medium"/>
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
				<th>图片</th>
				<th>商品名称</th>
				<th>规格</th>
				<th>规格值</th>
				<th>商品编号</th>
				<%--<th>修改时间</th>--%>
				<shiro:hasPermission name="goods:goodsSpecification:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="goodsSpecification">
			<tr>
				<td><a href="${ctx}/goods/goodsSpecification/form?id=${goodsSpecification.id}">
					${goodsSpecification.id}
				</a></td>
				<td style="text-align:center;width:80px;height:80px;background-image: url('${ goodsSpecification.picUrl}');background-size: cover; background-repeat: no-repeat;background-position: 50% 50%;">
				</td>
				<td>
						${goodsSpecification.goodsName}
				</td>

				<td>
					${goodsSpecification.specificationName}
				</td>
				<td>
					${goodsSpecification.value}
				</td>


					<td>
					${goodsSpecification.goodsId}
					</td>

				<%--<td>--%>
					<%--<fmt:formatDate value="${goodsSpecification.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<%--</td>--%>
				<shiro:hasPermission name="goods:goodsSpecification:edit"><td>
    				<a href="${ctx}/goods/goodsSpecification/form?id=${goodsSpecification.id}">修改</a>
					<a href="${ctx}/goods/goodsSpecification/delete?id=${goodsSpecification.id}" onclick="return confirmx('确认要删除该商品对应规格表值表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>