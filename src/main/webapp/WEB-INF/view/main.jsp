<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>메인</title>
</head>
<body>

<c:if test="${! empty auth}">${auth.name} 로그인 함</c:if>
<c:if test="${empty auth}">
<ul>
    <li><a href="./login">로그인</a></li>
    <li><a href="./regist">가입</a></li>
</ul>
</c:if>

</body>
</html>