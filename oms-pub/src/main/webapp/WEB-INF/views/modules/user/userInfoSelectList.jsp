<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>选择用户</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function(h) {
		$("input[name=id]").each(function() {
			$(this).click(function() {
				if ($(this).attr('checked')) {
					$(':checkbox[name=id]').removeAttr('checked');
					$(this).attr('checked', 'checked');
				}

				top.checkedUserId = $(this).val();
			});
		});
	});

	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<div style="margin: 10px;">
		<form:form id="searchForm" modelAttribute="userInfo"
			action="${ctx}/user/userInfo/selectList" method="post"
			class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<label>昵称：</label>
			<form:input path="nickname" htmlEscape="false" maxlength="50"
				class="input-small" />&nbsp;
			<label>邮箱：</label>
			<form:input path="email" htmlEscape="false" maxlength="50"
				class="input-small" />&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />&nbsp;&nbsp;
		</form:form>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align: center;">选择</th>
					<th>ID</th>
					<th>昵称</th>
					<th>邮箱</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="userInfo">
					<tr>
						<td style="text-align: center;"><input type="checkbox"
							name="id" value="${userInfo.id}" /></td>
						<td>${userInfo.id}</td>
						<td>${userInfo.nickname}</td>
						<td>${userInfo.email}</td>
						<td><fmt:formatDate value="${userInfo.createTime}"
								type="both" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>
