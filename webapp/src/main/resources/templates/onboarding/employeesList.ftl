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
<#--                <div id="iframe" class="find-top1">-->
<#--                    <form action="" id="form_new_employees">-->
<#--                        <table class="top-table">-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">职员编号：</td>-->
<#--                                <td><input type="text" name="username" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/></td>-->
<#--                                <td class="top-table-label">职员姓名：</td>-->
<#--                                <td><input type="text" name="name" onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/></td>-->
<#--                                <td class="top-table-label">出生年月：</td>-->
<#--                                <td><input type="date" name="dateOfBirth"/></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">联系电话：</td>-->
<#--                                <td><input type="text" name="phone"></td>-->
<#--                                <td class="top-table-label">联系邮箱：</td>-->
<#--                                <td><input type="text" name="email"></td>-->
<#--                                <td class="top-table-label">常住地址：</td>-->
<#--                                <td><input type="text" name="address"></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">证件号码：</td>-->
<#--                                <td><input type="text" name="identityCard"></td>-->
<#--                                <td class="top-table-label">证件类型：</td>-->
<#--                                <td><select name="identityType">-->
<#--                                        <option>第二代身份证</option>-->
<#--                                        <option>护照</option>-->
<#--                                    </select></td>-->
<#--                                <td class="top-table-label">性别：</td>-->
<#--                                <td><input type="radio" checked="checked" name="gender" value="0"><span>男</span>-->
<#--                                    <input type="radio" name="gender" value="1"><span>女</span>-->
<#--                                </td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">学历：</td>-->
<#--                                <td><select name="degree">-->
<#--                                        <option>本科</option>-->
<#--                                        <option>研究生</option>-->
<#--                                        <option>博士</option>-->
<#--                                    </select></td>-->
<#--                                <td class="top-table-label">专业：</td>-->
<#--                                <td><input type="text" name="professional"></td>-->
<#--                                <td class="top-table-label">毕业院校：</td>-->
<#--                                <td><input type="text" name="university"></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">毕业时间：</td>-->
<#--                                <td><input type="date" name="graduationDate"></td>-->
<#--                                <td class="top-table-label">工作时间：</td>-->
<#--                                <td><input type="date" name="firstWorkDate"></td>-->
<#--                                <td class="top-table-label">工作年限：</td>-->
<#--                                <td><input type="text" name="workYears"></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">国籍：</td>-->
<#--                                <td><select name="nationality">-->
<#--                                        <option>中国</option>-->
<#--                                        <option>外国</option>-->
<#--                                    </select></td>-->
<#--                                <td class="top-table-label">民族：</td>-->
<#--                                <td><select name="national">-->
<#--                                        <option value="">请选择</option>-->
<#--                                        <option value="汉族">汉族</option>-->
<#--                                        <option value="少数民族">少数民族</option>-->

<#--                                    </select></td>-->
<#--                                <td class="top-table-label">籍贯：</td>-->
<#--                                <td><input type="text" name="nativePlace"></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">户籍地址：</td>-->
<#--                                <td><input type="text" name="censusRegister"></td>-->
<#--                                <td class="top-table-label">入职部门：</td>-->
<#--                                <td><select name="departmentId" onChange="department_job(this)">-->
<#--                                        <option value="">选择部门</option>-->
<#--                                        <#list departmentJobList as departmentJob>-->
<#--                                            <option value="${departmentJob.departmentId}">${departmentJob.departmentName}</option>-->
<#--                                        </#list>-->
<#--                                    </select></td>-->
<#--                                <td class="top-table-label">工作岗位：</td>-->
<#--                                <td><select name="jobName" id="jobs">-->
<#--                                        <option value="">选择岗位</option>-->
<#--                                    </select></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">入职时间：</td>-->
<#--                                <td><input type="date" name="employmentDate"></td>-->
<#--                                <td class="top-table-label">实习时间：</td>-->
<#--                                <td><input type="date" name="internshipDate"></td>-->
<#--                                <td class="top-table-label">员工类型：</td>-->
<#--                                <td><select name="typesOfEmployees">-->
<#--                                        <option>全职</option>-->
<#--                                        <option>实习</option>-->
<#--                                    </select></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td class="top-table-label">基本工资：</td>-->
<#--                                <td><input type="text" name="baseSalary"></td>-->
<#--                                <td class="top-table-label">绩效工资：</td>-->
<#--                                <td><input type="text" name="performanceSalary"></td>-->
<#--                            </tr>-->
<#--                            <tr>-->
<#--                                <td colspan="6" style="text-align: center">-->
<#--                                    <input type="button" value="添加" onclick="saveClick()"/>-->
<#--                                </td>-->
<#--                            </tr>-->
<#--                        </table>-->
<#--                    </form>-->
<#--                </div>-->
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
    }
    function deleteEmployees(id) {
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
