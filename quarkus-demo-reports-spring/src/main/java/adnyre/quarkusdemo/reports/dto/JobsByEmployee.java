package adnyre.quarkusdemo.reports.dto;

public class JobsByEmployee {
    private String employeeName;

    private long jobs;

    private long finishedJobs;

    private long declinedJobs;

    public JobsByEmployee() {
    }

    public JobsByEmployee(String employeeName, long jobs, long finishedJobs, long declinedJobs) {
        this.employeeName = employeeName;
        this.jobs = jobs;
        this.finishedJobs = finishedJobs;
        this.declinedJobs = declinedJobs;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getJobs() {
        return jobs;
    }

    public void setJobs(long jobs) {
        this.jobs = jobs;
    }

    public long getFinishedJobs() {
        return finishedJobs;
    }

    public void setFinishedJobs(long finishedJobs) {
        this.finishedJobs = finishedJobs;
    }

    public long getDeclinedJobs() {
        return declinedJobs;
    }

    public void setDeclinedJobs(long declinedJobs) {
        this.declinedJobs = declinedJobs;
    }
}
