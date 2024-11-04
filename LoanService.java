package services;

import dao.LoanDAO;
import entities.Loan;

import javax.inject.Inject;
import java.util.List;

public class LoanService {

    @Inject
    private LoanDAO loanDAO;

    // Save a loan
    public void save(Loan loan) {
        loanDAO.save(loan);
    }

    // Find a loan by ID
    public Loan findById(Long loanId) {
        return loanDAO.findById(loanId);
    }

    // Update a loan
    public void update(Loan loan) {
        loanDAO.update(loan);
    }

    // Delete a loan by ID
    public void delete(Long loanId) {
        Loan loan = loanDAO.findById(loanId);
        if (loan != null) {
            loanDAO.delete(loanId);  // Pass the loan ID to delete instead of the Loan object
        }
    }

    // Get all loans 
    public List<Loan> findAll() {
        return loanDAO.findAll();
    }
}
