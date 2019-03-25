package adnyre.quarkusdemo.jobs.service;

import adnyre.quarkusdemo.jobs.dao.EmployeeDao;
import adnyre.quarkusdemo.jobs.dao.JobDao;
import adnyre.quarkusdemo.jobs.dto.JobDto;
import adnyre.quarkusdemo.jobs.dto.OrderDto;
import adnyre.quarkusdemo.jobs.model.Employee;
import adnyre.quarkusdemo.jobs.model.Job;
import adnyre.quarkusdemo.jobs.model.JobStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    @Inject
    JobDao jobDao;
    @Inject
    EmployeeDao employeeDao;
    @Inject
    @RestClient
    OrdersService ordersService;

    public JobDto getById(long id) {
        Job job = jobDao.getById(id);
        return JobDto.from(job);
    }

    public List<JobDto> getJobs() {
        List<Job> jobs = jobDao.getJobs();
        LOGGER.info("Found " + jobs.size() + " jobs");
        return jobs.stream()
                .map(JobDto::from)
                .collect(Collectors.toList());
    }

    public List<JobDto> getJobsByAssigneeId(long assigneeId) {
        List<Job> jobs = jobDao.getJobsByAssigneeId(assigneeId);
        LOGGER.info("Found " + jobs.size() + " jobs for assignee " + assigneeId);
        return jobs.stream()
                .map(JobDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public JobDto assign(long jobId, long assigneeId) {
        Job job = jobDao.getById(jobId);
        Employee assignee = employeeDao.getById(assigneeId);
        LOGGER.info("Setting assignee: " + assigneeId + " for job: " + jobId);
        assignee.getAssignedJobs().add(job);
        job.setAssignee(assignee);
        job.setStatus(JobStatus.ASSIGNED);
        return JobDto.from(job);
    }

    @Transactional
    public JobDto start(long jobId) {
        Job job = jobDao.getById(jobId);
        LOGGER.info("Starting job: " + jobId);
        job.setStatus(JobStatus.IN_PROGRESS);
        return JobDto.from(job);
    }

    @Transactional
    public JobDto finish(long jobId) {
        Job job = jobDao.getById(jobId);
        LOGGER.info("Finishing job: " + jobId);
        job.setStatus(JobStatus.FINISHED);
        return JobDto.from(job);
    }

    @Transactional
    public JobDto save(JobDto dto) {
        validateOrder(dto.getOrderId());
        Job job = dto.to();
        if (dto.getAssignee() != null) {
            Employee assignee = employeeDao.getById(dto.getAssignee().getId());
            job.setAssignee(assignee);
            assignee.getAssignedJobs().add(job);
        }
        job.setStatus(JobStatus.UNASSIGNED);
        jobDao.save(job);
        return JobDto.from(job);
    }

    private void validateOrder(Long orderId) {
        if (orderId == null) {
            throw new RuntimeException("Job should have an order id");
        }
        OrderDto order;
        try {
            order = ordersService.get(orderId);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find order by id: " + orderId, e);
        }
        if (order == null) {
            throw new RuntimeException("Order " + orderId + " not found");
        }
    }
}
