<%@ page pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<html>

<head>
  <title>系统主页</title>
  <link rel="stylesheet" href="./css/pintuer.css">
  <link rel="stylesheet" href="./css/admin.css">
  <script src="./js/jquery.js"></script>
  <script src="./js/pintuer.js"></script>
</head>

<body style="background-color:#99CCCC;">
<div class="header bg-main">
  <div class="logo margin-small-center fadein-top">
    <h1>
      面向 Ethereum 网络的区块链 P2P 探测与节点挖掘系统
    </h1>
  </div>
</div>
<div class="leftnav">
  <div class="leftnav-title">
    <strong><span class="icon-list"></span>功能列表</strong>
  </div>
  <ul style="display:block">
    <h2>
      <a href="networkfind.jsp" target="right"><span class="icon-caret-right"></span>
        <font color="white">网络探测</font>
      </a>
    </h2>
  </ul>
  <ul style="display:block">
    <h2>
      <a href="analysis.jsp" target="right"><span class="icon-caret-right"></span>
        <font color="white">数据分析</font>
      </a>
    </h2>
  </ul>
  <ul style="display:block">
    <h2>
      <a href="https://dsg-titech.github.io/simblock-visualizer/" target="right"><span class="icon-caret-right"></span>
        <font color="white">仿真预测</font>
      </a>
    </h2>
  </ul>

</div>
<script type="text/javascript">
  $(function () {
    $(".leftnav h2").click(function () {
      $(this).next().slideToggle(200);
      $(this).toggleClass("on");
    })
    $(".leftnav ul li a").click(function () {
      $("#a_leader_txt").text($(this).text());
      $(".leftnav ul li a").removeClass("on");
      $(this).addClass("on");
    })
  });
</script>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="welcome_setting.jsp" name="right" width="100%" height="100%"></iframe>
</div>
</body>

</html>