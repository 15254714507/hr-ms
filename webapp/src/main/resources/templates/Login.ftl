<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>登录页</title>

    <link rel="stylesheet" href="css/style.css" />

<body>

<div class="login-container">
    <h1>单点登录页面</h1>

    <div class="connect">
        <p>版权归孔超所有</p>
    </div>

    <form action="/login.do" method="post" id="loginForm">
        <div>
            <input type="text" name="username" class="username" placeholder="用户名" autocomplete="off"/>
        </div>
        <div>
            <input type="password" name="password" class="password" placeholder="密码" oncontextmenu="return false" onpaste="return false" />
        </div>
        <button id="submit" type="submit">登 陆</button>
    </form>

</div>

<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/common.js" type="text/javascript"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js" type="text/javascript"></script>
<script src="js/supersized-init.js" type="text/javascript"></script>
<!--表单验证-->
<script src="js/jquery.validate.min.js?var1.14.0" type="text/javascript"></script>

</body>
</html>