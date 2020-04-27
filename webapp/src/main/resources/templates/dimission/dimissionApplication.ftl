<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>离职申请页面</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.step.css"/>
    <link href="css/table.css" rel="stylesheet" type="text/css">
    <script src="js/jquery.js"></script>
    <script src="js/jquery.step.min.js"></script>
    <link rel="stylesheet" href="css/all.css">

    <style>
        html, body {
            height: 100%
        }

        button {
            display: inline-block;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            text-align: center;
            cursor: pointer;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #fff;
            background-color: #5bc0de;
        }

        .main {
            width: 90%;
            margin: 100px auto;
        }

        #step {
            margin-bottom: 60px;
        }

        .btns {
            float: left;
        }

        .info {
            float: left;
            height: 34px;
            line-height: 34px;
            margin-left: 40px;
            font-size: 28px;
            font-weight: bold;
            color: #928787;
        }

        .info span {
            color: red;
        }
    </style>
</head>

<body style="background-color: #ecf0f5;font-family: 微软雅黑,serif;color: #475059;min-width: 1000px;overflow: auto">
<div class="notice_main">
    <br/>
    <div class="main">
        <div id="step"></div>
    </div>
    <div class="find-top1">
        <form id="dimission_form">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">员工编码：</td>
                    <td><input type="text" id="username" name="username" onchange="dimissionUser()"
                               onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"></td>
                    <td class="top-table-label">姓名：</td>
                    <td><input type="text" id="name" name="name" readonly></td>
                    <td class="top-table-label">部门：</td>
                    <td><input type="text" id="departmentName" name="departmentName" readonly></td>
                </tr>
                <tr>
                    <td class="top-table-label">岗位：</td>
                    <td><input type="text" id="jobName" name="jobName" readonly></td>
                    <td class="top-table-label">入职时间：</td>
                    <td><input type="date" id="dateOfEntry" name="dateOfEntry" readonly></td>
                </tr>
                <tr>
                    <td class="top-table-label">离职原因</td>
                    <td colspan="5"><textarea id="reasonsForSeparation" name="reasonsForSeparation"></textarea></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input id="btn" type="button" class="glyphicon glyphicon-edit" onclick="saveDimissionUser()"
                               value="提交">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    var $step = $("#step");
    var $index = $("#index");

    $step.step({
        index: 0,
        time: 500,
        title: ["填写离职原因", "待审核", "已审核", "打印离职证明", "完成"]
    });
    $index.text($step.getIndex());

    function dimissionUser() {
        $.ajax({
            url: "/getDimissionUserMsg.do",
            type: "POST",
            cache: false,
            data: {
                username: $("#username").val()
            },
            success: function (result) {
                if (result.code === 1) {
                    if (result.object != null) {
                        let dimissionUser = result.object;
                        $index.text(dimissionUser.steps);
                        $("#name").val(dimissionUser.name);
                        $("#departmentName").val(dimissionUser.departmentName);
                        $("#jobName").val(dimissionUser.jobName);
                        $("#dateOfEntry").val(dimissionUser.dateOfEntry);
                        if (dimissionUser.steps !== 0) {
                            $("#reasonsForSeparation").val(dimissionUser.reasonsForSeparation).prop("readonly", true);
                            $("#btn").hide();
                        } else {
                            $("#reasonsForSeparation").val("").prop("readonly", false);
                            $("#btn").show();
                        }
                        $step.toStep(dimissionUser.steps);
                        $index.text(dimissionUser.steps);
                    }
                } else {
                    alert(result.msg)
                }
            }
            ,
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        })
        ;
    }

    function saveDimissionUser() {

        $.ajax({
            url: "/saveDimissionUser.do",
            type: "POST",
            cache: false,
            data: $("#dimission_form").serialize(),
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    $("#btn").hide();
                    $("#reasonsForSeparation").prop("readonly", true);
                    $step.nextStep();
                    $index.text(1);
                    alert(result.msg);
                } else {
                    alert(result.msg)
                }
            },
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }
</script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>