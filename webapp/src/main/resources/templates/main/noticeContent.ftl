<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>通知内容页</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<div style="text-align: center"><h2>${(notice.title)!}</h2></div>
<div>
    <p>
        &nbsp; &nbsp; &nbsp; &nbsp;  ${(notice.content)}
    </p>
    <br/>
    <br/>

</div>
<script src="js/jquery.js"></script>
<script src="js/echarts-all.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>