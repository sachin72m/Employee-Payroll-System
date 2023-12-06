package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveEmployeeData
 */
@WebServlet("/SaveEmployeeData")
public class SaveEmployeeData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveEmployeeData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieving form data here...
		String employeeId = request.getParameter("employeeId");
		String employeeName = request.getParameter("employeeName");
		String companyName = request.getParameter("companyName");

		Connection conn = null;
        PreparedStatement statement = null;
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("MySQL Connector/J JDBC driver loaded successfully");
            String url = "jdbc:mysql://localhost:3306/empinformation";
            String username = "root";
            String password = "ABab12@@";

            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);

            // SQL query to insert employee data
            String sql = "INSERT INTO employees(employee_name, employee_id, company_name) VALUES (?, ?, ?)";

            statement = conn.prepareStatement(sql);
            statement.setString(1, employeeName);
            statement.setString(2, employeeId);
            statement.setString(3, companyName);

            int rows = statement.executeUpdate();

         // Close connections
            statement.close();
            conn.close();

            
            if (rows > 0) {
                out.println("Employee details saved successfully");
            } else {
                out.println("Failed to save employee details");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

}
