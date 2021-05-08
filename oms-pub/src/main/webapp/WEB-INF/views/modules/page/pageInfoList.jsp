<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>页面信息管理管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				row.createTime = formatDate('yyyy-MM-dd HH:mm:ss', row.createTime);
				row.updateTime = formatDate('yyyy-MM-dd HH:mm:ss', row.updateTime);
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}

		function formatDate(format, dateStr) {
			var time = new Date(dateStr);
		    var date = {
		        "M+": time.getMonth() + 1,
		        "d+": time.getDate(),
		        "H+": time.getHours(),
		        "m+": time.getMinutes(),
		        "s+": time.getSeconds(),
		        "q+": Math.floor((time.getMonth() + 3) / 3),
		        "S+": time.getMilliseconds()
		    };
		    if (/(y+)/i.test(format)) {
		        format = format.replace(RegExp.$1, (time.getFullYear() + '').substr(4 - RegExp.$1.length));
		    }
		    for (var k in date) {
		        if (new RegExp("(" + k + ")").test(format)) {
		            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
		        }
		    }
		    return format;
		};
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/page/pageInfo/">页面信息管理列表</a></li>
		<shiro:hasPermission name="page:pageInfo:edit"><li><a href="${ctx}/page/pageInfo/form">页面信息管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="pageInfo" action="${ctx}/page/pageInfo/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>父节点id</th>
				<th>名称</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注</th>
				<shiro:hasPermission name="page:pageInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/page/pageInfo/form?id={{row.id}}">
				{{row.id}}
			</a></td>
			<td>
				{{row.parent.id}}
			</td>
			<td>
				{{row.name}}
			</td>
			<td>
				{{row.createBy.name}}
			</td>
			<td>
				{{row.createTime}}
			</td>
			<td>
				{{row.updateBy.name}}
			</td>
			<td>
				{{row.updateTime}}
			</td>
			<td>
				{{row.remark}}
			</td>
			<shiro:hasPermission name="page:pageInfo:edit"><td>
   				<a href="${ctx}/page/pageInfo/form?id={{row.id}}">修改</a>
				<a href="${ctx}/page/pageInfo/delete?id={{row.id}}" onclick="return confirmx('确认要删除该页面信息管理及所有子页面信息管理吗？', this.href)">删除</a>
				<a href="${ctx}/page/pageInfo/form?parent.id={{row.id}}">添加下级页面信息管理</a>
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>
