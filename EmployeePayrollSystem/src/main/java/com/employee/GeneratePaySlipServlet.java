package com.employee;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GeneratePaySlipServlet
 */
@WebServlet("/GeneratePaySlipServlet")
public class GeneratePaySlipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GeneratePaySlipServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            
            // Retrieve payroll data from the session
            Map<String, String> payrollData = (Map<String, String>) session.getAttribute("payrollData");

            // Set payroll data as an attribute to forward it to the JSP page
            request.setAttribute("payrollData", payrollData);

            // Forward to a JSP page to display the pay slip
            RequestDispatcher dispatcher = request.getRequestDispatcher("payslip.jsp"); // Replace "payslip.jsp" with your JSP page
            dispatcher.forward(request, response);
        }
    }
