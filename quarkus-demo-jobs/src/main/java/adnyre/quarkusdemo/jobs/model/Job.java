package adnyre.quarkusdemo.jobs.model;

import javax.persistence.*;

@Entity
public class Job {
    @Id
    @SequenceGenerator(
            name = "jobSequence",
            sequenceName = "job_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobSequence")
    public Long id;

    @Enumerated(EnumType.STRING)
    public JobStatus status;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    public Employee assignee;

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

    public Employee getAssignee() {
        return assignee;
    }

    public void setAssignee(Employee assignee) {
        this.assignee = assignee;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
