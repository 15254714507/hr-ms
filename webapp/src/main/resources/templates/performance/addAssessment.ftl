<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>添加考核项</title>
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
            <form id="add_form">
                <input id="performance_id" name="id" style="display: none"/>
                <div class="modal-body">
                    <label>
                        <textarea id="text" name="goal" cols="70" rows="5"></textarea>
                    </label>
                </div>
            </form>
            <footer class="modal-footer">
                <button id="btn" onclick="savePerformanceGoal()" style="display: none">提交</button>
            </footer>
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
            url: "/getNotAddPerformanceList.do",
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
                    field: 'performanceId',
                    visible: false
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
                    title: '日期',
                    field: 'yearMonth',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '考核项内容',
                    field: 'goal',
                    visible: false
                },
                {
                    title: '状态',
                    field: 'status',
                    width: 180,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        let status = null;
                        if (!row.status) {
                            status = "考核项未添加";
                        } else {
                            status = "考核项已添加";
                        }
                        return status;
                    }
                },
                {
                    title: '考核项',
                    field: 'Ope',
                    width: 180,
                    align: 'center',
                    valign: 'middle',
                    formatter: function (cellval, row) {
                        let d = null;
                        if (row.status) {
                            d = '<button  id="see" data-id="98" class="btn btn-xs btn-primary" onclick="see(\'' + row.goal + '\')">查看考核项</button> ';
                        } else {
                            d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="add(\'' + row.performanceId + '\')">添加考核项</button> ';
                        }
                        return d;
                    }
                },
            ]
        });
    }

    //只是查看考核项
    function see(goal) {
        document.getElementById("text").value = goal;
        document.getElementById("btn").style.display = "none";
        modal.style.display = "block";
    }

    //添加考核项，提交按钮打开
    function add(id) {
        document.getElementById("performance_id").value = id;
        document.getElementById("btn").style.display = "block";
        modal.style.display = "block";
    }

    //关闭弹出框
    function modal_close() {
        modal.style.display = "none";
    }

    //提交考核项
    function savePerformanceGoal() {
        $.ajax({
            url: "/savePerformanceGoal.do",
            type: "POST",
            cache: false,
            data: $("#add_form").serialize(),
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    modal.style.display = "none";
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