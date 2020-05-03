<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>申请假期列表</title>
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

<body style="background-color: #ecf0f5;font-family: 微软雅黑;color: #475059;min-width: 1000px;overflow: auto">
<div class="notice_main">
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>假期申请列表</span></div>
    <div style=>
        <button class="add-but btn-lg" onclick="addNewVacation()"><i class="glyphicon glyphicon-edit"></i>申请假期</button>
    </div>
    <div class="table-con">
        <table id="table" class="table-style"></table>
    </div>
    <div id="modal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
        <div  class="modal-dialog modal-lg" role="document">
            <div id="iframe" class="modal-content">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        table();

    })

    function table() {
        $('#table').bootstrapTable({
            method: "POST",
            striped: false,
            singleSelect: false,
            url: "/getVacationList.do",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: false, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            sortName: "createTime",
            sortStable: true,
            sortOrder: "desc",
            columns: [
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
                    title: '姓名',
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '开始日期',
                    field: 'startDate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '结束日期',
                    field: 'endDate',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "daysOfRecess",
                    field: 'daysOfRecess',
                    visible: false
                },
                {
                    title: "daysOfLeave",
                    field: 'daysOfLeave',
                    visible: false
                },
                {
                    title: "daysOfSickLeave",
                    field: 'daysOfSickLeave',
                    visible: false
                },
                {
                    title: "daysOfAbsenteeism",
                    field: 'daysOfAbsenteeism',
                    visible: false
                },
                {
                    title: '类型',
                    field: 'sex',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (row.daysOfRecess != null && row.daysOfRecess > 0) {
                            return "调休";
                        }
                        if (row.daysOfLeave != null && row.daysOfLeave > 0) {
                            return "事假";
                        }
                        if (row.daysOfSickLeave != null && row.daysOfSickLeave > 0) {
                            return "病假";
                        }
                        if (row.daysOfAbsenteeism != null && row.daysOfAbsenteeism > 0) {
                            return "旷工";
                        }
                    }
                },
                {
                    title: '请假天数',
                    field: 'dayNum',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (row.daysOfRecess != null && row.daysOfRecess > 0) {
                            return row.daysOfRecess;
                        }
                        if (row.daysOfLeave != null && row.daysOfLeave > 0) {
                            return row.daysOfLeave;
                        }
                        if (row.daysOfSickLeave != null && row.daysOfSickLeave > 0) {
                            return row.daysOfSickLeave;
                        }
                        if (row.daysOfAbsenteeism != null && row.daysOfAbsenteeism > 0) {
                            return row.daysOfAbsenteeism;
                        }
                    }
                },
                {
                    title: '说明',
                    field: 'description',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '审核人',
                    field: 'auditUser',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: "status",
                    field: 'status',
                    visible: false
                },
                {
                    title: '状态',
                    field: 'auditStatus',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (row.status) {
                            return "通过";
                        } else {
                            return "未审核";
                        }
                    }
                },
                {
                    title: "createTime",
                    field: 'createTime',
                    visible: false
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="add(\'' + row.id + '\')">通过</button> ';
                        var c = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="add(\'' + row.id + '\')">删除</button> ';

                        return d + c;
                    }
                }
            ]
        });
    }
    function addNewVacation() {
        $("#iframe").load("/gotoAddNewVacation.do");
        $("#modal").modal('show')
    }
</script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>