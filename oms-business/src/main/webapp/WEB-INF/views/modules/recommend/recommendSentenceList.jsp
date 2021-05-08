<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商品推荐语管理</title>
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
		<li class="active"><a href="${ctx}/recommend/recommendSentence/">商品推荐语列表</a></li>
		<shiro:hasPermission name="recommend:recommendSentence:edit"><li><a href="${ctx}/recommend/recommendSentence/form">商品推荐语添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="recommendSentence" action="${ctx}/recommend/recommendSentence/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<!--<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>-->
			<li><label>推荐语：</label>
				<form:input path="recommend" htmlEscape="false" maxlength="128" class="input-xxlarge"/>
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
				<th>推荐语</th>
				<th>更新时间</th>
				<shiro:hasPermission name="recommend:recommendSentence:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="recommendSentence">
			<tr>
				<td><a href="${ctx}/recommend/recommendSentence/form?id=${recommendSentence.id}">
					${recommendSentence.id}
				</a></td>
				<td>
					${recommendSentence.recommend}
				</td>
				<td>
					<fmt:formatDate value="${recommendSentence.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="recommend:recommendSentence:edit"><td>
    				<a href="${ctx}/recommend/recommendSentence/form?id=${recommendSentence.id}">修改</a>
					<a href="${ctx}/recommend/recommendSentence/delete?id=${recommendSentence.id}" onclick="return confirmx('确认要删除该商品推荐语吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
