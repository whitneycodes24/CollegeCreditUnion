package services;

import dao.StudentDAO;
import entities.Loan;
import entities.Student;
import javax.inject.Inject;
import java.util.List;

public class StudentService {

    @Inject
    private StudentDAO studentDAO;

    //save a new student
    public void save(Student student) {
        studentDAO.addStudent(student);  
    }

    //find a student by ID
    public Student findById(Long studentId) {
        return studentDAO.findStudentById(studentId);  
    }

    //update student information
    public Student update(Student student) { 
        return studentDAO.updateStudent(student);  
    }

    //delete a student by ID
    public void delete(Long studentId) {
        Student student = studentDAO.findStudentById(studentId);  
        if (student != null) {
            studentDAO.deleteStudentById(studentId);  
        }
    }

    //assign a loan to a student 
    public void assignLoanToStudent(Long studentId, Loan loan) {
        Student student = studentDAO.findStudentById(studentId); 
        if (student != null && student.getLoan() == null) {
            student.assignLoan(loan);
            studentDAO.updateStudent(student);
        }
    }

    //retrieve all students
    public List<Student> findAll() {
        return studentDAO.getAllStudents();  
    }
}
