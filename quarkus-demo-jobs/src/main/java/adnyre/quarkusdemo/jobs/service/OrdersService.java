package adnyre.quarkusdemo.jobs.service;

import adnyre.quarkusdemo.jobs.dto.OrderDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
@Path("orders")
public interface OrdersService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    OrderDto get(@PathParam("id") long id);
}