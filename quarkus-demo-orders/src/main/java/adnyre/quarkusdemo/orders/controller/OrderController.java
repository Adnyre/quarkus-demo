package adnyre.quarkusdemo.orders.controller;

import adnyre.quarkusdemo.orders.dto.OrderDto;
import adnyre.quarkusdemo.orders.service.OrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/orders")
public class OrderController {

    @Inject
    OrderService orderService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto get(@PathParam("id") long id) {
        return orderService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDto> get(@DefaultValue("false") @QueryParam("unprocessedOnly") boolean unprocessedOnly) {
        if (unprocessedOnly) {
            return orderService.getUnprocessedOrders();
        } else {
            return orderService.getOrders();
        }
    }

    @PUT
    @Path("/{id}/process")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDto setProcessed(@PathParam("id") long id) {
        return orderService.setProcessed(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDto save(OrderDto order) {
        return orderService.save(order);
    }
}