<%--
  Created by IntelliJ IDEA.
  User: wuhepeng
  Date: 2019/8/8
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="../js/vue.js"></script>
</head>
<body>
<div id="test">
    <%--        <p>{{introduce}}<span>{{less ? '...省略' ： '收起'}}</span></p>--%>
    <%--        <Spread :mes2="需要传递的文字数据"></Spread>--%>
    <span class="m-content overflow-line" id="J_description">{{introduce}}
        </span>
    <span style="font-family:PingFangSC-Regular;width:20px;font-size:12px;color:#2a5193;line-height:12px;text-align:left;"
          v-if="isShowMore" @click="showmoreDesc($event)">展开全部</span>
</div>
</body>

<script>
    new Vue({
        el: "#test",
        name: "Spread",
        data: {
            isShowMore: false,
            isDescStatus: true,
            introduce: ''
        },
        mounted() {
            this.introduce = '需要传递的文字数据需要传据需的文字数据需要需要传递的文字数据需要传据需的文字数据需要传据需的文字数据需要传据需的文字数据需要传据需要传递数据要传递的文字数据传据需的文字数据需要传据需的文字数据需要传据需要传递数据要传递的文字数据';
        },
        methods: {
            showmoreDesc(e) {
                let el = e.currentTarget;
                el.previousElementSibling.classList[!this.isDescStatus ? 'add' : 'remove']('overflow-line');
                el.classList[this.isDescStatus ? 'add' : 'remove']('more-collapse');
                el.innerHTML = !this.isDescStatus ? '展开全部' : '收起';
                this.isDescStatus = !this.isDescStatus;
                this.isShowMore = true;
            }
        },
        watch: {
            introduce() {
                if (this.introduce.length > 30) {
                    this.isShowMore = true;
                }
            }
        }
    })
</script>

<style>
    .m-content.overflow-line {
        width: 40px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
    }
</style>
</html>
