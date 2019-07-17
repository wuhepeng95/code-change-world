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
    <%-- 引入vue --%>
    <script type="text/javascript" src="../js/vue.js"></script>
    <script src="https://cdn.bootcss.com/vue-resource/1.5.1/vue-resource.js"></script>
    <%--    <script src="https://cdn.jsdelivr.net/npm/vue-router@3.0.2/dist/vue-router.js"></script>--%>

    <!-- 引入elementUI -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>
Welcome!!
<div id="test">
    {{status | openStatusFilter}}
    {{status == -1 ? '关闭' : status == 0 ? '开通' : '暂时开通'}}
    <el-input :data="keyword" placeholder="输入关键字查询" style="width: 300px"></el-input>
    <%-- :data = v-model 参数绑定--%>
    <el-button type="primary" @click="getData()">调用接口</el-button>
    <%-- @click = v-on:click 事件绑定--%>
    <el-button type="primary" @click="delayTest()">延时弹窗</el-button>

    <br/>
    <a href="/dialog">弹窗测试</a>
    <%--饿了么UI 表格 --%>
    <el-table :data="testList" style="width: 100%">
        <el-table-column prop="id" label="序号" width="180" ></el-table-column>
        <el-table-column prop="name" label="姓名" width="180"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
        <el-table-column prop="createTime" label="创建日期"></el-table-column>
    </el-table>
</div>
</body>
<script>
    var test = new Vue({
        el: "#test",
        data: {
            status: 1,
            keyword: '',
            testList: []
        },
        filters: {
            openStatusFilter: function (value) {
                if (value === -1) return '关闭';
                if (value === 0) return '开通';
                if (value === 1) return '暂时开通';
            }
        },
        methods: {
            getData: function () {
                this.$http({
                    method: 'GET',
                    url: 'http://localhost:18080/getData?keyword=' + this.keyword
                }).then(function (response) {
                    this.testList = response.data;//response.data 固定写法
                }, function (error) {
                    alert("服务器异常\n" + error.data);
                })
            },
            delayTest: function () {
                //debugger
                setTimeout("alert('延迟了1秒钟')", 1000);
            }
        }
    })
</script>
</html>
