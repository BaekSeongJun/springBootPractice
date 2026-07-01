<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#postBtn").on("click", function() {
			var boardDTOArray = [{boardNo:"01", title:"pw01"},
                {boardNo:"02", title:"pw02"}];
			
			$.ajax({
				type : "post",
				url : "/test/gohome2",
				data : JSON.stringify(boardDTOArray),
				contentType : "application/json; charset=utf-8",
				success : function(result) {
					console.log("result: " + result);
					if (result === "SUCCESS") {
						alert("SUCCESS");
					}
				}
			});
		});

	});
</script>
<body>
	<h1>Ajax Home</h1>
	<div>
		<button id="postBtn">boardDTO(객체배열) 전송</button>
	</div>
</body>
</html>