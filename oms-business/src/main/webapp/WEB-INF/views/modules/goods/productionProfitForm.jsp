<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>关联产品利润配置</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
            $('input[id=coverImgFile]').change(function () {
                var f = document.getElementById("coverImgFile").files;
                $('#beautyFile').val(f[0].name);
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/goods/goodsInfo/">商品列表</a></li>
    <li class="active"><a href="${ctx}/goods/goodsInfo/form?id=${goodsInfo.id}">关联产品利润配置</a></li>
</ul>
<br/>
<c:choose>

    <c:when test="${not empty goodsInfo.relativeProducts}">
        <form:form id="inputForm" modelAttribute="goodsInfo" action="${ctx}/goods/goodsInfo/saveProfit" method="post"
                   class="form-horizontal" enctype="multipart/form-data">
            <form:hidden path="id"/>
            <sys:message content="${message}"/>
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th>产品名称</th>
                    <th>规格</th>
                    <th>单价</th>
                    <th>销售价</th>
                    <th>库存</th>
                    <th>利润</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${goodsInfo.relativeProducts}" var="product" varStatus="i">
                    <tr>
                        <td> ${goodsInfo.name}</td>
                        <td>
                                ${product.goodsSpecificationDes}
                        </td>
                        <td>
                                ${product.unitPrice}
                        </td>
                        <td>
                                ${product.retailPrice}
                        </td>
                        <td>
                                ${product.storeNumber}
                        </td>
                        <td>
                                ${product.profit}
                            <%--<input name="relativeProducts[${i.index}].profit" value="${product.profit}" htmlEscape="false"--%>
                                   <%--class="input-xlarge required"/>--%>
                            <%--<input name="relativeProducts[${i.index}].id" value="${product.id}" type="hidden"/>--%>
                            <%--<span class="help-inline"><font color="red">*</font> </span>--%>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--<div class="form-actions" >--%>
                <%--<shiro:hasPermission name="goods:goodsInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit"--%>
                                                                        <%--value="保 存"/>&nbsp;</shiro:hasPermission>--%>
                <%--<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
            <%--</div>--%>
        </form:form>
    </c:when>

    <c:otherwise>
        <label class="lbl">没有与该商品相关联的产品</label>
    </c:otherwise>

</c:choose>
<%--<c:if test="${not empty goodsInfo.relativeProducts}">--%>


<%--</c:if>--%>
</body>
</html>