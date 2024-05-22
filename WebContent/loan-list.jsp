<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <style>
   		 body {
			 background-color: #E5E1DA !important;
				}
		table.table-bordered > thead > tr > th,
	    table.table-bordered > tbody > tr > td {
	        border: 2px solid #000; /* Change border color here */
	    }
       
        .content {
            padding: 20px;
        }
        .main-content {
            margin: 0 auto; 
            max-width: 1000px; 
        }
        footer {
            position: fixed;
            bottom: 0;
            width: 100%;
            background-color: #333333;
            color: white;
            text-align: center;
            padding: 10px 0;
        }
    </style>
</head>
<body>
	<header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #333333">
            <div>
                <a href="https://www.javaguides.net" class="navbar-brand"> Loan Management App </a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/list_loan" class="nav-link">Loans</a></li>
            </ul>
        </nav>
    </header>
    
    <div class="container-fluid">
        <div class="row">
            
            <div class="col-md-10">
                <div class="content">
                    <!-- Main content wrapper -->
                    <div class="main-content">
                        <!-- Main content here -->
                        <div class="container">
                            <h3 class="text-center">List of Loans</h3>
                            <hr>
                            <div class="container text-left">
                                <a href="<%=request.getContextPath()%>/newLoan" class="btn btn-success">Add New Loan</a>
                            </div>
                            <br>
                            <div class="table-responsive text-center">
                                <table class="table table-bordered d-inline-block">
                                    <thead>
                                        <tr>
                                            <th>Loan ID</th>
                                            <th>Loan Amount</th>
                                            <th>Interest Rate</th>
                                            <th>Loan Term</th>
                                            <th>Total Pay</th>
                                            <th>Monthly Pay</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="Loan" items="${listLoans}">
                                            <tr>
                                                <td><c:out value="${Loan.loanID}" /></td>
                                                <td><c:out value="${Loan.loanAmount}" /></td>
                                                <td><c:out value="${Loan.interestRate}" /></td>
                                                <td><c:out value="${Loan.loanTerm}" /></td>
                                                <td><c:out value="${Loan.totalPay}" /></td>
                                                <td><c:out value="${Loan.monthlyPay}" /></td>      
                                                <td>
												    <a href="<%=request.getContextPath()%>/editLoan?loanID=<c:out value='${Loan.loanID}' />" class="btn btn-primary">Edit</a>
												    <a href="#" onclick="confirmDelete('<c:out value='${Loan.loanID}' />')" class="btn btn-danger">Delete</a>
												</td>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <footer>
        <!-- Footer content here -->
        <p>&copy; 2024 Loan Management</p>
    </footer>
    
    <script>
    function confirmDelete(loanID) {
        if (confirm("Are you sure you want to delete this loan?")) {
            window.location.href = "<%=request.getContextPath()%>/deleteLoan?loanID=" + loanID;
        }
    }
</script>
    
    
</body>
</body>
</html>