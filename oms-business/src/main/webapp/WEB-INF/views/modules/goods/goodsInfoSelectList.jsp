<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>选择区块内容</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function(h) {
            $("input[name=id]").each(function(){
                var valueSelect = top.valueSelect;
                for (var i=0; i<valueSelect.length; i++){
                    if (valueSelect[i][0]==$(this).val()){
                        this.checked = true;
                    }
                }
                $(this).click(function(){
                    <c:if test="${checkMode == 'single'}">
                    if($(this).attr('checked')){
                        $(':checkbox[name=id]').removeAttr('checked');
                        $(this).attr('checked','checked');
                    }
                    </c:if>
                    var id = $(this).val(), title = $(this).attr("title");
                    top.valueSelectAddOrDel(id, title);
                });
            });
        });
        function view(href){
            // top.$.jBox.open('iframe:'+href,'查看文章',$(top.document).width()-220,$(top.document).height()-120,{
            //     buttons:{"关闭":true},
            //     loaded:function(h){
            //         $(".jbox-content", top.document).css("overflow-y","hidden");
            //         $(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
            //     }
            // });
            // return false;
        }
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<div style="margin:10px;">
    <form:form id="searchForm" modelAttribute="goodsInfo" action="${ctx}/goods/goodsInfo/selectList" method="post" class="breadcrumb form-search">
        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
        <label>商品名称：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
    </form:form>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead><tr><th style="text-align:center;">选择</th><th>图片</th><th>名称</th><th>库存</th><th>更新时间</th></tr></thead>
        <tbody>
        <c:forEach items="${page.list}" var="goodsInfo">
            <tr>
                <td style="text-align:center;"><input type="checkbox" name="id" value="${goodsInfo.id}" title="${fns:abbr(goodsInfo.name,40)}" /></td>
                <td style="text-align:center;width:80px;height:80px;background-image: url('${ goodsInfo.cover}');background-size: cover; background-repeat: no-repeat;background-position: 50% 50%;">
                </td>
                <td>${fns:abbr(goodsInfo.name,40)}</td>
                <td>${goodsInfo.storeNumber}</td>
                <td><fmt:formatDate value="${goodsInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</div>
</body>
</html>