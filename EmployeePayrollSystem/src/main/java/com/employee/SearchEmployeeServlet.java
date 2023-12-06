package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchEmployeeServlet")
public class SearchEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String employeeID = request.getParameter("employeeID2");

        String url = "jdbc:mysql://localhost:3306/empdetails";
        String username = "root";
        String password = "ABab12@@";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);

            String sql = "SELECT * FROM employee WHERE employee_id = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, employeeID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                response.getWriter().write("Valid");
            } else {
                response.getWriter().write("Invalid");
            }
        } catch (SQLException | ClassNotFoundException e) {
            response.getWriter().write("Error");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
