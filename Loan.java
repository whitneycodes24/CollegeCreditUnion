package entities;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;
    private String description;
    private double loanAmount;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deposit> deposits = new ArrayList<>();

    // Default constructor
    public Loan() {
    }

    // Constructor
    public Loan(String description, double loanAmount) {
        this.description = description;
        this.loanAmount = loanAmount;
    }

    // Getters and Setters
    public Long getId() {
        return loanId;
    }

    public void setId(Long id) {
        this.loanId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        this.deposits = deposits;
    }

    // Convenience method to add a deposit
    public void addDeposit(Deposit deposit) {
        deposits.add(deposit);
        deposit.setLoan(this);  // Sets the relationship
    }

    @Override
    public String toString() {
        return "Loan [id=" + loanId + ", description=" + description + ", loanAmount=" + loanAmount + "]";
    }
}

