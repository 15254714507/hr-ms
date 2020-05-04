<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>假期申请页</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="find-top1">
        <form action="" id="form_new_vacation">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">职员编号：</td>
                    <td><input type="text" id="username" name="username" onchange="setUserName()"
                               onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/></td>
                    <td class="top-table-label">姓名：</td>
                    <td><input type="text" id="name" name="name" readonly/></td>
                    <td class="top-table-label">请假类型：</td>
                    <td><select name="type">
                            <option value="0">调休</option>
                            <option value="1">事假</option>
                            <option value="2">病假</option>
                        </select></td>
                </tr>
                <tr>
                    <td class="top-table-label">开始时间：</td>
                    <td><input type="date" id="startDate" name="startDate" onchange="computeDate()"></td>
                    <td class="top-table-label">结束时间：</td>
                    <td><input type="date" id="endDate" name="endDate" onchange="computeDate()"></td>
                    <td class="top-table-label">天数：</td>
                    <td><input type="text" id="days" name="days" readonly></td>
                </tr>
                <tr>
                    <td class="top-table-label">理由</td>
                    <td colspan="5"><textarea name="description"></textarea></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="申请" data-dismiss="modal" onclick="saveClick()"/>
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
<script type="text/javascript">
    //添加新的假期申请
    function saveClick() {
        $.ajax({
            url: "/saveNewVacation.do",
            type: "POST",
            cache: false,
            data: $("#form_new_vacation").serialize(),
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    alert(result.msg);
                    location.reload();
                } else {
                    alert(result.msg)
                }
            },
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }

    //计算请假天数
    function computeDate() {
        let startDate = $("#startDate").val();
        let endDate = $("#endDate").val();
        if (startDate == null || startDate == '') {
            return;
        }
        if (endDate == null || endDate == '') {
            return;
        }
        if (endDate < startDate) {
            $("#endDate").val("");
            alert("开始日期大于结束日期");
            return;
        }
        let timeInterval = new Date(endDate).getTime() - new Date(startDate).getTime();
        let days = parseInt(timeInterval / (1000 * 60 * 60 * 24));
        $("#days").val(days + 1);
    }

    //根据员工编码获得姓名
    function setUserName() {
        $.ajax({
            url: "/getVacationUserName.do",
            type: "POST",
            cache: false,
            data: {
                username: $("#username").val()
            },
            success: function (result) {
                if (result.code === 1) {
                    if (result.object != null) {
                        $("#name").val(result.object);
                    } else {
                        $("#name").val();
                    }
                } else {
                    alert(result.msg);
                }
            }
            ,
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        })
        ;
    }
</script>
</body>
</html>