package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
    private String studentName;
    private String studentNumber;
    private String phoneNumber;
    private String address;
    private String programmeCode;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Loan loan;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(String name, String studentNumber, String phoneNumber, String address, String programmeCode) {
        this.studentName = name;
        this.studentNumber = studentNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.programmeCode = programmeCode;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setId(Long id) {
        this.studentId = id;
    }

    public String getName() {
        return studentName;
    }

    public void setName(String name) {
        this.studentName = name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    // Convenience method to assign a loan to the student
    public void assignLoan(Loan loan) {
        this.loan = loan;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", name=" + studentName + ", studentNumber=" + studentNumber + ", phoneNumber=" + phoneNumber
                + ", address=" + address + ", programmeCode=" + programmeCode + ", loan=" + loan + "]";
    }
}
