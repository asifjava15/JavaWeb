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
<jsp:include page="header.jsp"/>
<!-- <h4>Main content here</h4> -->
<%-- <jsp:include page="./destination"/> --%>
<table border="1" style="width: 600px; border-color: blue; height: 600px;">

<%List<Employee> employeesList= (List<Employee>) request.getAttribute("employees"); %>
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
<jsp:include page="footer.jsp"/>

</body>
</html>