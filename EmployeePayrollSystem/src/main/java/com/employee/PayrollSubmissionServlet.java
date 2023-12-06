package com.employee;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.lang.ProcessBuilder.Redirect;
import java.util.*;

@WebServlet("/PayrollSubmissionServlet")
public class PayrollSubmissionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve form data
        String employeeID = request.getParameter("employeeID");
        String salaryType = request.getParameter("salaryType");
        String salary = request.getParameter("salary");
        String firstName2 = request.getParameter("firstName2");
        String lastName2 = request.getParameter("lastName2");
        String travelAllowances = request.getParameter("travelAllowances");
        String foodAllowances = request.getParameter("foodAllowances");
        String performanceBonus = request.getParameter("performanceBonus");
        String epf             = request.getParameter("epf");
        String tax             = request.getParameter("tax");
        // ... other form fields

        // Create a Map or an object to hold form data
        Map<String, String> payrollData = new HashMap<>();
        payrollData.put("employeeID", employeeID);
        payrollData.put("salaryType", salaryType);
        payrollData.put("salary", salary);
        payrollData.put("firstName2", firstName2);
        payrollData.put("lastName2", lastName2);
        payrollData.put("travelAllowances", travelAllowances);
        payrollData.put("foodAllowances", foodAllowances);
        payrollData.put("performanceBonus", performanceBonus);
        payrollData.put("epf", epf);
        payrollData.put("tax", tax);
        // ... store other form fields in the map

        // Store payroll data in the session
        session.setAttribute("payrollData", payrollData);

        // Redirect to a success page or another JSP
      //  response.sendRedirect("payslip.jsp"); // Replace "success.jsp" with your success page
        response.sendRedirect("index.html");
    }
}
