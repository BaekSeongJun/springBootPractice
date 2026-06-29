<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/test/gohome1" method="post">
        boardNo : <input type ="text" name="boardNo" value="10"><br>
        title : <input type ="text" name="title" value="Title"><br>
        cotent : <input type ="text" name="content" value="Content"><br>
        writer : <input type ="text" name="writer" value="zeus"><br>
        coin : <input type ="text" name="coin" value="100"><br>
        <input type="submit" value="test/gohome1">
    </form>
</body>
</html>