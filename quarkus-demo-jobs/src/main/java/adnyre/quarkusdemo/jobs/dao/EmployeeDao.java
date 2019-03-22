package adnyre.quarkusdemo.jobs.dao;

import adnyre.quarkusdemo.jobs.model.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class EmployeeDao {

    @Inject
    EntityManager em;

    public Employee getById(long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> getEmployees() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> rootEntry = cq.from(Employee.class);
        CriteriaQuery<Employee> all = cq.select(rootEntry);
        TypedQuery<Employee> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void save(Employee employee) {
        em.persist(employee);
    }

}
