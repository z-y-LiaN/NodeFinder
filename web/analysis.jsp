<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: 周周周同学
  Date: 2022/6/18
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
    <script type="text/javascript" src="analysis_style/js/rem.js"></script>
    <style type="text/css">
        .layer04-panel {
            position: relative;
            float: left;
            height: 100%;
            width: 48%;
        }

        .layer04-panel-label {
            width: 100%;
            height: 15%;
            color: white;
            padding-top: 5px;
        }
    </style>
    <link rel="stylesheet" href="analysis_style/css/style.css">
    <title></title>
</head>

<body style="visibility: hidden;">
<%
    List<String>data_list=(List<String>) session.getAttribute("data_list");
    List<String>type_list=(List<String>) session.getAttribute("type_list");
%>
<div class="container-flex" tabindex="0" hidefocus="true">
    <div class="box-left">
        <div class="left-top">
            <div class="title-box" style="width: 3.5em;">
                <h6>网络探测数据</h6>
            </div>
            <div class="chart-box">
                <table class="table3">
                    <thead>
                    <tr>
                        <th>数据类型</th>
                        <th>数量（个）</th>
                    </tr>
                    </thead>
                    <tbody id="tList">
                    <%
                        for(int i=0;i<7;i++)
                        {
                    %>
                    <tr>
                        <td><%=type_list.get(i)%></td>
                        <td><%=data_list.get(i)%></td>
                    </tr>
                    <%}%>

                    </tbody>
                </table>

            </div>
        </div>
        <div class="left-bottom" class="select">
            <div class="title-box" style="width: 3.5em;">
                <h6>网络分析数据</h6>
            </div>
            <div class="chart-box">
                <table class="table3">
                    <thead>
                    <tr>
                        <th>数据类型</th>
                        <th>数值</th>
                    </tr>
                    </thead>
                    <tbody id="tList">
                    <%
                        for(int i=7;i<15;i++)
                        {
                    %>
                    <tr>
                        <td><%=type_list.get(i)%></td>
                        <td><%=data_list.get(i)%></td>
                    </tr>
                    <%}%>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    <div class="box-center">
        <div class="center-top">
            <div class="title-box" style="width: 3.5em;">
                <h6>不同算法效果对比折线图</h6>
            </div>
            <div class="chart-box">
                <div id="five-line" style="width: 100%; height: 100%;"></div>
            </div>
        </div>
        <div class="center-center">
            <div class="title-box" style="width: 3.5em;">
                <h6>不同算法效果对比数据表</h6>
            </div>
            <%
                HashMap<String,List<String>>consensus= (HashMap<String,List<String>>)session.getAttribute("consensus");
                if(consensus==null)
                    consensus=new HashMap<String,List<String>>();
            %>
            <div class="chart-box">
                <table class="table3">
                    <thead>
                    <tr>
                        <th> </th>
                        <th>1%</th>
                        <th>2%</th>
                        <th>3%</th>
                        <th>5%</th>
                    </tr>
                    </thead>
                    <tbody id="tList">
                    <tr>
                        <td>活跃度算法</td>
                        <td><%=consensus.get("MY1").get(0)%></td>
                        <td><%=consensus.get("MY1").get(1)%></td>
                        <td><%=consensus.get("MY1").get(2)%></td>
                        <td><%=consensus.get("MY1").get(3)%></td>
                    </tr>
                    <tr>
                        <td>度中心性算法</td>
                        <td><%=consensus.get("DEG").get(0)%></td>
                        <td><%=consensus.get("DEG").get(1)%></td>
                        <td><%=consensus.get("DEG").get(2)%></td>
                        <td><%=consensus.get("DEG").get(3)%></td>
                    </tr>
                    <tr>
                        <td>聚集系数算法</td>
                        <td><%=consensus.get("MY2").get(0)%></td>
                        <td><%=consensus.get("MY2").get(1)%></td>
                        <td><%=consensus.get("MY2").get(2)%></td>
                        <td><%=consensus.get("MY2").get(3)%></td>
                    </tr>
                    <tr>
                        <td>活跃度-聚集系数算法</td>
                        <td><%=consensus.get("MY3").get(0)%></td>
                        <td><%=consensus.get("MY3").get(1)%></td>
                        <td><%=consensus.get("MY3").get(2)%></td>
                        <td><%=consensus.get("MY3").get(3)%></td>
                    </tr>
                    <tr>
                        <td>随机算法</td>
                        <td><%=consensus.get("RAND").get(0)%></td>
                        <td><%=consensus.get("RAND").get(1)%></td>
                        <td><%=consensus.get("RAND").get(2)%></td>
                        <td><%=consensus.get("RAND").get(3)%></td>
                    </tr>
                    <tr>
                        <td>介数中心性算法</td>
                        <td><%=consensus.get("BET").get(0)%></td>
                        <td><%=consensus.get("BET").get(1)%></td>
                        <td><%=consensus.get("BET").get(2)%></td>
                        <td><%=consensus.get("BET").get(3)%></td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>

    </div>
    <%
        Map<String, Object> raw_data=(Map<String, Object>)session.getAttribute("raw_data");
        if(raw_data==null)
            raw_data=new HashMap<String,Object>();
    %>
    <div class="box-right">
        <div class="right-top">
            <div class="current-num">
                <div>未移除任何关键节点的平均共识达成时间</div>
                <p><%=raw_data.get("平均共识达成时间")%> ms</p>
            </div>
            <div class="current-num">
                <div>未移除任何关键节点的平均块传播时间</div>
                <p><%=raw_data.get("平均块传播时间")%> s</p>
            </div>
            <div class="current-num">
                <div>未移除任何关键节点的出块时间</div>
                <p><%=raw_data.get("平均出块时间")%> ms</p>
            </div>

        </div>
        <div class="right-bottom">
            <div class="title-box">
                <h6>拓扑中节点的度分布</h6>
            </div>
            <div class="chart-box pie-chart">
                <div id="dufenbu_data" style="width: 100%; height: 100%;" data=></div>

            </div>
        </div>
    </div>
</div>

</body>
<%
    List<String>degree_list=(List<String>)session.getAttribute("degree_list");
    List<String>bin_list=(List<String>)session.getAttribute("bin_list");
    if (degree_list==null)
        degree_list=new ArrayList<String>();
    if (bin_list==null)
        bin_list=new ArrayList<String>();
%>
<script type="text/javascript">
    var degree=[];
    <%
    for(String degree:degree_list)
        {%>
    degree.push(<%=degree%>);
    <%
        }
    %>
    var bin=[];
    <%
    for(String bin:bin_list)
        {%>
    bin.push(<%=bin%>);
    <%
        }
    %>
    var MY1=[];
    var MY2=[];
    var MY3=[];
    var DEG=[];
    var BET=[];
    var RAND=[];
    <%
   for(String value:consensus.get("MY1"))
       {%>
    MY1.push(<%=value%>);
    <%
        }
    %>
    <%
   for(String value:consensus.get("MY2"))
       {%>
    MY2.push(<%=value%>);
    <%
        }
    %>
    <%
   for(String value:consensus.get("MY3"))
       {%>
    MY3.push(<%=value%>);
    <%
        }
    %>
    <%
   for(String value:consensus.get("DEG"))
       {%>
    DEG.push(<%=value%>);
    <%
        }
    %>
    <%
       for(String value:consensus.get("BET"))
           {%>
    BET.push(<%=value%>);
    <%
        }
    %>
    <%
   for(String value:consensus.get("RAND"))
       {%>
    RAND.push(<%=value%>);
    <%
        }
    %>
</script>
<script type="text/javascript" src="analysis_style/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="analysis_style/js/layer/layer.min.js"></script>
<script type="text/javascript" src="analysis_style/js/layer/laydate/laydate.js"></script>
<script type="text/javascript" src="analysis_style/js/echarts.min.js"></script>
<script type="text/javascript" src="analysis_style/js/infographic.js"></script>
<script type="text/javascript" src="analysis_style/js/macarons.js"></script>
<script type="text/javascript" src="analysis_style/js/shine.js"></script>
<script type="text/javascript" src="analysis_style/js/base.js"></script>
<script type="text/javascript">
    $('document').ready(function () {
        $("body").css('visibility', 'visible');
        var localData = [$('#teacher').val(), $('#start').val() + '/' + $('#end').val(), $('#leader').val()]
        localStorage.setItem("data", localData);

    })
</script>

</html>