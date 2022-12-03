<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书信息添加</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .form-group {
            margin-bottom: 0;
        }
    </style>
    <script>
        $(function () {
            $('#header').load('admin_header.html');
        })
    </script>
</head>
<body background="img/sky.jpg" style=" background-repeat:no-repeat ;
background-size:100% 100%;
background-attachment: fixed;">

<div id="header"></div>
<div style="position: relative;padding-top: 60px; width: 80%;margin-left: 10%">
    <form action="book_add_do.html" method="post" id="addbook">
        <div class="form-group">
            <label for="call_name">索书号</label>
            <input type="text" class="form-control" name="call_name" id="call_name" placeholder="请输入索书号">
        </div>
        <div class="form-group">
            <label for="name">书目</label>
            <input type="text" class="form-control" name="name" id="name" placeholder="请输入书目">
        </div>
        <div class="form-group">
            <label for="author">作者</label>
            <input type="text" class="form-control" name="author" id="author" placeholder="请输入作者名">
        </div>
        <div class="form-group">
            <label for="publish">出版社</label>
            <input type="text" class="form-control" name="publish" id="publish" placeholder="请输入出版社">
        </div>
        <div class="form-group">
            <label for="pub_place">出版地</label>
            <input type="text" class="form-control" name="pub_place" id="pub_place" placeholder="请输入出版地">
        </div>
        <div class="form-group">
            <label for="pubstr">出版日期</label>
            <input type="date" class="form-control" name="pubstr" id="pubstr" placeholder="请输入出版日期">
        </div>
        <div class="form-group">
            <label for="page_num">页卷数</label>
            <input type="text" class="form-control" name="page_num" id="page_num" placeholder="请输入页卷数">
        </div>
        <div class="form-group">
            <label for="isbn">ISBN</label>
            <input type="text" class="form-control" name="isbn" id="isbn" placeholder="请输入ISBN">
        </div>
        <div class="form-group">
            <label for="price">单价</label>
            <input type="text" class="form-control" name="price" id="price" placeholder="请输入单价">
        </div>
        <div class="form-group">
            <label for="classId">分类号</label>
            <input type="text" class="form-control" name="classId" id="classId" placeholder="请输入分类号">
        </div>
        <div class="form-group">
            <label for="language">作品语种</label>
            <input type="text" class="form-control" name="language" id="language" placeholder="请输入作品语种">
        </div>
        <div class="form-group">
            <label for="series_title">丛编题名</label>
            <textarea class="form-control" rows="3" name="series_title" id="series_title"
                      placeholder="请输入丛编题名"></textarea>
        </div>
        <div class="form-group">
            <label for="number">可借复本</label>
            <input type="text" class="form-control" name="number" id="number" placeholder="请输入可借复本数量">
        </div>

        <input type="submit" value="添加" class="btn btn-success btn-sm" class="text-left">
        <script>
            $("#addbook").submit(function () {
                if ($("#name").val()==''||$("#author").val()==''||$("#publish").val()==''||$("#pubstr").val()==''||$("#isbn").val()==''||$("#price").val()==''||$("#language").val()=='') {
                    alert("请填入完整图书信息！");
                    return false;
                }
            })
        </script>
    </form>
</div>
</body>
</html>
