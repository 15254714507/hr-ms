<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>入职</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>审核新职员页面</span></div>
    <div class="table-con">
        <table id="table" class="table-style"></table>
    </div>
</main>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script>
    $(function () {
        table();

    })

    function table() {
        $('#table').bootstrapTable({
            method: "get",
            striped: false,
            singleSelect: false,
            url: "/getRegisterNewEmployeeList.do",
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
                    title: "员工编码",
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
                    title: '性别',
                    field: 'gender',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '学历',
                    field: 'degree',
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
                    title: '职位',
                    field: 'jobName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '审核状态',
                    field: 'approvalStatus',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="pass(\'' + row.id + '\')">通过</button> ';
                        var c = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="fail(\'' + row.id + '\')">不通过</button> ';

                        return d + c;
                    }
                }
            ]
        });
    }

    //同意
    function pass(id) {
        $.ajax({
            url: "/passNewEmployees.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
            success: function (result) {
                if (result.code === 1) {
                    location.reload();
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

    //不同意
    function fail(id) {
        // $.ajax({
        //     url: "/failNewEmployees.do",
        //     type: "POST",
        //     cache: false,
        //     data: {
        //         id: id
        //     },
        //     success: function (result) {
        //         if (result.code === 1) {
        //             alert(result.msg);
        //         } else {
        //             alert(result.msg)
        //         }
        //     },
        //     error: function () {
        //         alert("连接服务器异常，请刷新后重试")
        //     }
        // });
    }
</script>
</body>
