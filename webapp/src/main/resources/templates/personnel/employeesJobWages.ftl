<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>职员岗位薪资列表</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">

</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>职员岗位薪资列表</span></div>
    <div class="table-con">
        <table id="table" class="table-style"></table>
    </div>

    <div id="modal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog"
         aria-labelledby="myLargeModalLabel">
        <div class="modal-dialog modal-lg" role="document">
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
            url: "/getEmployeesWagesList.do",
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
                    field: 'employees.id',
                    visible: false
                },
                {
                    title: "员工编码",
                    field: 'employees.username',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '姓名',
                    field: 'employees.name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '部门名称',
                    field: 'employees.departmentName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '岗位名称',
                    field: 'employees.jobName',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '基础工资',
                    field: 'baseSalary',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '绩效工资',
                    field: 'performanceSalary',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'userJobId',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        return '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="editEmployeesJobWages(\'' + cellval + '\')">修改</button> ';

                    }
                }
            ]
        });
    }

    function editEmployeesJobWages(id) {
        $("#iframe").load("/gotoUpdateEmployeesJobWages.do?id="+id);
        $("#modal").modal('show')
    }
</script>
</body>
</html>
