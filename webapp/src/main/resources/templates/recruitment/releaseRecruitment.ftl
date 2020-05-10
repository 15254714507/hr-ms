<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>申请新的招聘信息</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>申请新的招聘信息</span></div>
    <div class="find-top1">
        <form action="" id="form_Recruitment">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">部门：</td>
                    <td><select name="departmentName" onChange="department_job(this)">
                            <#list departmentJobList as departmentJob>
                                <option value="${departmentJob.departmentName}">${departmentJob.departmentName}</option>
                            </#list>
                        </select></td>
                    <td class="top-table-label">岗位：</td>
                    <td><select name="jobName" id="jobs">
                            <#list departmentJobList as departmentJob>
                                <#list departmentJob.jobList as job>
                                    <option value="${job.jobName}">${job.getJobName()}</option>
                                </#list>
                            </#list>
                        </select></td>
                    <td class="top-table-label">招聘员工类型：</td>
                    <td><select name="typeOfEmployees" id="jobs">
                            <option>全职</option>
                            <option>实习</option>
                        </select></td>
                </tr>
                <tr>
                    <td class="top-table-label">招聘数量：</td>
                    <td><input name="num" value="0"></td>
                    <td class="top-table-label">薪资范围：</td>
                    <td><input name="wages" placeholder="最低薪~最高薪"></td>
                </tr>
                <tr>
                    <td class="top-table-label">岗位职责</td>
                    <td colspan="6"><textarea id="jobResponsibilities" name="jobResponsibilities"></textarea></td>
                </tr>
                <tr>
                    <td class="top-table-label">岗位要求</td>
                    <td colspan="6"><textarea id="jobRequirements" name="jobRequirements"></textarea></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="申请" data-dismiss="modal" onclick="saveClick()"/>
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
    jobs['${departmentJob.departmentName}'] = [
        <#list departmentJob.jobList as job>
        '${job.jobName}',
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
            job.options.add(new Option(jobs[dv][i], jobs[dv][i]));
            ii = i + 1;
        }
    };

    function saveClick() {
        $.ajax({
            url: "/saveRecruitmentNeeds.do",
            type: "POST",
            cache: false,
            data: $("#form_Recruitment").serialize(),
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