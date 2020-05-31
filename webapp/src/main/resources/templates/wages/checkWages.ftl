<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>核对工资信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div style="text-align:center"><h4>考勤列表</h4></div>
    <div class="table-con">
        <table id="table2" class="table-style"></table>
    </div>
    <div class="find-top1">
        <form action="" id="form_wages">
            <input name="id" value="${(wages.id)}" style="display: none">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">职员编号：</td>
                    <td><input type="text" id="username" value="${(wages.username)!}" readonly/></td>
                    <td class="top-table-label">姓名：</td>
                    <td><input type="text" id="name" value="${(wages.name)!}" readonly/></td>
                    <td class="top-table-label">基础工资：</td>
                    <td><input type="text" value="${(wages.baseSalary)!}"></td>
                </tr>
                <tr>
                    <td class="top-table-label">绩效工资：</td>
                    <td><input type="text" value="${(wages.performanceSalary)!}"></td>
                    <td class="top-table-label">调休：</td>
                    <td><input type="text" id="daysOfRecess" name="daysOfRecess" value="0"></td>
                    <td class="top-table-label">事假：</td>
                    <td><input type="text" id="daysOfLeave" name="daysOfLeave" value="0"></td>
                </tr>
                <tr>
                    <td class="top-table-label">病假：</td>
                    <td><input type="text" name="daysOfSickLeave" value="0"></td>
                    <td class="top-table-label">旷工：</td>
                    <td><input type="text" id="daysOfAbsenteeism" name="daysOfAbsenteeism" value="0"></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="确认" data-dismiss="modal" onclick="saveClick()"/>
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
    $(function () {
        table2();
    })


    function table2() {
        $('#table2').bootstrapTable({
            method: "POST",
            striped: false,
            singleSelect: false,
            url: "/getAttendanceLastMonthList.do?",
            date: {
                username: $("#username")
            },
            dataType: "json",
            pagination: true, //分页
            pageSize: 5,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            sortName: "createTime",
            sortStable: true,
            sortOrder: "asc",
            columns: [
                {
                    title: '序号',//标题  可不加
                    formatter: function (value, row, index) {
                        return index+1;
                    }
                },
                {
                    title: "id",
                    field: 'id',
                    visible: false
                },
                {
                    title: "编号",
                    field: 'username',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '上班时间',
                    field: 'workTime',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        return cellval.replace("T"," ");
                    }
                },
                {
                    title: '下班时间',
                    field: 'getOffWork',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        return cellval.replace("T"," ");
                    }
                },
                {
                    title: '加班时长',
                    field: 'workOvertime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '说明',
                    field: 'description',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "createTime",
                    field: 'createTime',
                    visible: false
                },
            ]
        });
    }

    //添加新的假期申请
    function saveClick() {
        $.ajax({
            url: "/checkWages.do",
            type: "POST",
            cache: false,
            data: $("#form_wages").serialize(),
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
</script>
</body>
</html>