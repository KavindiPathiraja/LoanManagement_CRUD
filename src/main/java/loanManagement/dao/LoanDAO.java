package loanManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import loanManagement.model.Loan;


public class LoanDAO {
	
	private static final String INSERT_LOAN_SQL = "INSERT INTO loans" +
            "  (loanAmount, interestRate, loanTerm, totalPay, monthlyPay) VALUES " +
            " (?, ?, ?, ?, ?);";

    private static final String SELECT_LOAN_BY_ID = "SELECT loanID, loanAmount, interestRate, loanTerm, totalPay, monthlyPay FROM loans WHERE loanID = ?";
    private static final String SELECT_ALL_LOANS = "SELECT * FROM loans";
    private static final String DELETE_LOAN_SQL = "DELETE FROM loans WHERE loanID = ?";
    private static final String UPDATE_LOAN_SQL = "UPDATE loans SET loanAmount = ?, interestRate = ?, loanTerm = ?, totalPay = ?, monthlyPay = ? WHERE loanID = ?";

    //insert
    public void insertLoans(Loan loan) {
        System.out.println(INSERT_LOAN_SQL);

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOAN_SQL)) {
            preparedStatement.setString(1, loan.getLoanAmount());
            preparedStatement.setString(2, loan.getInterestRate());
            preparedStatement.setString(3, loan.getLoanTerm());
            preparedStatement.setString(4, loan.getTotalPay());
            preparedStatement.setString(5, loan.getMonthlyPay());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //update
    public boolean updateLoans(Loan loan) {
        boolean rowUpdated = false;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LOAN_SQL)) {
        	statement.setString(1, loan.getLoanAmount());
        	statement.setString(2, loan.getInterestRate());
        	statement.setString(3, loan.getLoanTerm());
        	statement.setString(4, loan.getTotalPay());
        	statement.setString(5, loan.getMonthlyPay());
            statement.setInt(6, loan.getLoanID());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    //select by id
    public Loan selectLoan(int loanID) {
        Loan loan = null;

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOAN_BY_ID)) {
            preparedStatement.setInt(1, loanID);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String loanAmount = rs.getString("loanAmount");
                String interestRate = rs.getString("interestRate");
                String loanTerm = rs.getString("loanTerm");
                String totalPay = rs.getString("totalPay");
                String monthlyPay = rs.getString("monthlyPay");

                loan = new Loan(loanID, loanAmount, interestRate, loanTerm, totalPay, monthlyPay);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loan;
    }

    //select all
    public List<Loan> selectAllLoans() {
        List<Loan> loans = new ArrayList<>();

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOANS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int loanID = rs.getInt("loanID");
                String loanAmount = rs.getString("loanAmount");
                String interestRate = rs.getString("interestRate");
                String loanTerm = rs.getString("loanTerm");
                String totalPay = rs.getString("totalPay");
                String monthlyPay = rs.getString("monthlyPay");

                loans.add(new Loan(loanID, loanAmount, interestRate, loanTerm, totalPay, monthlyPay));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    //delete
    public boolean deleteLoan(int loanID) {
        boolean rowDeleted = false;
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_LOAN_SQL)) {
            statement.setInt(1, loanID);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

}
