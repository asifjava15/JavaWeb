<%@page import="com.training.j2ee.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> This is results jsp to render employee records</h1>

<%List<Employee> employeesList= (List<Employee>) request.getAttribute("employees"); %>
<table border="1">
<thead>
<td>EmpId</td>
<td>Name</td>
<td>Age</td>
<td>Gender</td>
</thead>
<%for(Employee employee:employeesList){ %>
<tr>
<td><%=employee.getEmpId() %></td>
<td><%=employee.getName() %></td>
<td><%=employee.getAge() %></td>
<td><%=employee.getGender() %></td>
</tr>
<%} %>
</table>
</body>
</html>