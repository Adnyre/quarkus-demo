package adnyre.quarkusdemo.jobs.dao;

import adnyre.quarkusdemo.jobs.model.Employee;
import adnyre.quarkusdemo.jobs.model.Job;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@ApplicationScoped
public class JobDao {

    @Inject
    EntityManager em;

    public Job getById(long id) {
        return em.find(Job.class, id);
    }

    public List<Job> getJobs() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> rootEntry = cq.from(Job.class);
        CriteriaQuery<Job> all = cq.select(rootEntry);
        TypedQuery<Job> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Job> getJobsByAssigneeId(long assigneeId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> rootEntry = cq.from(Job.class);
        Join<Job, Employee> employeeJoin = rootEntry.join("assignee");
        Predicate predicate = cb.equal(employeeJoin.get("id"), assigneeId);
        CriteriaQuery<Job> all = cq.select(rootEntry).where(predicate);
        TypedQuery<Job> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void save(Job job) {
        em.persist(job);
    }
}
