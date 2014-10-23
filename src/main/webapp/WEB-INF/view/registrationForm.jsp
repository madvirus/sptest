<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>회원 가입</title>
</head>
<body>

<form:form commandName="member">
<p>
	<label for="memberId">ID</label>:
	<form:input path="id" id="memberId"/>
	<form:errors path="id" />
</p>
<p>
	<label for="password">암호</label>: 
	<form:password path="password" />
	<form:errors path="password" /> <br/>
</p>
<p>
	<label for="confirmPassword">암호확인</label>:
	<form:password path="confirmPassword" />
	<form:errors path="confirmPassword" /> <br/>
</p>
<p>
    <label for="name">이름</label>:
    <form:input path="name" />
    <form:errors path="name" />
</p>

<input type="submit" value="가입" />

</form:form>

</body>
</html>