<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#sendFile").on("change",
                function(event){ console.log("change");

                    var files = event.target.files;
                    var file = files[0];
                    console.log(file);

                    var formData = new FormData();
                    formData.append("file", file);
                    $.ajax({
                        type : "post",
                        url : "/test/gohome3",
                        data: formData,
                        dataType:'text',
                        processData: false,
                        contentType: false,
                        success: function(data){
                            alert(data);
                        }
                    });
                });
        });
    </script>
<body>
	<h1>Ajax 파일 업로드</h1>
	<div>
		<input type="file" id="sendFile" name="sendFile" />
	</div>
</body>
</html>