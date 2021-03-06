package adnyre.quarkusdemo.reports.service;

import adnyre.quarkusdemo.reports.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JobsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.services.jobs.url}")
    private String jobsServiceUrl;

    public List<JobDto> get() {
        ResponseEntity<List<JobDto>> response = restTemplate.exchange(
                jobsServiceUrl + "/jobs",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<JobDto>>() {
                });
        return response.getBody();
    }
}
