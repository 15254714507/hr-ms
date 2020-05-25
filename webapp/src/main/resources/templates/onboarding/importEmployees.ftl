<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>导入新员工信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <link href="css/table.css" rel="stylesheet" type="text/css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>导入新员工信息</span></div>
    <div style="border-bottom: 1px #ccc solid;padding-bottom: 8px">
        <p style="line-height: 24px;font-size: 14px;padding: 4px 0 0 36px ;color:#bb8940;background-repeat: no-repeat;background-position: 10px 8px;font-weight: bold">
            温馨提示</p>
        <ul class="ts">
            <li><span>*</span>上传的excel需要符合要求，如没有请<a href="/downEmployeesTemplates.do">下载模板</a></li>
        </ul>
    </div>
    <div class="find-top1">
        <form name="form_employees">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">上传excel</td>
                    <td colspan="5"><input type="file" name="file"></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="提交" data-dismiss="modal"
                                                                       onclick="submitFile()"/>
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
    function submitFile() {
        let form = document.forms.namedItem("form_employees");
        let formData = new FormData(form);
        $.ajax({
            url: "/importEmployees.do",
            type: "POST",
            data: formData,
            dataType: "json",
            contentType: false, //必须
            processData: false, //必须
            cache: false,
            success: function (result) {
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