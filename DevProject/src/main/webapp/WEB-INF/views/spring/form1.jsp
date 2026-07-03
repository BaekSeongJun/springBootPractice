<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spring Form</title>
</head>
<body>
    <h1>Model 데이터 전송 테스트 : ${error}</h1>
    <h2>Spring Form 입력 화면</h2>
    <!-- modelAttribute 속성에 폼 객체의 속성명을 지정한다. -->
    <form:form modelAttribute="member" method="post" action="/spring/register">
        <table>
            <tr>
                <td>유저ID</td>
                <td><form:input path="userId" />
                    <font color="red"><form:errors path="userId" /></font></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><form:password path="password" />
                    <font color="red"><form:errors path="password" /></font></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><form:input path="userName" />
                    <font color="red"><form:errors path="userName" /></font></td>
            </tr>
                <td>이메일</td>
                <td><form:input path="email" />
                    <font color="red"><form:errors path="email" /></font></td>
            </tr>
                <td>자기소개</td>
                <td><form:textarea path="introduction" rows="6" cols="40"/>
                    <font color="red"><form:errors path="introduction" /></font></td>
            </tr>
            </tr>
                <td>취미</td>
                <td><form:checkboxes path="hobbyValue" items="${member.hobbyMap}"/>
<%--                    <font color="red"><form:errors path="hobbyMap" /></font></td>--%>
            </td>
<%--            </tr>--%>
<%--                <td>취미</td>--%>
<%--                <td><form:checkboxes path="hobbyValue" itemValue="value" itemLabel="label" items="${member.hobbyList}"/>--%>
<%--&lt;%&ndash;                    <font color="red"><form:errors path="hobbyMap" /></font></td>&ndash;%&gt;--%>
<%--            </td>--%>
            </tr>
                <td>외국인 여부</td>
                <td><form:checkbox path="foreigner"/>
                    <font color="red"><form:errors path="foreigner" /></font></td>
            </td>
            </tr>
            <td>취미(hobbyArray)</td>
                <td>
                    <form:checkbox path="hobbyArray" value = "Sports" label="Sports"/><br>
                    <form:checkbox path="hobbyArray" value = "Music" label="Music"/><br>
                    <form:checkbox path="hobbyArray" value = "Movie" label="Movie"/>
                </td>
            </tr>
        </table>
        <form:button name="register">등록</form:button>
    </form:form>
</body>
</html>
