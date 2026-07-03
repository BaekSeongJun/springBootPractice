<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>homepage</title>
</head>
<body>
<h1>환영합니다.<br>
    <spring:message code="welcome.message" /><br>
    <spring:message code="welcome.message" arguments="홍길동" />
</h1>
</body>
</html>