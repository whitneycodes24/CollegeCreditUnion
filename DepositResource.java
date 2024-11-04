package resources;


import entities.Deposit;
import entities.Loan;
import services.DepositService;
import services.LoanService;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/deposits")
public class DepositResource {

    @Inject                                             //depending injection, allows an instance into a field
    private DepositService depositService;

    @Inject
    private LoanService loanService;

    // Create a deposit for a loan
    @POST                                               //responds to HTTP POST requests
    @Path("/{loanId}")                               //endpoint path as loanId is path parameter representing ID
    @Consumes(MediaType.APPLICATION_JSON)            //method "consumes" the data from the JSON in the request body
    @Produces(MediaType.APPLICATION_JSON)            //method that "produces" data from JSON in response body
    public Response createDeposit(@PathParam("loanId") Long loanId, Deposit deposit) {
        Loan loan = loanService.findById(loanId);         //gets the loan by the loanId
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Loan not found").build();
        }

        deposit.setLoan(loan);                            //set the loan with the deposit 
        depositService.save(deposit, loan);             //save deposit w/ deposit service and 
        
        return Response.status(Response.Status.CREATED).entity(deposit).build();
    }

    // Get all deposits for a loan
    @GET                                           //HTTP request
    @Path("/loan/{loanId}")                       //endpoint path as loanId is path parameter representing ID
    @Produces(MediaType.APPLICATION_JSON)        //method that "produces" data from JSON in response body
    public Response getDepositsByLoanId(@PathParam("loanId") Long loanId) {
        Loan loan = loanService.findById(loanId);       //get loan by loanId in loan service
        if (loan == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Loan not found").build();
        }
        
        List<Deposit> deposits = depositService.findByLoanId(loanId); 
        return Response.ok(deposits).build();
    }

    // Get a specific deposit
    @GET                                        //HTTP request
    @Path("/{depositId}")                      //endpoint path as depositId is path parameter representing ID
    @Produces(MediaType.APPLICATION_JSON)      //method that "produces" data from JSON in response body
    public Response getDeposit(@PathParam("depositId") Long depositId) {   //gets depositId path parameter from url to deposit method parameter
        Deposit deposit = depositService.findById(depositId);           //findById method of depositService passing the depositId as a parameter
        if (deposit == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Deposit not found").build();
        }
        
        return Response.ok(deposit).build();
    }

    // Delete a specific deposit
    @DELETE                                 //HTTP request
    @Path("/{depositId}")                  //endpoint path as depositId is path parameter representing ID
    public Response deleteDeposit(@PathParam("depositId") Long depositId) {
        Deposit deposit = depositService.findById(depositId);
        if (deposit == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Deposit not found").build();
        }
        
        depositService.delete(depositId);
        return Response.noContent().build();
    }
}
