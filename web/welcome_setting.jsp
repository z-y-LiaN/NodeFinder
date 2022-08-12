<%--
  Created by IntelliJ IDEA.
  User: 周周周同学
  Date: 2022/6/18
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
        <title>todo:参数设置页面</title>
</head>
<link rel="stylesheet" href="css/add.css" />
<body>
<div id="main">
    <div class="main-content">

        <div class="first">
            <h2>参数配置</h2>
        </div>

        <div class="bigBox">
            <form action="NodeFinderController" method="post">
                <table>
                    <tr>
                        <td> 本次探测的数据库名：</td>
                        <td><input  type = "text" > </td>
                    </tr>
                    <tr>
                        <td> 本次探测的数据库账号：</td>
                        <td><input  type = "text" > </td>
                    </tr>
                    <tr>
                        <td> 本次探测的数据库密码：</td>
                        <td><input  type = "password" ></td>
                    </tr>
                    <tr>
                        <td> 本次探测名字：</td>
                        <td><input  type = "text" > </td>
                    </tr>
                    <tr>
                        <td> 探测开始时间：</td>
                        <td><input type="date" name="date"/></td>
                    </tr>
                    <tr>
                        <td> 快照周期（/分钟）：</td>
                        <td><input  type = "text" > </td>
                    </tr>
                    <tr>
                        <td> 模式：</td>
                        <td>  <select>
                            <option>1998</option>
                            <option>1999</option>
                            <option>2000</option>
                            <option>2001</option>
                        </select></td>
                    </tr>
                    <tr>
                        <td> 循环次数（/次）：</td>
                        <td><input  type = "text" > </td>
                    </tr>
                    <tr>
                        <td> 探测间隔（/分钟）：</td>
                        <td><input  type = "text" > </td>
                    </tr>


                </table>

                <div class="form-group">
                    <div class="bt">
                        <button type="submit" >提交</button>
                    </div>
                    <div class="bt">
                        <button type="button" >取消</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

