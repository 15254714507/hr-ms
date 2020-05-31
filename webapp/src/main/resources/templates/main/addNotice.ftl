<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加通知页面</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i>所在位置：<span>添加新的通知</span></div>
    <div class="find-top1">
        <form action="" id="form_notice">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">标题：</td>
                    <td><input type="text" name="title"/></td>
                </tr>
                <tr>
                    <td class="top-table-label">内容：</td>
                    <td colspan="10"><textarea id="content" name="content"
                                               placeholder="长度限制在255个字符以内。"
                                               onKeyDown="checkLength()"
                                               onKeyUp="checkLength()" onPaste="checkLength()"
                                               maxlength='255'></textarea>
                        <div>还可以输入<span id="validNum">255</span>字</div>
                    </td>
                </tr>

                <tr>
                    <td class="top-table-label">截止时间：</td>
                    <td><input name="deadline" type="date"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="添加新通知" data-dismiss="modal" onclick="saveClick()"/>
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
    function checkLength() {
        var value = document.getElementById("content").value;
        if (value.length > 140) {
            document.getElementById("content").value = document.getElementById("content").value.substr(0, 140);
        } else {
            document.getElementById("validNum").innerHTML = 140 - value.length;
        }
    }

    function saveClick() {
        $.ajax({
            url: "/saveNotice.do",
            type: "POST",
            data: $("#form_notice").serialize(),
            dataType: "json",
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
        document.getElementById("savePassword").style.display = "block";
        document.getElementById("updatePassword").style.display = "none";
    }

    function savePassword() {
        $.ajax({
            url: "/updateUserPassword.do",
            type: "POST",
            data: {
                password: $("#password").val()
            },
            dataType: "json",
            cache: false,
            success: function (result) {
                if (result.code === 1) {
                    document.getElementById("savePassword").style.display = "none";
                    document.getElementById("updatePassword").style.display = "block";
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