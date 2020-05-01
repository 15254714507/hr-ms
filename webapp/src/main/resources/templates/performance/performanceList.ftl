<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>绩效认定页面</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
    <style>
        h4 {
            margin-left: 20px;
            color: white;
        }

        p {
            text-align: center;
        }


        .btn:hover, .btn:focus {
            background-color: #95b4ed;
        }

        .modal {
            display: none;
            width: 100%;
            height: 100%;
            position: fixed;
            left: 0;
            top: 0;
            z-index: 1000;
            background-color: rgba(0, 0, 0, 0.5);
        }

        .modal-content {
            display: flex;
            flex-flow: column nowrap;
            justify-content: space-between;
            width: 50%;
            max-width: 700px;
            height: auto;
            max-height: 500px;
            margin: 100px auto;
            border-radius: 10px;
            background-color: #fff;
            -webkit-animation: zoom 0.6s;
            animation: zoom 0.6s;
            resize: both;
            overflow: auto;
        }

        @-webkit-keyframes zoom {
            from {
                -webkit-transform: scale(0)
            }
            to {
                -webkit-transform: scale(1)
            }
        }

        @keyframes zoom {
            from {
                transform: scale(0)
            }
            to {
                transform: scale(1)
            }
        }

        .modal-header {
            box-sizing: border-box;
            background-color: rgb(23, 65, 91);
            border-bottom: 1px solid #ccc;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .close {
            color: #b7b7b7;
            font-size: 30px;
            font-weight: bold;
            margin-right: 20px;
            transition: all 0.3s;
        }

        .close:hover, .close:focus {
            color: #95b4ed;
            text-decoration: none;
            cursor: pointer;
        }

        .modal-body {
            padding: 10px;
            font-size: 16px;
            box-sizing: border-box;
            height: auto;
        }

        .modal-footer {
            box-sizing: border-box;
            /*    border-top:1px solid #ccc;
            */
            display: flex;
            padding: 15px;
            justify-content: flex-end;
            align-items: center;
        }

        .modal-footer button {
            width: 60px;
            height: 35px;
            padding: 5px;
            box-sizing: border-box;
            margin-right: 10px;
            font-size: 16px;
            color: white;
            border-radius: 5px;
            background-color: cornflowerblue;
        }

        .modal-footer button:hover, .modal-footer button:focus {
            background-color: #95b4ed;
            cursor: pointer;
        }

        @media only screen and (max-width: 700px) {
            .modal-content {
                width: 80%;
            }
        }
    </style>
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>添加绩效考核目标</span></div>
    <div class="table-con">
        <table id="table" class="table-style"></table>
    </div>
    <div id="modal" class="modal">
        <div class="modal-content">
            <header class="modal-header">
                <h4>考核项：</h4>
                <div class="close" onclick="modal_close()">x</div>
            </header>
            <div class="modal-body">
                <label>
                    <textarea id="text" cols="70" rows="5" readonly></textarea>
                </label>
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
            url: "/getPerformanceList.do",
            dataType: "json",
            pagination: true, //分页
            pageSize: 10,
            pageNumber: 1,
            search: true, //显示搜索框
            contentType: "application/x-www-form-urlencoded",
            queryParams: null,
            sortName: "createTime",
            sortStable: true,
            sortOrder: "desc",
            columns: [
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
                    title: '日期',
                    field: 'yearMonth',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '绩效审核人',
                    field: 'auditUser',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '考核项内容',
                    field: 'goal',
                    visible: false
                },
                {
                    title: 'KPI',
                    field: 'kpi',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '考核项',
                    field: 'Ope',
                    width: 180,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (row.goal != null && row.goal !== '') {
                            return '<button  id="see" data-id="98" class="btn btn-xs btn-primary" onclick="see(\'' + row.goal + '\')">查看</button> ';
                        }
                        return "待定";
                    }
                },
                {
                    title: '绩效的状态',
                    field: 'status',
                    visible: false
                },
                {
                    title: '状态',
                    field: 'ope1',
                    width: 180,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        if (row.status === false) {
                            return "待定考核项";
                        }
                        if (row.status === true) {
                            if (row.auditUser == null) {
                                return "待定KPI";
                            } else {
                                return "已完成";
                            }
                        }
                        return "未知"
                    }
                },
                {
                    title: '创建时间',
                    field: 'createTime',
                    visible: false
                },
            ]
        });
    }

    //只是查看考核项
    function see(goal) {
        document.getElementById("text").value = goal;
        modal.style.display = "block";
    }

    //关闭弹出框
    function modal_close() {
        modal.style.display = "none";
    }
</script>
</body>
</html>