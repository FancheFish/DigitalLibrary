<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>《 ${detail.name}》</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script>
        $(function () {
            $('#header').load('reader_header.html');
        })
    </script>
</head>
<body background="img/lizhi.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">
<div id="header"></div>
<div class="col-xs-6 col-md-offset-3" style="position: relative;top: 3%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">《 ${detail.name}》</h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr>
                    <th>索书号</th>
                    <td>${detail.call_name}</td>
                </tr>
                <tr>
                    <th width="15%">书目</th>
                    <td>${detail.name}</td>
                </tr>
                <tr>
                    <th>作者</th>
                    <td>${detail.author}</td>
                </tr>
                <tr>
                    <th>出版社</th>
                    <td>${detail.publish}</td>
                </tr>
                <tr>
                    <th>出版地</th>
                    <td>${detail.pub_place}</td>
                </tr>
                <tr>
                    <th>出版日期</th>
                    <td>${detail.pubdate}</td>
                </tr>
                <tr>
                    <th>页卷数</th>
                    <td>${detail.page_num}</td>
                </tr>
                <tr>
                    <th>ISBN</th>
                    <td>${detail.isbn}</td>
                </tr>
                <tr>
                    <th>单价</th>
                    <td>${detail.price}</td>
                </tr>
                <tr>
                    <th>分类号</th>
                    <td>${detail.classId}</td>
                </tr>
                <tr>
                    <th>文献类型</th>
                    <td>中文图书</td>
                </tr>
                <tr>
                    <th>作品语种</th>
                    <td>${detail.language}</td>
                </tr>
                <tr>
                    <th>丛编题名</th>
                    <td>${detail.series_title}</td>
                </tr>
                <tr>
                    <th>状态</th>
                    <c:if test="${detail.number>1}">
                        <td>在馆</td>
                    </c:if>
                    <c:if test="${detail.number==0}">
                        <td>借出</td>
                    </c:if>
                </tr>
                <tr>
                    <th>可借复本</th>
                    <td>${detail.number}</td>
                </tr>
                <tr>
                    <th>电子资源快速链接</th>
                    <c:if test="${ebook_detail.bookurl != '暂无'}">
                        <td><a href="${ebook_detail.bookurl}">${ebook_detail.bookurl}</a></td>
                    </c:if>
                    <c:if test="${ebook_detail.bookurl == '暂无'}">
                        <td>暂无</td>
                    </c:if>
                </tr>
                <tr>
                    <th>电子资源来源</th>
                    <td>${ebook_detail.source}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>

</body>
</html>
