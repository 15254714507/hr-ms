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
    <div class="find-top1">
        <form action="" id="form_new_employees">
            <input id="id" name="id" value="${(registerNewEmployee.id)!}" style="display: none"/>
            <table class="top-table">
                <tr>
                    <td class="top-table-label">职员编号：</td>
                    <td><input type="text" value="${(registerNewEmployee.getUsername())!}" name="username"
                               onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/></td>
                    <td class="top-table-label">职员姓名：</td>
                    <td><input type="text" name="name" value="${(registerNewEmployee.getName())!}"
                               onkeyup="this.value=this.value.replace(/^ +| +$/g,'')"/></td>
                    <td class="top-table-label">出生年月：</td>
                    <td><input type="date" name="dateOfBirth" value="${(registerNewEmployee.getDateOfBirth())!}"/></td>
                </tr>
                <tr>
                    <td class="top-table-label">联系电话：</td>
                    <td><input type="text" name="phone" value="${(registerNewEmployee.getPhone())!}"></td>
                    <td class="top-table-label">联系邮箱：</td>
                    <td><input type="text" name="email" value="${(registerNewEmployee.getEmail())!}"></td>
                    <td class="top-table-label">常住地址：</td>
                    <td><input type="text" name="address" value="${(registerNewEmployee.getAddress())!}"></td>
                </tr>
                <tr>
                    <td class="top-table-label">证件号码：</td>
                    <td><input type="text" name="identityCard" value="${(registerNewEmployee.getIdentityCard())!}"></td>
                    <td class="top-table-label">证件类型：</td>
                    <td><select name="identityType">
                            <#if registerNewEmployee.getIdentityType()=="第二代身份证">
                                <option>第二代身份证</option>
                                <option>护照</option>
                            <#else>
                                <option>护照</option>
                                <option>第二代身份证</option>
                            </#if>
                        </select></td>
                    <td class="top-table-label">性别：</td>
                    <td>
                        <#if registerNewEmployee.getGender()==0>
                            <input type="radio" checked="checked" name="gender" value="0"><span>男</span>
                            <input type="radio" name="gender" value="1"><span>女</span>
                        <#else>
                            <input type="radio" name="gender" value="0"><span>男</span>
                            <input type="radio" checked="checked" name="gender" value="1"><span>女</span>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td class="top-table-label">学历：</td>
                    <td><select name="degree">
                            <#if registerNewEmployee.getDegree()=="本科">
                                <option>本科</option>
                                <option>研究生</option>
                                <option>博士</option>
                            <#elseif registerNewEmployee.getDegree()=="研究生">
                                <option>研究生</option>
                                <option>本科</option>
                                <option>博士</option>
                            <#else >
                                <option>博士</option>
                                <option>本科</option>
                                <option>研究生</option>
                            </#if>

                        </select></td>
                    <td class="top-table-label">专业：</td>
                    <td><input type="text" name="professional" value="${(registerNewEmployee.professional)!}"></td>
                    <td class="top-table-label">毕业院校：</td>
                    <td><input type="text" name="university" value="${(registerNewEmployee.university)!}"></td>
                </tr>
                <tr>
                    <td class="top-table-label">毕业时间：</td>
                    <td><input type="date" name="graduationDate" value="${(registerNewEmployee.graduationDate)!}"></td>
                    <td class="top-table-label">工作时间：</td>
                    <td><input type="date" name="firstWorkDate" value="${(registerNewEmployee.firstWorkDate)!}">
                    </td>
                    <td class="top-table-label">工作年限：</td>
                    <td><input type="text" name="workYears" value="${(registerNewEmployee.workYears)!}"></td>
                </tr>
                <tr>
                    <td class="top-table-label">国籍：</td>
                    <td><select name="nationality">
                            <#if registerNewEmployee.nationality=="中国">
                                <option>中国</option>
                                <option>外国</option>
                            <#else >
                                <option>外国</option>
                                <option>中国</option>
                            </#if>

                        </select></td>
                    <td class="top-table-label">民族：</td>
                    <td><select name="national">
                            <#if registerNewEmployee.national=="汉族">
                                <option value="汉族">汉族</option>
                                <option value="少数民族">少数民族</option>
                            <#else>
                                <option value="少数民族">少数民族</option>
                                <option value="汉族">汉族</option>
                            </#if>
                        </select></td>
                    <td class="top-table-label">籍贯：</td>
                    <td><input type="text" name="nativePlace" value="${(registerNewEmployee.nativePlace)!}"></td>
                </tr>
                <tr>
                    <td class="top-table-label">户籍地址：</td>
                    <td><input type="text" name="censusRegister" value="${(registerNewEmployee.censusRegister)!}"></td>
                    <td class="top-table-label">入职部门：</td>
                    <td><select name="departmentId" onChange="department_job(this)">
                            <#list departmentJobList as departmentJob>
                                <#if registerNewEmployee.departmentId==departmentJob.departmentId>
                                    <option value="${departmentJob.departmentId}"
                                            selected>${departmentJob.departmentName}</option>
                                <#else >
                                    <option value="${departmentJob.departmentId}">${departmentJob.departmentName}</option>
                                </#if>
                            </#list>
                        </select></td>
                    <td class="top-table-label">工作岗位：</td>
                    <td><select name="jobName" id="jobs">
                            <#list departmentJobList as departmentJob>
                                <#if registerNewEmployee.departmentId==departmentJob.departmentId>
                                    <#list departmentJob.jobList as job>
                                        <#if job.getId()==registerNewEmployee.getJobId()>
                                            <option selected>${job.getJobName()}</option>
                                        <#else >
                                            <option>${job.getJobName()}</option>
                                        </#if>
                                    </#list>
                                </#if>
                            </#list>
                        </select></td>
                </tr>
                <tr>
                    <td class="top-table-label">入职时间：</td>
                    <td><input type="date" name="employmentDate" value="${(registerNewEmployee.employmentDate)!}"></td>
                    <td class="top-table-label">实习时间：</td>
                    <td><input type="date" name="internshipDate" value="${(registerNewEmployee.internshipDate)!}"></td>
                    <td class="top-table-label">员工类型：</td>
                    <td><select name="typesOfEmployees">
                            <#if registerNewEmployee.typesOfEmployees=="全职">
                                <option>全职</option>
                                <option>实习</option>
                            <#else>
                                <option>实习</option>
                                <option>全职</option>
                            </#if>
                        </select></td>
                </tr>
                <tr>
                    <td class="top-table-label">基本工资：</td>
                    <td><input type="text" name="baseSalary" value="${(registerNewEmployee.baseSalary?c)!}"></td>
                    <td class="top-table-label">绩效工资：</td>
                    <td><input type="text" name="performanceSalary"
                               value="${(registerNewEmployee.performanceSalary?c)!}">
                    </td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <input type="button" value="修改" onclick="updateClick()"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="table-con">
        <table id="table1" class="table-style"></table>
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

        let selectJob = '${(registerNewEmployee.jobName)}';
        for (i = 0; i < jobs[dv].length; i++) {
            job.options.add(new Option(jobs[dv][i], jobs[dv][i]));
            ii = i + 1;
        }
    };

    function updateClick() {
        $.ajax({
            url: "/updateNewEmployees.do",
            type: "POST",
            cache: false,
            data: $("#form_new_employees").serialize(),
            dataType: "json",
            success: function (result) {
                if (result.code === 1) {
                    alert(result.msg);
                    $("#modal").modal({
                        dismiss:"modal"
                    });
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