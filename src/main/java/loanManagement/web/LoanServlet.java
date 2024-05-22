package loanManagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import loanManagement.dao.LoanDAO;
import loanManagement.model.Loan;


@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private LoanDAO loanDAO;
    
    public LoanServlet() {
       this.loanDAO = new LoanDAO();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

        try {
            switch (action) {
                case "/newLoan":
                    showNewFormLoan(request, response);
                    break;
                case "/insertLoan":
                    insertLoan(request, response);
                    break;
                case "/deleteLoan":
                    deleteLoan(request, response);
                    break;
                case "/editLoan":
                    showEditFormLoan(request, response);
                    break;
                case "/updateLoan":
                    updateLoan(request, response);
                    break;
                default:
                    listLoans(request, response);
                    break;
            }  
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	private void showNewFormLoan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("loan-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertLoan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String loanAmount = request.getParameter("loanAmount");
        String interestRate = request.getParameter("interestRate");
        String loanTerm = request.getParameter("loanTerm");
        String totalPay = request.getParameter("totalPay");
        String monthlyPay = request.getParameter("monthlyPay");

        Loan newLoan = new Loan(loanAmount, interestRate, loanTerm, totalPay, monthlyPay);
        loanDAO.insertLoans(newLoan);
        response.sendRedirect("list_loan");
    }

    private void deleteLoan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int loanID = Integer.parseInt(request.getParameter("loanID"));
        loanDAO.deleteLoan(loanID);
        response.sendRedirect("list_loan");
    }

    private void showEditFormLoan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int loanID = Integer.parseInt(request.getParameter("loanID"));
        Loan existingLoan = loanDAO.selectLoan(loanID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loan-form.jsp");
        request.setAttribute("Loan", existingLoan);
        dispatcher.forward(request, response);
    }

    private void updateLoan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int loanID = Integer.parseInt(request.getParameter("loanID"));
        String loanAmount = request.getParameter("loanAmount");
        String interestRate = request.getParameter("interestRate");
        String loanTerm = request.getParameter("loanTerm");
        String totalPay = request.getParameter("totalPay");
        String monthlyPay = request.getParameter("monthlyPay");
        
        Loan loan = new Loan(loanID, loanAmount, interestRate, loanTerm, totalPay, monthlyPay);
        loanDAO.updateLoans(loan);
        response.sendRedirect("list_loan");
    }

    private void listLoans(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Loan> listLoans = loanDAO.selectAllLoans();
        request.setAttribute("listLoans", listLoans);
        RequestDispatcher dispatcher = request.getRequestDispatcher("loan-list.jsp");
        dispatcher.forward(request, response);
    }
	

}
