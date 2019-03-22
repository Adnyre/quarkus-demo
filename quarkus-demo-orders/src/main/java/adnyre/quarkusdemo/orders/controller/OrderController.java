package adnyre.quarkusdemo.orders.controller;

import adnyre.quarkusdemo.orders.dto.OrderDto;
import adnyre.quarkusdemo.orders.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class OrderController {

    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok("Ok", MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto get(@PathParam("id") long id) {
        return orderService.getById(id);
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> get(@DefaultValue("false") @QueryParam("unprocessedOnly") boolean unprocessedOnly) {
        if (unprocessedOnly) {
            return orderService.getUnprocessedOrders();
        } else {
            return orderService.getOrders();
        }
    }

    @PUT
    @Path("orders/{id}/process")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto setProcessed(@PathParam("id") long id) {
        return orderService.setProcessed(id);
    }

    @POST
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDto save(OrderDto order) {
        return orderService.save(order);
    }
}