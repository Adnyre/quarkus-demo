package adnyre.quarkusdemo.orders.dto;

import adnyre.quarkusdemo.orders.model.Car;
import adnyre.quarkusdemo.orders.model.Client;
import adnyre.quarkusdemo.orders.model.Order;

import java.time.LocalDateTime;

public class OrderDto {
    private Long id;

    private ClientDto client;

    private CarDto car;

    private String description;

    private String comment;

    private LocalDateTime createdAt;

    private Boolean processed;

    public static OrderDto from(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setDescription(order.getDescription());
        dto.setComment(order.getComment());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setProcessed(order.isProcessed());
        ClientDto clientDto = ClientDto.from(order.getClient());
        dto.setClient(clientDto);
        CarDto carDto = CarDto.from(order.getCar());
        dto.setCar(carDto);
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Order to() {
        Order order = new Order();
        order.setId(this.id);
        order.setDescription(this.description);
        order.setComment(this.comment);
        order.setCreatedAt(this.createdAt);
        order.setProcessed(this.processed != null ? this.processed : false);
        Client client = this.client.to();
        order.setClient(client);
        client.setOrder(order);
        Car car = this.car.to();
        order.setCar(car);
        car.setOrder(order);
        return order;
    }
}
