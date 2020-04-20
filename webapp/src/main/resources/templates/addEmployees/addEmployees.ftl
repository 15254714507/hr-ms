<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>入职管理</title>
    <link rel="stylesheet" href="js/bstable/css/bootstrap.min.css">
    <link rel="stylesheet" href="js/bstable/css/bootstrap-table.css">
    <link rel="stylesheet" href="css/all.css">
</head>
<body>
<main>
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>入职管理</span></div>
    <div class="find-top1">
        <form action="">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">职员编号：</td>
                    <td><input type="text"></td>
                    <td class="top-table-label">职员姓名：</td>
                    <td><input type="text"></td>
                    <td class="top-table-label">出生年月：</td>
                    <td><input type="date"></td>
                </tr>
                <tr>
                    <td class="top-table-label">联系电话：</td>
                    <td><input type="text"></td>
                    <td class="top-table-label">联系邮箱：</td>
                    <td><input type="text"></td>
                    <td class="top-table-label">常住地址：</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td class="top-table-label">证件号码：</td>
                    <td><input type="text"></td>
                    <td class="top-table-label">证件类型：</td>
                    <td><select>
                            <option>第二代身份证</option>
                            <option>护照</option>
                        </select></td>
                    <td class="top-table-label">性别：</td>
                    <td><input type="radio" checked="checked" name="sex"><span>男</span>
                        <input type="radio" name="sex"><span>女</span>
                    </td>
                </tr>
                <tr>
                    <td class="top-table-label">学历：</td>
                    <td><select>
                            <option>本科</option>
                            <option>研究生</option>
                            <option>博士</option>
                        </select></td>
                    <td class="top-table-label">专业：</td>
                    <td><input type="text"></td>
                    <td class="top-table-label">毕业院校：</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td class="top-table-label">毕业时间：</td>
                    <td><input type="date"></td>
                    <td class="top-table-label">工作时间：</td>
                    <td><input type="date"></td>
                    <td class="top-table-label">工作年限：</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td class="top-table-label">国籍：</td>
                    <td><select>
                            <option>中国</option>
                            <option>外国</option>
                        </select></td>
                    <td class="top-table-label">民族：</td>
                    <td><select>
                            <option value="">请选择</option>
                            <option value="汉族">汉族</option>
                            <option value="少数民族">少数民族</option>

                        </select></td>
                    <td class="top-table-label">户籍地址：</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td class="top-table-label">入职部门：</td>
                    <td><select name="department" onChange="department_job(this)">
                            <option value="">选择部门</option>
                            <#list departmentJobList as departmentJob>
                                <option value="${departmentJob.departmentId}">${departmentJob.departmentName}</option>
                            </#list>
                        </select></td>
                    <td class="top-table-label">工作岗位：</td>
                    <td><select name="job" id="jobs">
                            <option value="">选择岗位</option>
                        </select></td>
                    <td class="top-table-label">入职时间：</td>
                    <td><input type="date"></td>
                </tr>
                <tr>
                    <td class="top-table-label">实习时间：</td>
                    <td><input type="date"></td>
                    <td class="top-table-label">员工类型：</td>
                    <td><select>
                            <option>全职</option>
                            <option>实习</option>
                        </select></td>
                    <td class="top-table-label">基本工资：</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td class="top-table-label">绩效工资：</td>
                    <td><input type="text"></td>
                </tr>
                <tr>
                    <td colspan="6" style="text-align: center">
                        <button class="add-but"><i class="glyphicon glyphicon-edit"></i> 提交</button>
                        <button class="add-del"><i class="glyphicon glyphicon-remove"></i> 取消</button>
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
    $(function () {


    })
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

        job.options.length = 1;

        if (dv === '') return;
        if (typeof (jobs[dv]) == 'undefined') return;

        for (i = 0; i < jobs[dv].length; i++) {
            job.options.add(new Option(jobs[dv][i], jobs[dv][i]))
            ii = i + 1;
        }
    };
</script>
</body>
</html>