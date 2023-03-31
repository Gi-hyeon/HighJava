<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<%
	// 로그인 id를 쿠키에서 읽어온다.
	String cookieUserId = "";
	String chk = ""; // CheckBox의 체크 여부를 지정할 변수

	Cookie[] cookieArr = request.getCookies();
	
	if(cookieArr != null){
		for(Cookie cookie : cookieArr){
			if("USERID".equals(cookie.getName())){
				cookieUserId = cookie.getValue();
				chk = "checked";
			}
		}
	}

%>
<body>
<form action="<%= request.getContextPath()%>/cookieLoginServlet.do" method="get">
	<table style="margin: 0 auto;">
	
		<tr>
			<td>ID : </td>
			<td><input type="text" name="userid" placeholder="ID를 입력하세요" value="<%=cookieUserId%>"></td>
		</tr>
	
		<tr>
			<td>PASS :</td>
			<td><input type="text" name="userpass" placeholder="PassWord를 입력하세요"></td>
		</tr>
	
		<tr>
			<td colspan="2"><input type="checkbox" name="chkid" value="check" <%= chk %>>ID 기억하기</td>
		</tr>
	
		<tr>
			<td colspan="2" style="text-align: center;"><input type="submit" value="check"> </td>
		</tr>
		
		
	</table>
</form>
</body>
</html>