package adnyre.quarkusdemo.reports.service;

import adnyre.quarkusdemo.reports.dto.OrderDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient
@Path("orders")
public interface OrdersService {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    OrderDto get(@PathParam("id") long id);

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    List<OrderDto> get(@DefaultValue("false") @QueryParam("unprocessedOnly") boolean unprocessedOnly);
}