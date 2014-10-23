<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head><title>로그인 폼</title></head>
<body>

<form:form commandName="login">
<form:errors element="div" />
<p>
	<label for="id">ID</label>:
	<form:input path="id" />
	<form:errors path="id"/>
</p>
<p>
	<label for="password">암호</label>:
	<input type="password" name="password" id="password">
	<form:errors path="password"/>
</p>
<input type="submit" value="로그인">
</form:form>

</body>
</html>