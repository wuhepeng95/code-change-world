<%--
  Created by IntelliJ IDEA.
  User: wuhepeng
  Date: 2019/7/4
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
</head>
<body>
Welcome!!
<div id="test">
    {{status | openStatusFilter}}
</div>
</body>
<script>
    var test = new Vue({
        el: "#test",
        data: {
            status: 1
        },
        filters:{
            openStatusFilter: function (value) {
                if (value === -1) return '关闭';
                if (value === 0) return '开通';
                if (value === 1) return '暂时开通';
            }
        }
    })
</script>
</html>
