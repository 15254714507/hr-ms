<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改入职员工信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>修改员工岗位和薪资</span></div>
    <div class="find-top1">
        <form action="" id="form_employees">
            <input id="id" name="id" value="${(userJob.id)!}" style="display: none"/>
            <input id="id" name="userId" value="${(userJob.userId)!}" style="display: none"/>
            <table class="top-table">

                <tr>
                    <td class="top-table-label">新部门：</td>
                    <td><select onChange="department_job(this)">
                            <option value="">选择部门</option>
                            <#list departmentJobList as departmentJob>
                                <option value="${departmentJob.departmentId}">${departmentJob.departmentName}</option>
                            </#list>
                        </select></td>
                    <td class="top-table-label">新岗位：</td>
                    <td><select name="jobId" id="jobs">
                        </select></td>
                </tr>
                <tr>
                    <td class="top-table-label">基本工资：</td>
                    <td><input type="text" name="baseSalary" value="${(userJob.baseSalary?c)!}"></td>
                    <td class="top-table-label">绩效工资：</td>
                    <td><input type="text" name="performanceSalary"
                               value="${(userJob.performanceSalary?c)!}">
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="修改" data-dismiss="modal" onclick="updateClick()"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</main>
<script src="js/jquery.js"></script>
<script src="js/bstable/js/bootstrap.min.js"></script>
<script src="js/bstable/js/bootstrap-table.js"></script>
<script src="js/bstable/js/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript">
    //一个部门一个数组，数组下是此部门的所有岗位
    jobs = {};
    <#list departmentJobList as departmentJob>
    jobs['${departmentJob.departmentId}'] = [
        <#list departmentJob.jobList as job>
        ['${job.jobName}', ${job.id}],
        </#list>
    ];
    </#list>

    function department_job(department) {
        let dv, jv;
        let i, ii;
        let job = document.getElementById('jobs');

        dv = department.value;

        job.options.length = 0;

        if (dv === '') return;
        if (typeof (jobs[dv]) == 'undefined') return;

        for (i = 0; i < jobs[dv].length; i++) {
            job.options.add(new Option(jobs[dv][i][0], jobs[dv][i][1]));
            ii = i + 1;
        }
    };

    function updateClick() {
        $.ajax({
            url: "/updateEmployeesJobWages.do",
            type: "POST",
            cache: false,
            data: $("#form_employees").serialize(),
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    location.reload();
                }
                alert(result.msg);
            },
            error: function () {
                alert("连接服务器异常，请刷新后重试")
            }
        });
    }
</script>
</body>
</html>