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
    <script type="text/javascript" src="../js/vue2.6.10.js"></script>

    <!-- 引入elementUI -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>

    <%-- 引入jquery --%>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"></script>
</head>
<body>
Welcome!!
<a href="../../static/vuedemo.html">vueDemo跑马灯</a>
<a href="/dialog">弹窗测试</a>
<div id="test">
    <el-button type="primary" @click="delayTest()">延时弹窗</el-button>
    <br/>
    {{status == -1 ? '关闭' : status == 0 ? '开通' : '暂时开通'}}
    <br/>
    <el-input
            v-model="keyword"
            placeholder="输入关键字查询"
            style="width: 300px"
            clearable
            >
    </el-input>
    <%-- :data = v-model 参数绑定--%>
    <el-button type="primary" @click="getData()">调用接口</el-button>
    <%-- @click = v-on:click 事件绑定--%>
    <br/>
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
    var vue = new Vue({
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
                // this.$http({
                //     method: 'GET',
                //     url: 'http://localhost:18080/getData?keyword=' + this.keyword
                // }).then(function (response) {
                //     this.testList = response;
                // }, function (error) {
                //     alert("服务器异常\n" + error.data);
                // })
                $.ajax({
                    url: 'http://localhost:18080/getData?keyword=' + this.keyword,
                    type: 'get',
                    // dataType: 'jsonp',
                    // data: {
                    //     sign: md5(this.appKey + text + this.salt + this.key)
                    // },
                    success: function (response) {
                        if (response.result) {
                            vue.$message({
                                message: '调用成功',
                                type: 'success'
                            });
                            vue.testList = response.data;//response.data 固定写法
                        } else {
                            vue.$message({
                                message: '调用失败' + response.msg,
                                type: 'error'
                            });
                        }
                    }
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
