package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Person;
import errorhandling.GenericExceptionMapper;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello Frederik :-)\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }
    @GET
    @Produces("text/plain")
    @Path("/queryDemo")
    public String getText(   @QueryParam("id") int id,
                             @QueryParam("name") String name) {
        return "{\"name\":\""+name+"\"}";
    }

    @Path("/username/{username}")
        @GET
        @Produces("text/plain")
        public String getUser(@PathParam("username") String userName){
        return "Hello " +userName;
    }

        @Path("/testexception")
        @GET
        @Produces("text/plain")
        public String throwException() throws Exception {
        throw new Exception("My exception");
    }


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPerson(String jsonInput){
//        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
//        System.out.println(rmdto);
        PersonDTO person = GSON.fromJson(jsonInput, PersonDTO.class);
        PersonDTO returned = FACADE.create(person);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

    @Path("/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
//    public Response updatePerson( String jsonInput){
        public Response updatePerson( @PathParam("id") long id, String jsonInput){

        PersonDTO personDTO = GSON.fromJson(jsonInput, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO returned = FACADE.updatePerson(personDTO);
//        PersonDTO returned = FACADE.updatePerson(personDTO, id);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }
    @Path("/user/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserById(@PathParam("id") long id) throws PersonNotFoundException {
        try {
            return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
        } catch (PersonNotFoundException e) {
            throw new PersonNotFoundException(e.getMessage());
        }

    }
}
