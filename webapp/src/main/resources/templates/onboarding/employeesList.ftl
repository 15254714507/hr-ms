<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>入职员工列表</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">

</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>入职列表页面</span></div>
    <div class="table-con">
        <table id="table" class="table-style"></table>
    </div>

    <div id="modal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div  class="modal-dialog modal-lg" role="document">
            <div id="iframe" class="modal-content">
            </div>
        </div>
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
            method: "POST",
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
            sortName: "updateTime",
            sortStable: true,
            sortOrder: "desc",
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
                    title: '原因',
                    field: 'approvalComments',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '审核人',
                    field: 'approvalUser',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '时间',
                    field: 'updateTime',
                    visible: false
                },

                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        if (row.approvalStatus === "审核不通过") {
                            var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="editEmployees(\'' + row.id + '\')">编辑</button> ';
                            var c = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="resubmit(\'' + row.id + '\')">重新提交</button> ';
                            var e = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="deleteEmployees(\'' + row.id + '\')">删除</button> ';
                            return d + c + e;
                        }
                        return null;

                    }
                }
            ]
        });
    }

    function editEmployees(id) {
        $("#iframe").load("/gotoUpdateEmployees.do?id="+id);
        $("#modal").modal('show')
    }
    function resubmit(id) {
            $.ajax({
                url: "/resubmitNewEmployees.do",
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
    function deleteEmployees(id) {
            $.ajax({
                url: "/deleteNewEmployees.do",
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
