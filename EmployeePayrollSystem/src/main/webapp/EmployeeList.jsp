<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee List</title>
    <style>
    header {
			background-color: orangered;
			color: white;
			padding: 10px;
			text-align: center;
		}
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<header>
    <h1>Employee List</h1>
</header>
    
    <table>
        <thead>
            <tr>
                <th>Employee ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email ID</th>
                <th>Date of Birth</th>
                <th>Address</th>
                <th>City</th>
                <th>Contact</th>
                <th>Gender</th>
                <th>Date of Joining</th>
                <th>Company Name</th>
                <th>Salary</th>
                <th>Company Location</th>
                <!-- Add other headers for employee details -->
            </tr>
        </thead>
        <tbody>
            <!-- Retrieve data from session attribute and display in the JSP -->
            <c:forEach var="employee" items="${sessionScope.employees}">
                <tr>
             
                    <td>${employee.employeeId}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.emailId}</td>
                    <td>${employee.dob}</td>
                    <td>${employee.address}</td>
                    <td>${employee.city}</td>
                    <td>${employee.contact}</td>
                    <td>${employee.gender}</td>
                    <td>${employee.dateOfJoining}</td>
                    <td>${employee.companyName}</td>
                    <td>${employee.salary}</td>
                    <td>${employee.companyLocation}</td>
                    <!-- Add other cells for employee details -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
