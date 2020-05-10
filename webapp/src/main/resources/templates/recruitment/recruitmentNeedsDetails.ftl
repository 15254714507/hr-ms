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
    <div class="home-tab"><i class="tab-i"></i> 所在位置：<span>招聘信息的岗位职责和要求</span></div>
    <div class="find-top1">
        <form action="" id="form_Recruitment">
            <table class="top-table">
                <tr>
                    <td class="top-table-label">岗位职责</td>
                    <td colspan="5"><textarea id="jobResponsibilities"
                                              name="jobResponsibilities">${(recruitmentNeeds.jobResponsibilities)}</textarea>
                    </td>
                </tr>
                <tr>
                    <td class="top-table-label">岗位要求</td>
                    <td colspan="5"><textarea id="jobRequirements"
                                              name="jobRequirements">${(recruitmentNeeds.jobRequirements)!}</textarea>
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
</body>
</html>