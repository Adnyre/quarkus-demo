package adnyre.quarkusdemo.jobs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employeeSequence",
            sequenceName = "employee_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSequence")
    public Long id;

    public String fullName;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    public List<Job> assignedJobs = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Job> getAssignedJobs() {
        return assignedJobs;
    }

    public void setAssignedJobs(List<Job> assignedJobs) {
        this.assignedJobs = assignedJobs;
    }
}
