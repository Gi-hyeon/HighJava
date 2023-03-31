<%@page import="kr.or.ddit.vo.LprodVO"%>
<%@page import="java.util.List"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<LprodVO> voList = (List<LprodVO>) request.getAttribute("listValue");
		
	Gson gson = new Gson();
	String result = gson.toJson(voList);
	
	out.print(result);
	out.flush();
	
%>
