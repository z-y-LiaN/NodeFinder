<%--
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
                    <tr>
                        <td>探测到的所有节点</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>探测到的IP地址</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>IP地址变化的节点</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>运行多个节点的IP地址</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>有路由表的节点</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>活跃节点总数</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>可路由节点</td>
                        <td>xxx</td>
                    </tr>

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
                    <tr>
                        <td>节点总数</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>去除孤点后的节点总数</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>平均度</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>平均最短路径长度</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>平均聚集系数</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>网络直径</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>度数大于4的节点数量</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>平均路由表大小</td>
                        <td>xxx</td>
                    </tr>
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
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>度中心性算法</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>聚集系数算法</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>活跃度-聚集系数算法</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>随机算法</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    <tr>
                        <td>介数中心性算法</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                        <td>xxx</td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>

    </div>
    <div class="box-right">
        <div class="right-top">
            <div class="current-num">
                <div>未移除任何关键节点的平均共识达成时间</div>
                <p>4186.425742574257 ms</p>
            </div>
            <div class="current-num">
                <div>未移除任何关键节点的平均块传播时间</div>
                <p>4.367421017027051 s</p>
            </div>
            <div class="current-num">
                <div>未移除任何关键节点的出块时间</div>
                <p>573574.5445544554 ms</p>
            </div>

        </div>
        <div class="right-bottom">
            <div class="title-box">
                <h6>拓扑中节点的度分布</h6>
            </div>
            <div class="chart-box pie-chart">
                <div id="dufenbu_data" style="width: 100%; height: 100%;"></div>

            </div>
        </div>
    </div>
</div>

</body>
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