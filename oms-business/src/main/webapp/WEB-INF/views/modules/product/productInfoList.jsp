<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>产品信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

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
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/product/productInfo/">产品信息列表</a></li>
    <shiro:hasPermission name="product:productInfo:edit">
        <li><a href="${ctx}/product/productInfo/form">产品信息添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="productInfo" action="${ctx}/product/productInfo/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
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
        <th>产品图片</th>
        <th>产品名称</th>
        <th>产品规格</th>
        <th>产品序列号</th>
        <th>库存</th>
        <th>销量</th>
        <th>零售价格</th>
        <th>价格</th>
        <th>利润</th>
        <shiro:hasPermission name="product:productInfo:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="productInfo">
        <tr>

            <td><a href="${ctx}/product/productInfo/form?id=${productInfo.id}">
                    ${productInfo.id}
            </a></td>
            <td style="text-align:center;width:80px;height:80px;background-image: url('${ productInfo.picUrl}');background-size: cover; background-repeat: no-repeat;background-position: 50% 50%;">
            </td>
            <td style="max-width: 240px">
                    ${productInfo.goodsName}
            </td>
            <td>
                    ${productInfo.goodsSpecificationIds}
            </td>
            <td>
                    ${productInfo.goodsSn}
            </td>
            <td>
                    ${productInfo.storeNumber}
            </td>
            <td>
                    ${productInfo.salesVolume}
            </td>
            <td>
                    ${productInfo.retailPrice}
            </td>
            <td>
                    ${productInfo.unitPrice}
            </td>
            <td>
                    ${productInfo.profit}
            </td>
            <shiro:hasPermission name="product:productInfo:edit">
                <td>
                    <a href="${ctx}/product/productInfo/form?id=${productInfo.id}">修改</a>
                    <a href="${ctx}/product/productInfo/delete?id=${productInfo.id}"
                       onclick="return confirmx('确认要删除该产品信息吗？', this.href)">删除</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>