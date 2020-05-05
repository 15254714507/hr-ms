<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改考勤异常</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="find-top1">
        <form action="" id="form_sign">
            <input name="id" style="display: none" value="${(sign.id)!}">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">职员编号：</td>
                    <td><input type="text" id="username" name="username" value="${(sign.username)!}" readonly/></td>
                    <td class="top-table-label">上班签到时间：</td>
                    <td><input type="datetime-local" id="workTime" name="workTime" value="${(sign.workTime)!}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td class="top-table-label">下班签到时间：</td>
                    <td><input type="datetime-local" id="getOffWork" name="getOffWork" value="${(sign.getOffWork)!}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td class="top-table-label">原因：</td>
                    <td colspan="3">
                        <input type="radio" name="reason" value="调休">调休 &nbsp;&nbsp;
                        <input type="radio" name="reason" value="事假">事假 &nbsp;&nbsp;
                        <input type="radio" name="reason" value="病假">病假 &nbsp;&nbsp;
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="修改" data-dismiss="modal" onclick="updateClick()"/>
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
    function updateClick() {
        $.ajax({
            url: "/updateAttendanceException.do",
            type: "POST",
            cache: false,
            data: $("#form_sign").serialize(),
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