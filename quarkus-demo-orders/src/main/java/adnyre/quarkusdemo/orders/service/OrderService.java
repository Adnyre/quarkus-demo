package adnyre.quarkusdemo.orders.service;

import adnyre.quarkusdemo.orders.dto.OrderDto;
import adnyre.quarkusdemo.orders.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    public OrderDto getById(long id) {
        Order order = Order.findById(id);
        return OrderDto.from(order);
    }

    public List<OrderDto> getOrders() {
        List<Order> orders = Order.listAll();
        LOGGER.info("Found " + orders.size() + " orders");
        return orders.stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getUnprocessedOrders() {
        List<Order> orders = Order.list("processed", false);
        LOGGER.info("Found " + orders.size() + " unprocessed orders");
        return orders.stream()
                .map(OrderDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDto setProcessed(long id) {
        Order order = Order.findById(id);
        LOGGER.info("Setting processed for order: " + order.getId());
        order.setProcessed(true);
        return OrderDto.from(order);
    }

    @Transactional
    public OrderDto save(OrderDto dto) {
        Order order = dto.to();
        order.persist();
        return OrderDto.from(order);
    }
}
