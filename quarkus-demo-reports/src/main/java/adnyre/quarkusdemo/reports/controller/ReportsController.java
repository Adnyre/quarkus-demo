package adnyre.quarkusdemo.reports.controller;

import adnyre.quarkusdemo.reports.dto.JobsByEmployee;
import adnyre.quarkusdemo.reports.dto.OrdersByMonth;
import adnyre.quarkusdemo.reports.service.ReportsService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.List;

@Path("/")
public class ReportsController {

    @Inject
    ReportsService reportsService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        System.out.println(new SimpleDateFormat("HH:mm:ss.SSS").format(new java.util.Date(System.currentTimeMillis())));
        return Response.ok("Ok", MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/reports/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrdersByMonth> getOrdersReport() {
        return reportsService.getOrdersByMonth();
    }

    @GET
    @Path("/reports/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobsByEmployee> getEmployeesReport() {
        return reportsService.getJobsByEmployee();
    }
}
