<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
    <link rel="stylesheet" href="https://rsms.me/inter/inter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/resultForm.css">
</head>
<body>

<div class="notion-result-container">

    <div class="notion-callout">
        <span class="notion-callout-emoji">🔔</span>
        <div class="notion-callout-text">
            ${boardDTO.writer} 실패 하였습니다.
        </div>
    </div>

    <a href="list" class="notion-link-btn">📁 목록으로 돌아가기</a>

</div>

</body>
</html>