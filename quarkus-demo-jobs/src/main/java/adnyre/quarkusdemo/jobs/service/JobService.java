package adnyre.quarkusdemo.jobs.service;

import adnyre.quarkusdemo.jobs.dao.EmployeeDao;
import adnyre.quarkusdemo.jobs.dao.JobDao;
import adnyre.quarkusdemo.jobs.dto.JobDto;
import adnyre.quarkusdemo.jobs.model.Employee;
import adnyre.quarkusdemo.jobs.model.Job;
import adnyre.quarkusdemo.jobs.model.JobStatus;
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
}
