package com.training.j2ee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetEmployees
 */
public class GetEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetEmployees() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			// 1. Register the driver
			Class.forName("org.postgresql.Driver");
			// 2.Establishing the connection
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Training", "postgres",
					"tiger");
			System.out.println("Connected to PostgreSQL database!");
			// Creating the statement
			// Creating the statement
			Statement statement = connection.createStatement();
			System.out.println("Reading employee records...");
			// Executing the query
			ResultSet resultSet = statement.executeQuery("SELECT \"EmpId\", name, age, gender FROM db.\"Employee\"");
			List<Employee> employeesList= new ArrayList<>();
			while (resultSet.next()) {
				Employee employee= new Employee();
				employee.setAge(resultSet.getInt("age"));
				employee.setGender(resultSet.getString("gender"));
				employee.setName(resultSet.getString("name"));
				employee.setEmpId(resultSet.getLong("empId"));
				employeesList.add(employee);
			}
			request.setAttribute("employees", employeesList);
			RequestDispatcher requestDispatcher= request.getRequestDispatcher("results.jsp");
			requestDispatcher.forward(request, response);
		} /*
			 * catch (ClassNotFoundException e) {
			 * System.out.println("PostgreSQL JDBC driver not found."); e.printStackTrace();
			 * }
			 */ catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
		}

	}

}
