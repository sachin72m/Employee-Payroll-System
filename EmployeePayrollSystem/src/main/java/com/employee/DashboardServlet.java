package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
		super();
	}
	
	
	
      /**
       * @SEE... HERE I AM ONLY HANDLING HTML FORM SUBMISSION DATA MEANS
       *         GETTING DATA, AND SENDING TO THE DATABASE, THAT'SET.
       */

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Retrieving the form data ...
//		String employeeId = request.getParameter("employeeId");
//		String employeeName = request.getParameter("employeeName");
//		String companyName = request.getParameter("companyName");

//		request.setAttribute("employeeName", employeeName);
//		request.setAttribute("employeeId", employeeId);
//		request.setAttribute("companyName", companyName);
//
//        // Forward to the JSP page to display the data
//        request.getRequestDispatcher("displayEmployee.jsp").forward(request, response);

		
		
		// Fetching form data here..
		String employeeId = request.getParameter("employeeId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String emailID = request.getParameter("emailID");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String contact = request.getParameter("contactInfo");
        String gender = request.getParameter("gender");
        String dateOfJoining = request.getParameter("dateOfJoining");
        String companyName = request.getParameter("companyName");
        String salary = request.getParameter("salary");
        String companyLocation = request.getParameter("companyLocation");

		
		
		// ** Here creating jdbc connection to save employeeInfo into database.**
		Connection conn = null;
		PreparedStatement statement = null;
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("MySQL Connector/J JDBC driver loaded successfully");
			String url = "jdbc:mysql://localhost:3306/empdetails";
			String username = "root";
			String password = "ABab12@@";

			conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn); //Checking Connection Object....

			 // SQL query to insert employee data
            String sql = "INSERT INTO employee (employee_id, first_name, last_name, email_ID,"
            + " dob, address, city, contact, gender, "
            + "date_of_joining, company_name, salary, company_location) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            

            // Create prepared statement
            statement = conn.prepareStatement(sql);
            statement.setString(1, employeeId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, emailID);
            statement.setString(5, dob);
            statement.setString(6, address);
            statement.setString(7, city);
            statement.setString(8, contact);
            statement.setString(9, gender);
            statement.setString(10, dateOfJoining);
            statement.setString(11, companyName);
            statement.setString(12, salary);
            statement.setString(13, companyLocation);

            
            //Execute the query....
			int rows = statement.executeUpdate();

			// Close connections
			statement.close();
			conn.close();

			if (rows > 0) {
				out.println("<h1>Employee details saved successfully</h1>");
			} else {
				out.println("Failed to save employee details");
			}
			
			// Redirect to a EmployeeList page (EmployeeList.jsp)
			response.sendRedirect("EmployeeListServlet");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		
	}
}
