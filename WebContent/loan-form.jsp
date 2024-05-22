<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
    body {
        background-color: #E5E1DA !important;
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
    .card-body {
        background-color: #F2F1EB;
        padding: 20px;
    }
</style>
<link rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
        style="background-color: #333333">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> Loan Management App </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list_loan"
                class="nav-link">Loans</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${Loan != null}">
                <form id="loanForm" action="updateLoan" method="post">
            </c:if>
            <c:if test="${Loan == null}">
                <form id="loanForm" action="insertLoan" method="post">
            </c:if>

            <caption>
                <h2>
                    <c:if test="${Loan != null}">
                        Edit Loan
                    </c:if>
                    <c:if test="${Loan == null}">
                        Add New Loan
                    </c:if>
                </h2>
            </caption>

            <c:if test="${Loan != null}">
                <input type="hidden" name="loanID" value="<c:out value='${Loan.loanID}' />" />
            </c:if>

            <fieldset class="form-group">
                <label>Loan Amount</label> 
                <input type="number"
                    value="<c:out value='${Loan.loanAmount}' />" 
                    class="form-control"
                    name="loanAmount" 
                    id="loanAmount"
                    step="0.01"
                    required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Interest Rate (%)</label> 
                <input type="number"
                    value="<c:out value='${Loan.interestRate}' />" 
                    class="form-control"
                    name="interestRate" 
                    id="interestRate"
                    step="0.01"
                    required="required">
            </fieldset>

            <fieldset class="form-group">
			    <label>Loan Term (Years)</label> 
			    <input type="number"
			        value="<c:out value='${Loan.loanTerm}' />" 
			        class="form-control"
			        name="loanTerm" 
			        id="loanTerm"
			        required="required"
			        step="1">
			</fieldset>

            
            <fieldset class="form-group">
                <label>Total Pay</label> 
                <input type="number"
                    value="<c:out value='${Loan.totalPay}' />" 
                    class="form-control"
                    name="totalPay" 
                    id="totalPay"
                    step="0.01"
                    required="required" readonly>
            </fieldset>
            
            <fieldset class="form-group">
                <label>Monthly Pay</label> 
                <input type="number"
                    value="<c:out value='${Loan.monthlyPay}' />" 
                    class="form-control"
                    name="monthlyPay" 
                    id="monthlyPay"
                    step="0.01"
                    required="required" readonly>
            </fieldset>

            <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
<footer>
    <!-- Footer content here -->
    <p>&copy; 2024 User Management</p>
</footer>

<script>
    document.getElementById('loanForm').addEventListener('input', function() {
        var loanAmount = parseFloat(document.getElementById('loanAmount').value);
        var interestRate = parseFloat(document.getElementById('interestRate').value);
        var loanTerm = parseFloat(document.getElementById('loanTerm').value);

        var totalInterest = loanAmount * (interestRate / 100) * loanTerm;
        var totalPay = loanAmount + totalInterest;
        var monthlyPay = totalPay / (loanTerm * 12);

        document.getElementById('totalPay').value = totalPay.toFixed(2);
        document.getElementById('monthlyPay').value = monthlyPay.toFixed(2);
    });
</script>

</body>
</html>
