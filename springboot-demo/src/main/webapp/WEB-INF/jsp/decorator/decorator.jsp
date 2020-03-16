<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>site mesh公用title</title>
    <%-- 引入vue.js --%>
    <script type="text/javascript" src="../../js/vue2.6.10.js"></script>
</head>
<body>
    <div id="header">
        <h3>hard study</h3>
    </div>
    <div id="content">
        <sitemesh:write property='body'/>
    </div>
    <div id="foot">
        <h3>day day on</h3>
    </div>
</body>
</html>
