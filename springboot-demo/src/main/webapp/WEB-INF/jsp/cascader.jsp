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
    <script src="https://cdn.bootcss.com/vue-resource/1.5.1/vue-resource.js"></script>
    <%--    <script src="https://code.jquery.com/jquery-2.2.4.min.js"--%>
    <%--            integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="--%>
    <%--            crossorigin="anonymous"></script>--%>
    <!-- 引入elementUI -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
</head>
<body>
    <div id="root">
        <div class="block">
            <span class="demonstration">折叠展示Tag</span>
            <el-cascader
                    :options="options2"
                    :props="props"
                    collapse-tags
                    clearable>
            </el-cascader>

            <el-select v-model="value" placeholder="请选择">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
            </el-select>

        </div>
    </div>
</body>
<script>
    let newVar = {
        data() {
            return {
                props: {multiple: true},
                options: [{
                    value: '选项1',
                    label: '黄金糕'
                }, {
                    value: '选项2',
                    label: '双皮奶'
                }, {
                    value: '选项3',
                    label: '蚵仔煎'
                }, {
                    value: '选项4',
                    label: '龙须面'
                }, {
                    value: '选项5',
                    label: '北京烤鸭'
                }],
                value: '选项4',// 必须为字符串类型.....

                options2: [{
                    value: 1,
                    label: '东南',
                    children: [{
                        value: 2,
                        label: '上海',
                        children: [
                            {value: 3, label: '普陀'},
                            {value: 4, label: '黄埔'},
                            {value: 5, label: '徐汇'}
                        ]
                    }, {
                        value: 7,
                        label: '江苏',
                        children: [
                            {value: 8, label: '南京'},
                            {value: 9, label: '苏州'},
                            {value: 10, label: '无锡'}
                        ]
                    }, {
                        value: 12,
                        label: '浙江',
                        children: [
                            {value: 13, label: '杭州'},
                            {value: 14, label: '宁波'},
                            {value: 15, label: '嘉兴'}
                        ]
                    }]
                }, {
                    value: 17,
                    label: '西北',
                    children: [{
                        value: 18,
                        label: '陕西',
                        children: [
                            {value: 19, label: '西安'},
                            {value: 20, label: '延安'}
                        ]
                    }, {
                        value: 21,
                        label: '新疆维吾尔族自治区',
                        children: [
                            {value: 22, label: '乌鲁木齐'},
                            {value: 23, label: '克拉玛依'}
                        ]
                    }]
                }]
            };
        }
    };
    const firstCourseQualityControlMainCtor = Vue.extend(newVar);
    var $firstCourseQualityControlVue = new firstCourseQualityControlMainCtor().$mount('#root');
</script>
</html>
