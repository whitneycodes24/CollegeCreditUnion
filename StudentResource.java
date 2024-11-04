package resources;

import entities.Student;
import dao.StudentDAO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/students") //base path for all student-related endpoints
@Produces(MediaType.APPLICATION_JSON) //response format
@Consumes(MediaType.APPLICATION_JSON) //request body format
public class StudentResource {

    @Inject
    private StudentDAO studentDAO; //injecting StudentDAO to interact with database

    // GET /students - Retrieve all students
    @GET
    public Response getAllStudents() {
        List<Student> students = studentDAO.getAllStudents();     //calls DAO to get all students
        return Response.ok(students).build();                     //responds with status 200 and student list
    }

    // GET /students/{id} - Retrieve a student by ID
    @GET
    @Path("/{id}")
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentDAO.findStudentById(id); //calls DAO to find student by ID
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build(); //responds with 404 if student not found
        }
        return Response.ok(student).build(); //responds with status 200 and student data
    }

    // POST /students - Create a new student
    @POST
    public Response createStudent(Student student) {
        studentDAO.addStudent(student); //calls DAO to save new student
        return Response.status(Response.Status.CREATED).entity(student).build(); //responds with 201 status and the created student
    }

    // PUT /students/{id} - Update an existing student by ID
    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") Long id, Student updatedStudent) {
        Student existingStudent = studentDAO.findStudentById(id); //finds the student to be updated
        if (existingStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build(); //responds with 404 if student not found
        }

        //update fields of the existing student with new values
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setStudentNumber(updatedStudent.getStudentNumber());
        existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
        existingStudent.setAddress(updatedStudent.getAddress());
        existingStudent.setProgrammeCode(updatedStudent.getProgrammeCode());

        studentDAO.updateStudent(existingStudent); //calls DAO to update the student
        return Response.ok(existingStudent).build(); //responds with 200 status and updated student data
    }

    // DELETE /students/{id} - Delete a student by ID
    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        Student student = studentDAO.findStudentById(id); //finds the student to delete
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build(); //responds with 404 if student not found
        }

        studentDAO.deleteStudentById(id); //calls DAO to delete the student
        return Response.noContent().build(); //responds with 204 status, indicating successful deletion without body content
    }
}
