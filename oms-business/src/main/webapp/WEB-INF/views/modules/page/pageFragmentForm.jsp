<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>页面区块配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/page/pageFragment/">页面区块配置列表</a></li>
		<li class="active"><a href="${ctx}/page/pageFragment/form?id=${pageFragment.id}">页面区块配置<shiro:hasPermission name="page:pageFragment:edit">${not empty pageFragment.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="page:pageFragment:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pageFragment" action="${ctx}/page/pageFragment/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
				<label class="control-label">页面id：</label>
				<!--  <div class="controls">
				<form:input path="pageId" htmlEscape="false" maxlength="20" class="input-xlarge required digits"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>-->

				<div class="controls">
					<sys:treeselect id="parent" name="pageId" value="${pageFragment.pageId}" labelName="parent.name" labelValue="${pageFragment.pageId}"
									title="父节点id" url="/page/pageInfo/treeData" extId="${pageFragment.pageId}" cssClass="" allowClear="true"/>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键字：</label>
			<div class="controls">
				<form:input path="keyword" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区块类型：</label>
			<div class="controls">
				<form:select  id="fragmentType" path="type" class="input-medium required" onchange="onTypeChanged()">
					<form:options items="${fns:getDictList('fragment_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区块名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区块值：</label>
			<%--<div class="controls">--%>
				<%--<form:input path="value" htmlEscape="false" maxlength="2000" class="input-xlarge required"/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span> id以竖线分割，示例： 2|3|10|11|9--%>
			<%--</div>--%>
			<div class="controls">
				<form:hidden id="valueRelation" path="value" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<ol id="valueSelectList"></ol>
				<a id="relationButton" href="javascript:" class="btn">添加相关</a>
				<script type="text/javascript">
                    var valueSelect = [];
                    function valueSelectAddOrDel(id,title){
                        var isExtents = false, index = 0;
                        for (var i=0; i<valueSelect.length; i++){
                            if (valueSelect[i][0]==id){
                                isExtents = true;
                                index = i;
                            }
                        }
                        if(isExtents){
                            valueSelect.splice(index,1);
                        }else{
                            valueSelect.push([id,title]);
                        }
                        valueSelectRefresh();
                    }

                    function urlSwitch() {
                        var type = $("#fragmentType").val()
                        var url = "${ctx}/goods/goodsInfo"
                        if(type == 2){
                            url = "${ctx}/goods/goodsCategory"
                        }else if(type == 3){
                            url = "${ctx}/shop/shopInfo"
                        }
						console.log("url is "+ url)
                        return url
                    }
                    function valueSelectRefresh(){
                        $("#valueRelation").val("");
                        $("#valueSelectList").children().remove();
                        for (var i=0; i<valueSelect.length; i++){
                            $("#valueSelectList").append("<li>"+valueSelect[i][1]+"&nbsp;&nbsp;<a href=\"javascript:\" onclick=\"valueSelectAddOrDel('"+valueSelect[i][0]+"','"+valueSelect[i][1]+"');\">×</a></li>");
                            var value = $("#valueRelation").val()+valueSelect[i][0]
                            if(i < valueSelect.length - 1){
                                value += "|";
							}
                            $("#valueRelation").val(value);
                        }
                    }

                    $.getJSON(urlSwitch()+"/findByIds",{ids:$("#valueRelation").val()},function(data){
                        for (var i=0; i<data.length; i++){
                            valueSelect.push([data[i].id,data[i].name]);
                        }
                        valueSelectRefresh();
                    });
                    $("#relationButton").click(function(){
                        top.valueSelect = valueSelect;
                        top.valueSelectAddOrDel = valueSelectAddOrDel;
                        top.$.jBox.open("iframe:"+ urlSwitch()+"/selectList?pageSize=8", "添加区块值",$(top.document).width()-220,$(top.document).height()-180,{
                            buttons:{"确定":true}, loaded:function(h){
                                $(".jbox-content", top.document).css("overflow-y","hidden");
                            }
                        });
                    });


                    function onTypeChanged() {
                        valueSelect = []
                        valueSelectRefresh();
                    }
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remark" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="page:pageFragment:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
