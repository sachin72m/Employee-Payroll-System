package com.employee;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeList
 */
@WebServlet("/EmployeeListServlet")
public class EmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeListServlet() {
        super();
    }
    
    
    
    /**
     *  @SEE...HERE I AM ONLY HANDLING GETTING DATA WHICH IS SAVE IN THE DATABASE
     *         AND SHOWS TO THE ALL EMPLOYEE USER LIST THROUGH JSP FILE. THAT'SET.
     */

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Fetching Employee details which is saved in database.
		 List<Employee> employeeList = new ArrayList<>();

	        // JDBC connection variables
		    String url = "jdbc:mysql://localhost:3306/empdetails";
			String username = "root";
			String password = "ABab12@@";

	        // JDBC objects for connection and statement
	        try 
	        (Connection conn = DriverManager.getConnection(url, username, password)) {
	        	
	            // SQL query to select all employees
	            String sql = "SELECT * FROM employee"; // Change 'employees' to your table name

	            try (Statement statement = conn.createStatement();
	                 ResultSet resultSet = statement.executeQuery(sql)) {

	                // Process the result set
	                while (resultSet.next()) {
	                    Employee employee = new Employee();
	                    // Set employee details from the result set
//	                    employee.setId(resultSet.getInt("id")); //for unique id identifier..
	                    employee.setEmployeeId(resultSet.getString("employee_id"));
	                    employee.setFirstName(resultSet.getString("first_name"));
	                    employee.setLastName(resultSet.getString("last_name"));
	                    employee.setEmailId(resultSet.getString("email_ID"));
	                    employee.setDob(resultSet.getString("dob"));
	                    employee.setAddress(resultSet.getString("address"));
	                    employee.setCity(resultSet.getString("city"));
	                    employee.setContact(resultSet.getString("contact"));
	                    employee.setGender(resultSet.getString("gender"));
	                    employee.setDateOfJoining(resultSet.getString("date_of_joining"));
	                    employee.setCompanyName(resultSet.getString("company_name"));
	                    employee.setSalary(resultSet.getDouble("salary"));
	                    employee.setCompanyLocation(resultSet.getString("company_location"));


	                    // Add employee to the list
	                    employeeList.add(employee);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        // Store the employeeList in session attributes
//Creating session to save details on the browser,doesn't matter if page gets refreshed.
	        
	        request.setAttribute("employees", employeeList);
	        HttpSession session = request.getSession();
	        session.setAttribute("employees", employeeList);
	        
	        //Checking session is storing data or not and,  YES it's storing data.
//	        System.out.println("Employee data stored in session: " + employeeList); // Log the data

	        

	        // Forward the request to a JSP
	        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeList.jsp");
	        dispatcher.forward(request, response);

	    }
		
}
