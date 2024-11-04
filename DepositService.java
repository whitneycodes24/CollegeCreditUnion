package services;


import dao.DepositDAO;
import entities.Deposit;
import entities.Loan;
import javax.inject.Inject;
import java.util.List;

public class DepositService {

    @Inject
    private DepositDAO depositDAO;

    // Save a deposit and link it to a loan
    public void save(Deposit deposit, Loan loan) {
        deposit.setLoan(loan);
        depositDAO.save(deposit);
    }

    // Find a deposit by ID
    public Deposit findById(Long depositId) {
        return depositDAO.findById(depositId);
    }

    // Update a deposit
    public void update(Deposit deposit) {
        depositDAO.update(deposit);
    }

    // Delete a deposit
    public void delete(Long depositId) {
        Deposit deposit = depositDAO.findById(depositId);
        if (deposit != null) {
            depositDAO.delete(deposit);
        }
    }

    // Get all deposits for a loan
    public List<Deposit> findByLoan(Loan loan) {
        return depositDAO.findByLoan(loan);
    }
    
    // Get deposits by loan ID if needed
    public List<Deposit> findByLoanId(Long loanId) {
        return depositDAO.findByLoanId(loanId);
    }
}
