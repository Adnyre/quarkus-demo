package adnyre.quarkusdemo.jobs.dto;

import adnyre.quarkusdemo.jobs.model.Job;
import adnyre.quarkusdemo.jobs.model.JobStatus;

public class JobDto {
    public Long id;

    public JobStatus status;

    public EmployeeDto assignee;

    public Long orderId;

    public static JobDto from(Job job) {
        JobDto dto = new JobDto();
        dto.setId(job.getId());
        dto.setStatus(job.getStatus());
        if (job.getAssignee() != null) {
            dto.setAssignee(EmployeeDto.from(job.getAssignee()));
        }
        dto.setOrderId(job.getOrderId());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public EmployeeDto getAssignee() {
        return assignee;
    }

    public void setAssignee(EmployeeDto assignee) {
        this.assignee = assignee;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Job to() {
        Job job = new Job();
        job.setId(this.id);
        job.setStatus(this.status);
        job.setOrderId(this.orderId);
        return job;
    }
}
