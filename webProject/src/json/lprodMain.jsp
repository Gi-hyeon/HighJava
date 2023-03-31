<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#lprodBtn').on('click', function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/lprodList.do",
				type : "get",
				success : function(res){
					alert("성공");
				},
				error : function(xhr){
					alert("상태 : " + xhr.status);
				},
				dataType : 'json'
			})
		});
	})
</script>
</head>
<body>
<!-- 
	아래의 'Lprod자료 가져오기'버튼을 클릭하면 DB의 LPROD테이블의 전체 테이블을 가져와	
	id가 'result'인 <div>태그에 표로 출력하시오.
	( Ajax이용, MVC패턴 사용, 서블릿의 URL패턴 : /lprodList.do )	
 -->
	<form>
		<input type="button" id="lprodBtn" value="Lprod자료 가져오기">
	</form>
	
	<h3>Lprod 자료 목록</h3>
	
	<div id="result"></div>
	
</body>
</html>