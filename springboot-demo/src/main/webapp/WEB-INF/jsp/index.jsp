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
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.bootcss.com/vue-resource/1.5.1/vue-resource.min.js"></script>

    <!-- 引入elementUI -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>
Welcome!!
<div id="test">
    {{status | openStatusFilter}}
    {{status == -1 ? '关闭' : status == 0 ? '开通' : '暂时开通'}}
    <input :data="keyword" placeholder="输入关键字查询"> <%-- :data = v-model 参数绑定--%>
    <input type="button" @click="getData()" value="调用接口"> <%-- @click = v-on:click 事件绑定--%>

    <%--饿了么UI 表格 --%>
    <el-table :data="testList" style="width: 100%">
        <el-table-column prop="id" label="序号" width="180"></el-table-column>
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
            }
        }
    })
</script>
</html>
