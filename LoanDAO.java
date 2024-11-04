package dao;

import entities.Loan;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class LoanDAO {

    @PersistenceContext
    private EntityManager entityManager;     //handles database interactions

    // Method to save a new Loan
    @Transactional                          //handles it as one transaction
    public void save(Loan loan) {
        entityManager.persist(loan);        //save loan to database
    }

    // Method to update an existing Loan
    @Transactional
    public void update(Loan loan) {          //takes loan object as a parameter
        entityManager.merge(loan);          //updates loan in database
    }

    // Method to find a Loan by its ID
    public Loan findById(Long loanId) {
        return entityManager.find(Loan.class, loanId);
    }

    // Method to get all Loans
    public List<Loan> findAll() {            //retrieve all loan entries in the database
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
