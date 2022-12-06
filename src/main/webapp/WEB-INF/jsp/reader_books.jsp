<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>全部图书信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
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

<div style="padding: 20px 500px 10px">
    <div>
        <ul class="nav nav-pills">
            <li role="presentation" id="search1" class="active"><a href="#">简单搜索</a></li>
            <li role="presentation" id="search2"><a href="#">高级搜索</a></li>
        </ul>
    </div>
    <form method="post" action="reader_querybook_do.html" class="form-inline" id="searchform">
        <div class="input-group" id="simple-search">
            <span class="input-group-btn">
                <select class="form-control">
                    <option>全部类型</option>
                    <option>书目</option>
                    <option>作者</option>
                    <option>ISBN</option>
                    <option>索书号</option>
                    <option>出版社</option>
                </select>
                <input type="text" placeholder="输入关键词" class="form-control" id="search" name="searchWord"
                       class="form-control" autocomplete="off">
                <input type="submit" value="搜索" class="btn btn-default">
                <a href="reader_books.html" class="btn btn-default">返回全部图书</a>
            </span>
        </div>
        <div class="btn-group btn-group-sm" role="group" aria-label="Small button group" id="hot-search">
            <p style="color: whitesmoke">30天内热门检索top5：</p>
            <button type="button" class="btn btn-default" id="btn1" value="计算机" style="margin-right: 10px">计算机</button>
            <button type="button" class="btn btn-default" id="btn2" value="网页设计" style="margin-right: 10px">网页设计</button>
            <button type="button" class="btn btn-default" id="btn3" value="数据库" style="margin-right: 10px">数据库</button>
            <button type="button" class="btn btn-default" id="btn4" value="C语言" style="margin-right: 10px">C语言</button>
            <button type="button" class="btn btn-default" id="btn5" value="清华大学" style="margin-right: 10px">清华大学</button>
        </div>
    </form>

    <form method="post" action="reader_advanced_querybook_do.html" class="form-inline" id="advanced-searchform" hidden="hidden">
        <div class="input-group" id="advanced-search">
            <input type="text" placeholder="输入书目" class="form-control" id="name-search" name="searchName"
                       class="form-control" autocomplete="off">
            <input type="text" placeholder="输入作者" class="form-control" id="author-search" name="searchAuthor"
                       class="form-control" autocomplete="off">
            <input type="text" placeholder="输入ISBN" class="form-control" id="ISBN-search" name="searchISBN"
                       class="form-control" autocomplete="off">
            <input type="text" placeholder="输入索书号" class="form-control" id="callname-search" name="searchCallname"
                       class="form-control" autocomplete="off">
            <input type="text" placeholder="输入出版社" class="form-control" id="publish-search" name="searchPublish"
                       class="form-control" autocomplete="off">
            <input type="submit" value="搜索" class="btn btn-default">
            <a href="reader_books.html" class="btn btn-default">返回全部图书</a>
        </div>
    </form>

    <script>
        $("#searchform").submit(function () {
            var val = $("#search").val();
            if (val == '') {
                alert("请输入关键字");
                return false;
            }
        });
        $("#advanced-searchform").submit(function () {
            var val1 = $("#name-search").val();
            var val2 = $("#author-search").val();
            var val3 = $("#ISBN-search").val();
            var val4 = $("#callname-search").val();
            var val5 = $("#publish-search").val();
            if (val1 == ''&&val2 == ''&&val3 == ''&&val4 == ''&&val5 == '') {
                alert("请输入至少一个关键字");
                return false;
            }
        })
    </script>
</div>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>
<div class="panel panel-default" style="width: 90%;margin-left: 5%">
    <div class="panel-heading" style="background-color: #fff">
        <h3 class="panel-title">
            全部图书
        </h3>
    </div>
    <div class="panel-body">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>索书号</th>
                <th>书目</th>
                <th>作者</th>
                <th>出版社</th>
                <th>ISBN</th>
                <th>单价</th>
                <th>可借复本</th>
                <th>借还</th>
                <th>详情</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td><c:out value="${book.call_name}"></c:out></td>
                    <td><c:out value="${book.name}"></c:out></td>
                    <td><c:out value="${book.author}"></c:out></td>
                    <td><c:out value="${book.publish}"></c:out></td>
                    <td><c:out value="${book.isbn}"></c:out></td>
                    <td><c:out value="${book.price}"></c:out></td>
                    <td><c:out value="${book.number}"></c:out></td>

                    <c:set var="flag" value="false"/>
                    <c:forEach var="lend" items="${myLendList}">
                        <c:if test="${lend eq book.bookId}">
                            <c:set var="flag" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${flag}">
                        <td><a href="returnbook.html?bookId=<c:out value="${book.bookId}"></c:out>">
                            <button type="button" class="btn btn-danger btn-xs">归还</button>
                        </a></td>
                    </c:if>
                    <c:if test="${not flag}">
                        <c:if test="${book.number>0}">
                            <td><a href="lendbook.html?bookId=<c:out value="${book.bookId}"></c:out>">
                                <button type="button" class="btn btn-primary btn-xs">借阅</button>
                            </a></td>
                        </c:if>
                        <c:if test="${book.number==0}">
                            <td>
                                <button type="button" class="btn btn-defalut btn-xs" disabled="disabled">已空</button>
                            </td>
                        </c:if>
                    </c:if>
                    <td><a href="reader_book_detail.html?bookId=<c:out value="${book.bookId}"></c:out>">
                        <button type="button" class="btn btn-success btn-xs">详情</button>
                    </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

<script>
    $('#btn1').click(function () {
        $('#search').val($('#btn1').val());
    });
    $('#btn2').click(function () {
        $('#search').val($('#btn2').val());
    });
    $('#btn3').click(function () {
        $('#search').val($('#btn3').val());
    });
    $('#btn4').click(function () {
        $('#search').val($('#btn4').val());
    });
    $('#btn5').click(function () {
        $('#search').val($('#btn5').val());
    });

    $('#search1').click(function () {
        //$('#simple-search').show();
        //$('#advanced-search').hide();
        //$('#hot-search').show();
        $('#search1').addClass("active");
        $('#search2').removeClass();
        $('#searchform').show();
        $('#advanced-searchform').hide();
    })
    $('#search2').click(function () {
        //$('#simple-search').hide();
        //$('#advanced-search').show();
        //$('#hot-search').hide();
        $('#search1').removeClass();
        $('#search2').addClass("active");
        $('#searchform').hide();
        $('#advanced-searchform').show();
    })
</script>