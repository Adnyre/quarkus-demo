package adnyre.quarkusdemo.jobs.controller;

import adnyre.quarkusdemo.jobs.dto.EmployeeDto;
import adnyre.quarkusdemo.jobs.dto.JobDto;
import adnyre.quarkusdemo.jobs.service.EmployeeService;
import adnyre.quarkusdemo.jobs.service.JobService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class JobController {

    @Inject
    JobService jobService;

    @Inject
    EmployeeService employeeService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok("Ok", MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("/jobs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JobDto getById(@PathParam("id") long id) {
        return jobService.getById(id);
    }

    @GET
    @Path("/jobs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobDto> get() {
        return jobService.getJobs();
    }

    @GET
    @Path("/employees/{employeeId}/jobs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobDto> getByAssignee(@PathParam("employeeId") long employeeId) {
        return jobService.getJobsByAssigneeId(employeeId);
    }

    @PUT
    @Path("/employees/{employeeId}/jobs/{jobId}/assign")
    @Produces(MediaType.APPLICATION_JSON)
    public JobDto assign(@PathParam("employeeId") long employeeId,
                         @PathParam("jobId") long jobId) {
        return jobService.assign(jobId, employeeId);
    }

    @PUT
    @Path("/jobs/{jobId}/start")
    @Produces(MediaType.APPLICATION_JSON)
    public JobDto assign(@PathParam("jobId") long jobId) {
        return jobService.start(jobId);
    }

    @PUT
    @Path("/jobs/{jobId}/finish")
    @Produces(MediaType.APPLICATION_JSON)
    public JobDto finish(@PathParam("jobId") long jobId) {
        return jobService.finish(jobId);
    }

    @POST
    @Path("/jobs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JobDto saveJob(JobDto job) {
        return jobService.save(job);
    }

    @POST
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployeeDto saveEmployee(EmployeeDto employee) {
        return employeeService.save(employee);
    }

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }
}