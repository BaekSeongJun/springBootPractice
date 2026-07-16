<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>파일 업로드</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {

            var formObj = $("#item");

            $("#btnRegister").on("click", function() {
                formObj.attr("action", "/item2/insert");
                formObj.attr("method", "post");
                formObj.submit();
            });

            $("#btnList").on("click", function() {
                self.location = "/item2/list";
            });

        });
    </script>
</head>
<body>
<h2>상품 등록</h2>
<!-- 1. enctype 뒤의 잘못된 따옴표(”)를 정상적인 따옴표(")로 수정했습니다. -->
<form:form modelAttribute="item" action="/item2/insert" enctype="multipart/form-data">
    <table>
        <tr>
            <td>상품명</td>
            <td><form:input path="itemName" /></td>
            <td><font color="red"><form:errors path="itemName" /></font></td>
        </tr>
        <tr>
            <td>가격</td>
            <td><form:input path="price" />&nbsp;원</td>
            <td><font color="red"><form:errors path="price" /></font></td>
        </tr>
        <tr>
            <td>파일1</td>
            <td><input type="file" name="pictures" /></td>
            <td></td>
        </tr>
        <tr>
            <td>파일2</td>
            <td><input type="file" name="pictures" /></td>
            <td></td>
        </tr>
        <tr>
            <td>개요</td>
            <td><form:textarea path="description" /></td>
            <td><form:errors path="description" /></td>
        </tr>
    </table>

    <!-- 2. 버튼들을 form:form 태그 안으로 이동시켰습니다. -->
    <!-- 3. 중복 작동 방지를 위해 button 타입을 button으로 변경했습니다. -->
    <div>
        <button type="button" id="btnRegister">Register</button>
        <button type="button" id="btnList">List</button>
    </div>
</form:form>
</body>