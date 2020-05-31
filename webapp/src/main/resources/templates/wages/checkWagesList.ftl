<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>工资核对</title>
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
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>工资信息核对列表</span></div>
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
            url: "/getCheckWagesList.do",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
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
                    title: '姓名',
                    field: 'name',
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
                    title: "时间",
                    field: 'wagesDate',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        let date = new Date(cellval);
                        let year = date.getFullYear();
                        let month = date.getMonth() + 1;
                        return year.toString() + "-" + month.toString();
                    }
                },
                {
                    title: '状态',
                    field: 'status',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (cellval === 0) {
                            return "核对考勤";
                        } else {
                            return "异常";
                        }
                    }
                },
                {
                    title: '操作',
                    field: 'status',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        let d = null;
                        if (cellval === 0) {
                            d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="checkWages(\'' + row.id + '\')">核对</button> ';
                        } else {
                            d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="reedWages(\'' + row.id + '\')">重新编辑</button> ';
                        }
                        return d;
                    }
                }
            ]
        });
    }

    //打开工资信息核对页面
    function checkWages(id) {
        $("#iframe").load("/gotoCheckWages.do?id=" + id);
        $("#modal").modal('show')
    }

    //重新编辑工资信息
    function reedWages(id) {
        $("#iframe").load("/gotoReedWages.do?id=" + id);
        $("#modal").modal('show')
    }

</script>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
</body>
</html>