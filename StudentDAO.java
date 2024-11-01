package dao;

import entities.Student;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StudentDAO {

	
	//create EntityManager instances for database
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CollegeCreditUnion");  
    //^uses persistence unit defined in xml


    // Create a new student
    public void addStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);  //this starts the transaction of persisting or saving the entity to the database
            em.getTransaction().commit();  //saves the transaction
        } finally {
            em.close();
        }
    }

    // Find a student by ID
    public Student findStudentById(Long id) {   //gets student ID
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Student.class, id);    //finds entity with the id
        } finally {
            em.close();
        }
    }

    // Update a student
    public Student updateStudent(Student student) {    //updates student in the database
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student updatedStudent = em.merge(student);     //merges the change into the database
            em.getTransaction().commit();
            return updatedStudent;
        } finally {
            em.close();
        }
    }

    // Delete a student by ID
    public void deleteStudentById(Long id) {     //deletes ID
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Student student = em.find(Student.class, id);    //finds the ID I just deleted
            if (student != null) {
                em.remove(student);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Get all students
    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Close EntityManagerFactory
    public static void close() {
        emf.close();
    }
}
