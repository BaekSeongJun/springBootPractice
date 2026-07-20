<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>AOP 게시판 에러 처리</title>
</head>
<body>
    <h1>문제점이 발생하였습니다. 빨리 조치를 취하도록 하겠습니다.</h1>
    <a href="/board/list">게시판 리스트</a>
    <p>${exception}</p>
</body>
</html>