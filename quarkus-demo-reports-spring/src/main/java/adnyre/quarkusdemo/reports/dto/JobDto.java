package adnyre.quarkusdemo.reports.dto;

public class JobDto {
    public Long id;

    public JobStatus status;

    public EmployeeDto assignee;

    public Long orderId;

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
}
