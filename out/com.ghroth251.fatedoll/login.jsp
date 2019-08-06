<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>骰子姬web页面！</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
    <meta name="keywords" content="骰子姬web页面！">
    <meta name="discription" content="骰子姬web页面！">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/system.css" />
    <script type="text/javascript">

    </script>
</head>
<body id="loginpage">
<div id="login" class="clearfix">
    <div class="main clearfix">
        <div class="logbox">
            <div class="header" style="background-color: #2468a9;">
                骰子姬web页面 <span style="color: red"></span>
            </div>
            <div class="web_login" id="web_login">
                <form id="loginform"  name="loginform" action="<%=request.getContextPath()%>/page/list" method="post">
                    <div class="inputOuter">
                        <input type="text" placeholder="请输入您的账户" class="inputstyle" id="u" name="uname" value="" tabindex="1">
                    </div>
                    <div class="inputOuter">
                        <input type="password" placeholder="请输入您的密码" class="inputstyle password" id="p" name="upass" value="" maxlength="16" tabindex="2">
                    </div>
                    <div class="submit">
                        <a class="login_button" href="index.html">
                            <input type="submit" tabindex="6" value="登 录" class="btn" id="login_button">
                            <input type="reset" tabindex="6" value="重 置" class="btn" id="login_button">
                        </a>
                    </div>
                </form>
            </div>
            <div class="footer">
                <a href="javascript:void(0);" target="_blank" >忘了密码？</a>
            </div>
        </div>

    </div>
</div>
</body>
</html>

