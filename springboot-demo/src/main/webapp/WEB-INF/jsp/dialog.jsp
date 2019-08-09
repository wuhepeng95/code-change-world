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
    <%--    <script src="https://code.jquery.com/jquery-2.2.4.min.js"--%>
    <%--            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="--%>
    <%--            crossorigin="anonymous"></script>--%>
    <!-- 引入elementUI -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>

<div id="root">
    <template>
        <el-button :plain="true" @click="open">打开消息提示</el-button>
        <el-button :plain="true" @click="openVn">VNode</el-button>
    </template>
    <el-button :plain="true" @click="conform">带有超链接的dialog</el-button>

    <el-input
            v-model="text"
            type="text"
            placeholder="选填，2可填写您对老师备课内容的评价补充"
            maxlength="500"
            show-word-limit
    ></el-input>
    <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>

    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
        <el-button :plain="true" @click="dialogFormVisible = false">此次不在提醒</el-button>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
    </el-dialog>


</div>
</body>
<script>
    var test = new Vue({
        el: "#root",
        data: {
            dialogFormVisible: false,
            text: ''
        },
        methods: {
            open() {
                this.$message('这是一条消息提示');
            },
            openVn() {
                const h = this.$createElement;
                this.$message({
                    message: h('p', null, [
                        h('span', null, '内容可以是 '),
                        h('i', {style: 'color: teal'}, 'VNode')
                    ])
                });
            },
            conform() {
                const h = this.$createElement;
                this.$confirm(h('p', null, [
                    h('span', null, '为家长绑定的手机号备注未含有群名称，是否继续设置？'),
                    h('a', {
                        style: {
                            color: 'red'
                        },
                        on: {
                            'click'() {
                                alert('点击事件');
                            }
                        }
                    }, "本次不再提醒")
                ]), {
                    confirmButtonText: '是',
                    cancelButtonText: '否',
                    type: 'warning'
                }).then(() = > {
                    alert("是"
            )
                ;
            }).
                catch(() = > {
                    alert("否"
            )
                ;
            })
                ;
            }
        }
    })
</script>
</html>
