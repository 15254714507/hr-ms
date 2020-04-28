<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>离职删除用户</title>
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

        .info span {
            color: red;
        }
    </style>
</head>

<body style="background-color: #ecf0f5;font-family: 微软雅黑;color: #475059;min-width: 1000px;overflow: auto">
<div class="notice_main">
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>删除已离职员工账号信息</span></div>

    <div class="table-con">
        <table id="table" class="table-style"></table>
    </div>

</div>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    $(function () {
        table();

    })

    function table() {
        $('#table').bootstrapTable({
            method: "POST",
            striped: false,
            singleSelect: false,
            url: "/getDeleteUserList.do",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            columns: [
                {
                    title: "id",
                    field: 'id',
                    visible: false
                },
                {
                    title: "员工编号",
                    field: 'username',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '姓名',
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '部门',
                    field: 'departmentName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '岗位',
                    field: 'jobName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="deleteUser(\'' + row.id + '\')">删除账号</button> ';
                        return d;
                    }
                }
            ]
        });
    }

    function deleteUser(id) {
        $.ajax({
            url: "/deleteUser.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
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