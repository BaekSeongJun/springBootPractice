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
        gender: <br>
            <input type="radio" name="gender" value="male" checked> Male<br>
            <input type="radio" name="gender" value="female"> Female<br>
            <input type="radio" name="gender" value="other"> Other<br>
        nationality:
        <select name="nationality" multiple>
            <option value="Korea" selected>대한민국</option>
            <option value="Germany">독일</option>
            <option value="Australia">호주</option>
            <option value="Canada">캐나다</option>
        </select><br>
        hobby: <br>
        <input type="checkbox" name="hobby" value="Sports" checked> Sports<br>
        <input type="checkbox" name="hobby" value="Music"> Music<br>
        <input type="checkbox" name="hobby" value="Movie"> Movie<br>
        foreigner: <input type="checkbox" name="foreigner"><br>
        <input type="submit" value="test/gohome1">
    </form>
    <hr>
    <form action="/test/gohome1" method="get">
        postCode: <input type="text" name="address[0].postCode" /><br>
        location: <input type="text" name="address[0].location" /><br>
        postCode: <input type="text" name="address[1].postCode" /><br>
        location: <input type="text" name="address[1].location" /><br>
        <input type="submit" value="test/gohome1 get방식">
    </form>
</body>
</html>