package resources;

import entities.Loan;
import entities.Student;
import services.LoanService;
import services.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/loans")             
public class LoanResource {

    @Inject
    private LoanService loanService;

    @Inject
    private StudentService studentService;

    // Create a loan for a student
    @POST
    @Path("/{studentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLoan(@PathParam("studentId") Long studentId, Loan loan) {
        Student student = studentService.findById(studentId);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
        }
        
        student.assignLoan(loan);
        loanService.save(loan);
        studentService.update(student);
        
        return Response.status(Response.Status.CREATED).entity(loan).build();
    }

    // Get loan details by student ID
    @GET
    @Path("/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoanByStudentId(@PathParam("studentId") Long studentId) {
        Student student = studentService.findById(studentId);
        if (student == null || student.getLoan() == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Loan not found for the student").build();
        }
        
        return Response.ok(student.getLoan()).build();
    }

    // Update loan
    @PUT
    @Path("/{loanId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)                  //updated loan object serialized to JSON when returned
    public Response updateLoan(@PathParam("loanId") Long loanId, Loan updatedLoan) {  //connects path parameter from url to method parameter
        Loan loan = loanService.findById(loanId);
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Loan not found").build();
        }
        
        loan.setDescription(updatedLoan.getDescription());     //Sets the description of the existing loan object to the description in updatedLoan
        loan.setLoanAmount(updatedLoan.getLoanAmount());     //Sets the loan anmount of the existing loan object to the description in updatedLoan
        loanService.update(loan);
        
        return Response.ok(loan).build();
    }

    // Delete a loan
    @DELETE
    @Path("/{loanId}")
    public Response deleteLoan(@PathParam("loanId") Long loanId) {
        Loan loan = loanService.findById(loanId);
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Loan not found").build();
        }
        
        loanService.delete(loanId);
        return Response.noContent().build();
    }
}
