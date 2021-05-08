<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>商品信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        };

        function clearConditions() {
            $("#onSaleOptions").val("");
            $("#approveStatusOptions").val("");
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/goods/goodsInfo/">商品信息列表</a></li>
    <shiro:hasPermission name="goods:goodsInfo:edit">
        <li><a href="${ctx}/goods/goodsInfo/form">商品信息添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="goodsInfo" action="${ctx}/goods/goodsInfo/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
            <%--<li><label>商品SN：</label>--%>
            <%--<form:input path="sn" htmlEscape="false" maxlength="128" class="input-medium"/>--%>
            <%--</li>--%>
        <li><label>商品名称：</label>
            <form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"
                        cssStyle="height: 22px; font-size: 12px"/>
        </li>
        <li><label>供应商名称：</label>
            <form:input path="supplierName" htmlEscape="false" maxlength="11" class="input-medium"
                        cssStyle="height: 22px; font-size: 12px"/>
        </li>
        <li><label>审核状态：</label>
            <form:select id="approveStatusOptions" path="approveStatus" class="input-medium">
                <form:option value="-1" label="" selected="selected">请选择</form:option>
                <form:options items="${fns:getDictList('approve_status')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li><label>上架状态：</label>
            <form:select id="onSaleOptions" path="onSaleFlag" class="input-medium">
                <form:option value="-1" label="" selected="selected">请选择</form:option>
                <form:options items="${fns:getDictList('on_sale_flag')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" style="margin-left: 20px; height:30px;width:100px; font-size: 14px;line-height: 14px"/></li>
            <%--<li class="clearfix"><input id="btnReset" class="btn" type="reset" value="清除" onclick="clearConditions()"/></li>--%>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>编号</th>
        <th>图片</th>
        <th>商品名称</th>
        <%--<th>商品SN</th>--%>
        <th>分类名称</th>
        <th>供应商</th>
        <th>销售量</th>
        <th>商品单位</th>
        <th>库存</th>
        <th>零售价</th>
        <th>单价</th>
        <th>利润</th>
        <th>商品描述</th>
        <th>是否上架</th>
        <th>审核状态</th>
        <shiro:hasPermission name="goods:goodsInfo:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="goodsInfo">
        <tr>
            <td>
                    ${goodsInfo.id}
            </td>
            <td style="text-align:center;width:80px;height:80px;background-image: url('${ goodsInfo.cover}');background-size: cover; background-repeat: no-repeat;background-position: 50% 50%;">
            </td>
            <td>
                    ${goodsInfo.name}
            </td>
            <td>
                    ${goodsInfo.categoryName}
            </td>
                <%--<td><a href="${ctx}/goods/goodsInfo/form?id=${goodsInfo.id}">--%>
                <%--${goodsInfo.sn}--%>
                <%--</a></td>--%>
            <td>
                    ${goodsInfo.supplierName}
            </td>


            <td>
                <c:if test='${not empty goodsInfo.salesVolume}'>${goodsInfo.salesVolume}</c:if><c:if
                    test='${empty goodsInfo.salesVolume}'>0</c:if>
            </td>
            <td>
                    ${goodsInfo.unit}
            </td>
            <td>
                <c:if test='${not empty goodsInfo.storeNumber}'>${goodsInfo.storeNumber}</c:if><c:if
                    test='${empty goodsInfo.storeNumber}'>0</c:if>
            </td>
            <td>
                    ${goodsInfo.retailPrice}
            </td>

            <td>
                    ${goodsInfo.unitPrice}
            </td>

            <td>
                    ${goodsInfo.profit}
            </td>

                <%--<td>--%>
                <%--${fns:getDictLabel(goodsInfo.delFlag, 'goods_del_flag', '')}--%>
                <%--</td>--%>
            <td style="max-width: 240px">
                    ${goodsInfo.shortDescription}
            </td>
            <td>
                    ${fns:getDictLabel(goodsInfo.onSaleFlag, 'on_sale_flag', '')}
            </td>
            <td>
                    ${fns:getDictLabel(goodsInfo.approveStatus, 'approve_status', '')}
            </td>
            <shiro:hasPermission name="goods:goodsInfo:edit">
                <td>
                    <a href="${ctx}/goods/goodsInfo/form?id=${goodsInfo.id}">修改</a><br/>
                    <a href="${ctx}/goods/goodsInfo/delete?id=${goodsInfo.id}"
                       onclick="return confirmx('确认要删除该商品信息吗？', this.href)">删除</a><br/>
                    <a href="${ctx}/goods/goodsInfo/productionProfitForm?id=${goodsInfo.id}">产品</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
