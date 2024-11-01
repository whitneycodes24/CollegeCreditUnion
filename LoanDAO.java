package dao;

import entities.Loan;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class LoanDAO {

    @PersistenceContext
    private EntityManager entityManager;

    // Method to save a new Loan
    @Transactional
    public void save(Loan loan) {
        entityManager.persist(loan);
    }

    // Method to update an existing Loan
    @Transactional
    public void update(Loan loan) {
        entityManager.merge(loan);
    }

    // Method to find a Loan by its ID
    public Loan findById(Long loanId) {
        return entityManager.find(Loan.class, loanId);
    }

    // Method to get all Loans
    public List<Loan> findAll() {
        return entityManager.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
    }

    // Method to delete a specific Loan
    @Transactional
    public void delete(Long loanId) {
        Loan loan = findById(loanId);
        if (loan != null) {
            entityManager.remove(loan);
        }
    }
}
