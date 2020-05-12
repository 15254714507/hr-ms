<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加新的应聘者</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>应聘者录入</span></div>
    <div class="find-top1">
        <form name="form_recruiters">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">姓名：</td>
                    <td><input type="text" name="name"></td>
                    <td class="top-table-label">应聘岗位：</td>
                    <td><input type="text" name="positions"></td>
                    <td class="top-table-label">工作类型：</td>
                    <td>
                        <select name="type">
                            <option>实习</option>
                            <option>全职</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="top-table-label">上传简历</td>
                    <td colspan="5"><input type="file" name="file"></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <i class="glyphicon glyphicon-edit"></i><input type="button" value="提交" data-dismiss="modal" onclick="submitRecruiters()"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</main>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    function submitRecruiters() {
        let form = document.forms.namedItem("form_recruiters");
        let formData = new FormData(form);
        $.ajax({
            url: "/saveRecruiters.do",
            type: "POST",
            data: formData,
            dataType: "json",
            contentType: false, //必须
            processData: false, //必须
            cache: false,
            success: function (result) {
                if (result.code === 1) {
                    location.reload();
                }
                alert(result.msg);
            },
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }
</script>
</body>
</html>