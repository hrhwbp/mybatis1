<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="processDao" class = "pack.business.ProcessDao" />    
<%
int code = Integer.parseInt(request.getParameter("code"));
boolean b = processDao.deleteData(code);

if(b){
	response.sendRedirect("list.jsp");
}else{
%>
	<script>
	alert("삭제 실패!");
	loaction.href = "list.jsp";
	</script>
<%
}
%>
