package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EmployeeDTO;
import facades.EmployeeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("employee")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
            return "Hello Frederik from employeeresource :-)";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }




    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHighestSalary() {
        try {
            EmployeeDTO employeeDTO = FACADE.getHighestSalary();
            return Response.ok().entity(GSON.toJson(employeeDTO)).build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }
    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployeeById(@PathParam("id") long id) {
        try {
            EmployeeDTO employeeDTO = FACADE.getById(id);
            return Response.ok().entity(GSON.toJson(employeeDTO)).build();
        } catch (Exception e) {
            return Response.noContent().build();
        }
    }
    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployeesWithSpecificName(@PathParam("name") String name) {
            try {
                List<EmployeeDTO> employeeDTO = FACADE.getAllEmployeesByName(name);
                return Response.ok().entity(GSON.toJson(employeeDTO)).build();
            } catch (Exception e) {
                return Response.noContent().build();
            }
        }


    @GET
    @Produces("text/plain")
    @Path("/queryDemo")
    public String getText(   @QueryParam("id") int id,
                             @QueryParam("name") String name) {
        return "{\"name\":\""+name+"\"}";
    }

//    @Path("/{username}")
//        @GET
//        @Produces("text/plain")
//        public String getUser(@PathParam("username") String userName){
//        return "Hello " +userName;
//    }




//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response postExample(String input){
////        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
////        System.out.println(rmdto);
//        return Response.ok().entity().build();
//    }
}
