package com.training.j2ee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		out.println("<h1>First Servlet Program executed well</h1>");
		String name=request.getParameter("name");
		Long empId=Long.valueOf(request.getParameter("empId"));
		Integer age = Integer.valueOf(request.getParameter("age"));
		String gender = request.getParameter("gender");
		try  {
			// 1. Register the driver
			Class.forName("org.postgresql.Driver");
			// 2.Establishing the connection
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Training",
					"postgres", "tiger");
			System.out.println("Connected to PostgreSQL database!");
			// Creating the statement
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO db.\"Employee\"(\r\n"
					+ "	\"EmpId\", name, age, gender)\r\n"
					+ "	VALUES (?, ?, ?, ?)");
			
			System.out.println("Enter employee details");
			preparedStatement.setLong(1, empId);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3, age);
			preparedStatement.setString(4, gender);
			//Executing the query
			int result = preparedStatement.executeUpdate();
			if(result>0) {
				System.out.println("Employee record inserted succesfully");
			}

		} /*
			 * catch (ClassNotFoundException e) {
			 * System.out.println("PostgreSQL JDBC driver not found."); e.printStackTrace();
			 * }
			 */ catch (SQLException e) {
			System.out.println("Connection failure.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
		}
	

				out.println("<h1>Employye record"+name+" has been created</h1>");
	}
    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	PrintWriter out=response.getWriter();
//		out.println("<h1>First Servlet Program executed well</h1>");
//		String name=request.getParameter("name");
//		out.println("<h1>Name is:"+name+"</h1>");
//    }
    
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	PrintWriter out=response.getWriter();
//		out.println("<h1>First Servlet Program executed well</h1>");
//		String name=request.getParameter("name");
//		out.println("<h1>Name is:"+name+"</h1>");
//    }
    

}
