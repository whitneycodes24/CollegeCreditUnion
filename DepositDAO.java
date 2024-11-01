package dao;

import entities.Deposit;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class DepositDAO {

    @PersistenceContext                         //provides methods to deal with the database
    private EntityManager entityManager;       //declaring the entity manager

    // Method to save a new Deposit
    @Transactional                           //every method is treated as a single transaction
    public void save(Deposit deposit) {     //public method to accept deposit
        entityManager.persist(deposit);    //makes JPA save deposit to database
    }

    // Method to update an existing Deposit
    @Transactional
    public void update(Deposit deposit) {     //public method to update deposit
        entityManager.merge(deposit);         //makes JPA update deposit to database
    }

    // Method to find a Deposit by its ID
    public Deposit findById(Long depositId) {                      //method to find deposit
        return entityManager.find(Deposit.class, depositId);      //makes JPA find deposit 
    }

    

    // Method to delete a specific Deposit
    @Transactional
    public void delete(Long depositId) {
        Deposit deposit = findById(depositId);
        if (deposit != null) {
            entityManager.remove(deposit);
        }
    }
}
