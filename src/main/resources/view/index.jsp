<%--
  Created by IntelliJ IDEA.
  User: zdr
  Date: 2017/7/25
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="vue.js"></script>
<script  src="jquery.min.js"></script>
<script  src="index.js"></script>
<html>
  <head>
    <title>各种传参方式</title>
  </head>
  <body>
    <div>
      <button id = "RequestParam_get" onclick="requestParamGet()">RequestParam get方式传参</button>
    </div>
    <%--<div>--%>
      <%--<button id = "RequestParam_get1" onclick="requestParamGet1()">RequestParam get方式传参转义</button>--%>
    <%--</div>--%>
    <div>
      <button id = "RequestParam_post" onclick="requestParamPost()">RequestParam post方式传参</button>
    </div>

    <div>
      <button id = "User_post" onclick="User_post()">post方式传参_JSON格式</button>
    </div>
    <div>
      <button id = "User_post2" onclick="post2Click()">post方式传参_x-www-form-urlencoded格式</button>
    </div>
    <div>
      <button id = "User_post3" onclick="postList()">post方式传List</button>
    </div>
    <div>
      <button id = "User_post4" onclick="postMap()">post方式传Map</button>
    </div>
    <div>
      <button id = "User_post5" onclick="postJsonObj()">post方式传Json</button>
    </div>
    <div>
      <input type="file" id = "file_post">
      <button id = "fileClick">post方式传文件</button>
    </div>
    <%--<form name="serForm" action="/demo/postFile" method="post"  enctype="multipart/form-data">--%>
      <%--<h1>采用流的方式上传文件</h1>--%>
      <%--<input type="file" name="file">--%>
      <%--<input type="submit" value="upload"/>--%>
    <%--</form>--%>

    <div>
      <button id = "postParam" onclick="postParam()">post方式，url带参数</button>
    </div>

    <%--<div id="app">--%>
      <%--{{ message }}--%>
    <%--</div>--%>

    <%--<div id="app-5">--%>
      <%--<p>{{ message }}</p>--%>
      <%--<button v-on:click="reverseMessage">逆转消息</button>--%>
    <%--</div>--%>


  </body>
</html>
