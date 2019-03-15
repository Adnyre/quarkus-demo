package adnyre.quarkusdemo.jobs.service;

import adnyre.quarkusdemo.jobs.dto.JobDto;
import adnyre.quarkusdemo.jobs.model.Employee;
import adnyre.quarkusdemo.jobs.model.Job;
import adnyre.quarkusdemo.jobs.model.JobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class JobService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobService.class);

    public JobDto getById(long id) {
        Job job = Job.findById(id);
        return JobDto.from(job);
    }

    public List<JobDto> getJobs() {
        List<Job> jobs = Job.listAll();
        LOGGER.info("Found " + jobs.size() + " jobs");
        return jobs.stream()
                .map(JobDto::from)
                .collect(Collectors.toList());
    }

    public List<JobDto> getJobsByAssigneeId(long assigneeId) {
        List<Job> jobs = Job.list("assignee.id", assigneeId);
        LOGGER.info("Found " + jobs.size() + " jobs for assignee " + assigneeId);
        return jobs.stream()
                .map(JobDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public JobDto assign(long jobId, long assigneeId) {
        Job job = Job.findById(jobId);
        Employee assignee = Employee.findById(assigneeId);
        LOGGER.info("Setting assignee: " + assigneeId + " for job: " + jobId);
        assignee.getAssignedJobs().add(job);
        job.setAssignee(assignee);
        job.setStatus(JobStatus.ASSIGNED);
        return JobDto.from(job);
    }

    @Transactional
    public JobDto start(long jobId) {
        Job job = Job.findById(jobId);
        LOGGER.info("Starting job: " + jobId);
        job.setStatus(JobStatus.IN_PROGRESS);
        return JobDto.from(job);
    }

    @Transactional
    public JobDto finish(long jobId) {
        Job job = Job.findById(jobId);
        LOGGER.info("Finishing job: " + jobId);
        job.setStatus(JobStatus.FINISHED);
        return JobDto.from(job);
    }

    @Transactional
    public JobDto save(JobDto dto) {
        Job job = dto.to();
        job.setStatus(JobStatus.UNASSIGNED);
        job.persist();
        return JobDto.from(job);
    }
}
