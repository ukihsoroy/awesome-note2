<%--
  Created by IntelliJ IDEA.
  User: xiaofei.wxf
  Date: 2015/5/9
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edas Demo</title>
</head>
<body>
<div>
    <form method="get" target="_blank" action="echo.htm">
        <label>Echo</label>
        <label>Echo内容：</label>
        <input type="text" name="echo"/>
        <label>调用次数：</label>
        <input type="text" name="round"/>
        <input type="submit"/>
    </form>
</div>

<div>
    <form method="get" target="_blank" action="now.htm">
        <label>获取时间</label>
        <label>调用次数：</label>
        <input type="text" name="round"/>
        <input type="submit"/>
    </form>
</div>

<div>
    <form method="get" target="_blank" action="send.htm">
        <label>发送ONS消息</label>
        <label>消息内容：</label>
        <input type="text" name="msg"/>
        <label>调用次数：</label>
        <input type="text" name="round"/>
        <input type="submit"/>
    </form>
</div>

<div>
    <form method="get" target="_blank" action="recv.htm">
        <label>接受ONS消息</label>
        <label>调用次数：</label>
        <input type="text" name="round"/>
        <input type="submit"/>
    </form>
</div>

<div>
    <form method="get" target="_blank" action="insert.htm">
        <label>数据Insert</label>
        <label>字段值：</label>
        <input type="text" name="name"/>
        <input type="submit"/>
    </form>
</div>

<div>
    <form method="get" target="_blank" action="query.htm">
        <label>数据Query</label>
        <input type="submit"/>
    </form>
</div>

<div>
    <form method="get" target="_blank" action="update.htm">
        <label>数据Update</label>
        <input type="submit"/>
    </form>
</div>

<div>
    <form method="get" target="_blank" action="delete.htm">
        <label>数据Delete</label>
        <input type="submit"/>
    </form>
</div>

</body>
</html>
