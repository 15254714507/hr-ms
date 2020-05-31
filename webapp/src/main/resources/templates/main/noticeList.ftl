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
            url: "/getNoticeList.do",
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
                    title: "标题",
                    field: 'title',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '截止时间',
                    field: 'deadline',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '创建时间',
                    field: 'createTime',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '操作',
                    field: 'opr',
                    width: 180,
                    align: 'center',
                    formatter: function (cellval, row) {
                        var d = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="seeNoticeContent(\'' + row.id + '\')">查看正文</button> ';
                        var e = '<button  id="add" data-id="98" class="btn btn-xs btn-primary" onclick="deleteNotice(\'' + row.id + '\')">删除</button> ';
                        return d + e;
                    }
                }
            ]
        });
    }

    function seeNoticeContent(id) {
        $("#iframe").load("/gotoNoticeContent.do?id=" + id);
        $("#modal").modal('show')
    }

    function deleteNotice(id) {
        $.ajax({
            url: "/deleteNotice.do",
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
