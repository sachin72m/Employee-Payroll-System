<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map.Entry" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pay Slip</title>
    <style>
        /* Basic CSS for styling the pay slip */
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .payslip {
            border: 1px solid #ccc;
            padding: 20px;
            width: 400px;
            margin: 0 auto;
            background-color: #f9f9f9;
        }
        .payslip h2 {
            text-align: center;
        }
        .payslip table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .payslip th, .payslip td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .payslip th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="payslip">
        <h2>Pay Slip</h2>

        <%-- Retrieve payroll data from the session --%>
        <% Map<String, String> payrollData = (Map<String, String>) session.getAttribute("payrollData");
           if (payrollData != null && !payrollData.isEmpty()) {
        %>
            <table>
                <tbody>
                    <!-- Display specific fields retrieved from payrollData -->
                    <% String[] fieldNames = {
                            "Employee ID", "Salary Type", "Salary", "First Name", "Last Name",
                            "Travel Allowances", "Food Allowances", "Performance Bonus", "EPF", "Tax"
                        };
                        String[] dataKeys = {
                            "employeeID", "salaryType", "salary", "firstName2", "lastName2",
                            "travelAllowances", "foodAllowances", "performanceBonus", "epf", "tax"
                        };

                        double salary = Double.parseDouble(payrollData.get("salary"));
                        double travelAllowances = Double.parseDouble(payrollData.get("travelAllowances"));
                        double foodAllowances = Double.parseDouble(payrollData.get("foodAllowances"));
                        double performanceBonus = Double.parseDouble(payrollData.get("performanceBonus"));
                        double epf = Double.parseDouble(payrollData.get("epf"));
                        double tax = Double.parseDouble(payrollData.get("tax"));

                        double totalDeductions = (epf + tax);
                        double netPay = (salary + travelAllowances + foodAllowances + performanceBonus) - totalDeductions;
                    %>
                        <% for (int i = 0; i < fieldNames.length; i++) {
                                String fieldName = fieldNames[i];
                                String value = "";
                                if (i < 8) {
                                    String dataKey = dataKeys[i];
                                    value = payrollData.get(dataKey);
                                } else if (i == 8) {
                                    value = String.valueOf(epf);
                                } else if (i == 9) {
                                    value = String.valueOf(tax);
                                }
                        %>
                            <tr>
                                <td><strong><%= fieldName %>:</strong></td>
                                <td><%= value %></td>
                            </tr>
                        <% } %>
                </tbody>
            </table>

            <!-- Display Net Pay amount -->
            <p><strong>Net Pay:</strong> <%= netPay %></p>
            <!-- Print button -->
            <button onclick="window.print()">Print</button>
        <% } else { %>
            <p>No payroll data available.</p>
        <% } %>
    </div>
</body>
</html>
