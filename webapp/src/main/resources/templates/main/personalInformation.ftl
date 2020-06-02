<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <style>
        #preview {
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<main>
    <div class="find-top1">
        <form action="" id="form_user" name="form_user">
        <table class="top-table">

                <tr>
                    <td class="top-table-label">头像：</td>
                    <td>
                        <div id="preview"><img src="${(employees.headShot)!}"
                                               style="width: 100px;height: 100px"
                                               class="per-img"/></div>
                        <input name="img" type="file" onchange="preview(this)"></td>
                </tr>

            <tr>
                <td class="top-table-label">职员编号：</td>
                <td><input type="text" value="${(employees.username)!}"
                           readonly/></td>
            </tr>
            <tr>
                <td class="top-table-label">职员姓名：</td>
                <td><input type="text" value="${(employees.name)!}"
                           readonly/></td>
            </tr>
            <tr>
                <td class="top-table-label">密码：</td>
                <td><input id="password" name="password" type="text"  value="*************"
                           readonly/>
                    <div>
                        <input type="button"  id="updatePasswordBtn" onclick="updatePassword()" value="修改密码"/>
                        <input type="button" style="display: none" id="savePasswordBtn" onclick="savePassword()" value="确认新密码"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="top-table-label">部门：</td>
                <td><input type="text" value="${(employees.departmentName)!}"
                           readonly/></td>
            </tr>
            <tr>
                <td class="top-table-label">岗位：</td>
                <td><input type="text" value="${(employees.jobName)!}"
                           readonly/></td>
            </tr>
            <tr>
                <td colspan="6" style="text-align: center">
                    <input type="button" value="设置新头像" data-dismiss="modal" onclick="updateClick()"/>
                </td>
            </tr>
        </table>
        </form>
    </div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
    </div>
</main>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    var _URL = window.URL || window.webkitURL;

    function preview(file) {
        var prevDiv = document.getElementById('preview');
        if (file.files && file.files[0]) {
            var reader = new FileReader();
            reader.onload = function (evt) {
                prevDiv.innerHTML = '<img id="imgnode" class="per-img" style="width: 100px;height: 100px" src="' + evt.target.result + '" />';
                console.log(evt.target.result)
            }
            reader.readAsDataURL(file.files[0]);
        } else {
            prevDiv.innerHTML = '<div class="img" id="imgnode"  style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';
        }
    }

    function updateClick() {
        let form = document.forms.namedItem("form_user");
        let formData = new FormData(form);
        $.ajax({
            url: "/updateUserHeadShot.do",
            type: "POST",
            data: formData,
            dataType: "json",
            contentType: false, //必须
            processData: false, //必须
            cache: false,
            success: function (result) {
                alert(result.msg);
                location.reload();
            },
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });

    }

    function updatePassword() {
        $("#password").attr("readOnly", false);
        $("#password").val("");
        document.getElementById("savePasswordBtn").style.display = "block";
        document.getElementById("updatePasswordBtn").style.display = "none";
    }
    function savePassword() {
        $.ajax({
            url: "/updateUserPassword.do",
            type: "POST",
            data: {
                password:$("#password").val()
            },
            dataType: "json",
            cache: false,
            success: function (result) {
                if(result.code === 1){
                    document.getElementById("savePasswordBtn").style.display = "none";
                    document.getElementById("updatePasswordBtn").style.display = "block";
                    $("#password").val("*************");
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