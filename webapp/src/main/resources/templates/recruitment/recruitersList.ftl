<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>应聘者列表</title>
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
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>应聘者列表</span></div>
    <div style=>
        <button class="add-but btn-lg" onclick="addNewRecruitmentNeeds()"><i class="glyphicon glyphicon-edit"></i>添加应聘者
        </button>
    </div>
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
            url: "/getRecruitersList.do",
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
                    title: "姓名",
                    field: 'name',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '应聘岗位',
                    field: 'positions',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '类型',
                    field: 'type',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: 'createTime',
                    field: 'createTime',
                    visible: false
                },
                {
                    title: '操作',
                    field: 'resumeId',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        if (!row.status) {
                            let d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="down(\'' + cellval + '\')">下载简历</button> ';
                            let c = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="deleteRecruiters(\'' + row.id + '\')">删除</button> ';
                            return d + c;
                        }
                    }
                }
            ]
        });
    }

    //打开添加新招聘者的信息
    function addNewRecruitmentNeeds() {
        $("#iframe").load("/gotoAddRecruiters.do");
        $("#modal").modal('show')
    }

    //查看招聘信息的职责和要求
    function down(id) {
        window.location.href = "/files/down?id=" + id;
    }

    //删除此招聘信息
    function deleteRecruiters(id) {
        $.ajax({
            url: "/deleteRecruiters.do",
            type: "POST",
            cache: false,
            data: {
                id: id
            },
            success: function (result) {
                if (result.code === 1) {
                    location.reload();
                }
                alert(result.msg);
            }
            ,
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