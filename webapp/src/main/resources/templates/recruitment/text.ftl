<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>申请新的招聘信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>简历</span></div>
    <div>
        <a href="/upload">表单上传</a>&emsp;&emsp;
    </div>
    <div class="find-top1">
        <form action="" id="form_Recruitment">
            <table class="top-table">
                <tr>
                    <td>id</td>
                    <td>名字</td>
                    <td>大小</td>
                    <td>操作</td>
                </tr>
                <#list fileDocumentList as fileDocument>
                    <tr>
                        <td>${fileDocument.id}</td>
                        <td>${fileDocument.name}</td>
                        <td>${fileDocument.size}</td>
                        <td><a href="/files/view?id=${fileDocument.id}">预览</a><a>下载</a> <a>删除</a></td>
                    </tr>
                </#list>

            </table>
        </form>
    </div>
</main>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>