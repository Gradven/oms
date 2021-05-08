<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息管理</title>
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
	<li class="active"><a href="${ctx}/user/userInfo/">用户信息列表</a></li>
	<shiro:hasPermission name="user:userInfo:edit"><li><a href="${ctx}/user/userInfo/form">用户信息添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="userInfo" action="${ctx}/user/userInfo/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
		<li><label>用户ID：</label>
			<form:input path="id" htmlEscape="false" maxlength="20" class="input-medium"/>
		</li>
		<li><label>昵称：</label>
			<form:input path="nickname" htmlEscape="false" maxlength="16" class="input-medium"/>
		</li>
		<li><label>手机号码：</label>
			<form:input path="mobile" htmlEscape="false" maxlength="32" class="input-medium"/>
		</li>
		<li><label>用户状态：</label>
			<form:select path="status" class="input-medium">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('user_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
		<th>昵称</th>
		<th>头像</th>
		<th>邮箱地址</th>
		<th>手机号码</th>
		<th>用户状态</th>
		<th>账号类型</th>
		<th>国家</th>
		<th>省份</th>
		<th>城市</th>
		<th>创建时间</th>
		<th>更新时间</th>
		<shiro:hasPermission name="user:userInfo:edit"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="userInfo">
		<tr>
			<td><a href="${ctx}/user/userInfo/form?id=${userInfo.id}">
					${userInfo.id}
			</a></td>
			<td>
					${userInfo.nickname}
			</td>
			<td>
				<a href="${userInfo.avatar}" target="_blank"><img src="${userInfo.avatar}" alt="" style="width:30px;height:30px;" /></a>
			</td>
			<td>
					${userInfo.email}
			</td>
			<td>
					${userInfo.mobile}
			</td>
			<td>
					${fns:getDictLabel(userInfo.status, 'user_status', '')}
			</td>
			<td>
					${fns:getDictLabel(userInfo.accountType, 'account_type', '')}
			</td>
			<td>
					${userInfo.country}
			</td>
			<td>
					${userInfo.province}
			</td>
			<td>
					${userInfo.city}
			</td>
			<td>
				<fmt:formatDate value="${userInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
				<fmt:formatDate value="${userInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<shiro:hasPermission name="user:userInfo:edit"><td>
				<a href="${ctx}/user/userInfo/form?id=${userInfo.id}">修改</a>
				<!--<a href="${ctx}/user/userInfo/delete?id=${userInfo.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>-->
			</td></shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
