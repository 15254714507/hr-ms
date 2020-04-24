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
    <div id="modal" class="modal">
        <div class="modal-content">
            <header class="modal-header">
                <h4>原因：</h4>
                <div class="close" onclick="modal_close()">x</div>
            </header>
            <form id="audit_form">
                <input id="id" name="id" style="display: none"/>
                <div class="modal-body">
                    <label>
                        <textarea name="approvalComments" cols="70" rows="5"></textarea>
                    </label>
                </div>
            </form>
            <footer class="modal-footer">
                <button onclick="saveApprovalComments()">确认</button>
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
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        if (row.approvalStatus === "审核不通过") {
                            var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="pass(\'' + row.id + '\')">编辑</button> ';
                            var c = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="fail(\'' + row.id + '\')">重新提交审核</button> ';
                            return d + c;
                        }
                        return null;

                    }
                }
            ]
        });
    }

    // //同意
    // function pass(id) {
    //     $.ajax({
    //         url: "/passNewEmployees.do",
    //         type: "POST",
    //         cache: false,
    //         data: {
    //             id: id
    //         },
    //         success: function (result) {
    //             if (result.code === 1) {
    //                 alert(result.msg);
    //                 location.reload();
    //             } else {
    //                 alert(result.msg)
    //             }
    //         },
    //         error: function () {
    //             alert("连接服务器异常，请刷新后重试")
    //         }
    //     });
    // }
    //
    //
    // // let close = document.getElementById('modal_close');
    // //不同意通过就打开弹出框写明原因
    // function fail(id) {
    //     document.getElementById("id").value = id;
    //     modal.style.display = "block";
    // }
    //
    // //关闭弹出框
    // function modal_close() {
    //     modal.style.display = "none";
    // }
    //
    // //不同意，需要提交不同意的原因，让别人知道为什么
    // function saveApprovalComments() {
    //     $.ajax({
    //         url: "/failNewEmployees.do",
    //         type: "POST",
    //         cache: false,
    //         data: $("#audit_form").serialize(),
    //         dataType: "json",
    //         success: function (result) {
    //             if (result.code === 1) {
    //                 modal.style.display = "none";
    //                 alert(result.msg);
    //                 location.reload();
    //             } else {
    //                 alert(result.msg)
    //             }
    //         },
    //         error: function () {
    //             alert("连接服务器异常，请刷新后重试")
    //         }
    //     });
    // }
</script>
</body>
</html>
