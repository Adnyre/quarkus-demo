package adnyre.quarkusdemo.reports.service;

import adnyre.quarkusdemo.reports.dto.JobDto;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RegisterRestClient
@Path("jobs")
public interface JobsService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<JobDto> get();
}
