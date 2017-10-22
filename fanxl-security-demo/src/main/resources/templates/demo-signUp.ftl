<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8" content="text/html"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>系统注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<body>

<form action="user/regist" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit" name="type" value="regist">注册</button>
                <button type="submit" name="type" value="binding">绑定</button>
            </td>
        </tr>
    </table>
</form>

</body>
</html>