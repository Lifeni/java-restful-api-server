package lfn.java;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
public class MainService {
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMethod() {
        return EventHandler.find();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMethod(@PathParam("id") String id) {
        return EventHandler.find(id);
    }

    @POST
    @Path("/post")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public String postMethod(String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            DataModel data = mapper.readValue(body, DataModel.class);
            return EventHandler.add(data);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    @PUT
    @Path("/put/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public String putMethod(String body, @PathParam("id") String id) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            DataModel data = mapper.readValue(body, DataModel.class);
            return EventHandler.update(data, id);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"status\":\"ERROR\"}";
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteMethod(@PathParam("id") String id) {
        return EventHandler.remove(id);
    }

    public static void main(String args[]) {
        DBHandler.run();
    }
}
