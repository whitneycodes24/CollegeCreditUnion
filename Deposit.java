package entities;


	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.ManyToOne;

	@Entity
	public class Deposit {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String depositDate;
	    private double amount;

	    @ManyToOne
	    private Loan loan;

	    // Default constructor
	    public Deposit() {
	    }

	    // Constructor
	    public Deposit(String depositDate, double amount) {
	        this.depositDate = depositDate;
	        this.amount = amount;
	    }

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getDepositDate() {
	        return depositDate;
	    }

	    public void setDepositDate(String depositDate) {
	        this.depositDate = depositDate;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public Loan getLoan() {
	        return loan;
	    }

	    public void setLoan(Loan loan) {
	        this.loan = loan;
	    }

	    @Override
	    public String toString() {
	        return "Deposit [id=" + id + ", depositDate=" + depositDate + ", amount=" + amount + "]";
	    }
	}
