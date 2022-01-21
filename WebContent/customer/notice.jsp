<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Notice.jsp</h3>
<%
String sql="select * from notices order by to_number(seq) desc";
//dbcon
Class.forName("oracle.jdbc.driver.OracleDriver");

String url="jdbc:oracle:thin:@localhost:1521:xe";
String user="hr";
String pw="123456";
Connection con=DriverManager.getConnection(url,user,pw);
//실행
Statement st=con.createStatement();

//결과
ResultSet rs=st.executeQuery(sql);
%>

<table width="500" border="1">

<tr>
	<td>번호</td>
	<td>제목</td>
	<td>작성자</td>
	<td>작성일</td>
	<td>조회수</td>
</tr>
<%
//http://localhost:8700/jspCrypt4/login/loginprocaa.do?id=blue&pw=
while(rs.next()){
%>
<tr>
	<td><%=rs.getString("seq") %></td>
	<td><a href="noticeDetail.jsp?c=<%=rs.getString("seq") %>"><%=rs.getString("title") %></a></td>
	<td><%=rs.getString("writer") %></td>
	<td><%=rs.getDate("regdate") %></td>
	<td><%=rs.getInt("hit") %></td>
</tr>
<%
}
%>
</table>

<a href="noticeReg.jsp">write</a>
</body>
</html>
<%
rs.close();
st.close();
con.close();
%>