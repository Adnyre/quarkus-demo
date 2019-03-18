package adnyre.quarkusdemo.orders.dao;

import adnyre.quarkusdemo.orders.model.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class OrderDao {

    @Inject
    EntityManager em;

    public Order getById(long id) {
        return em.find(Order.class, id);
    }

    public List<Order> getOrders() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        CriteriaQuery<Order> all = cq.select(rootEntry);
        TypedQuery<Order> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public List<Order> getUnprocessedOrders() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> rootEntry = cq.from(Order.class);
        Predicate predicate = cb.equal(rootEntry.get("processed"), false);
        CriteriaQuery<Order> all = cq.select(rootEntry).where(predicate);
        TypedQuery<Order> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void save(Order order) {
        em.persist(order);
    }
}
