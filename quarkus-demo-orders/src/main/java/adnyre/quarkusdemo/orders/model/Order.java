package adnyre.quarkusdemo.orders.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "repairorder")
public class Order {
    @Id
    @SequenceGenerator(
            name = "orderSequence",
            sequenceName = "order_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSequence")
    public Long id;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, optional = false)
    public Client client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, optional = false)
    public Car car;

    public String description;

    public String comment;

    @Column(updatable = false)
    public LocalDateTime createdAt;

    public boolean processed;

    public Order() {
    }

    @PrePersist
    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
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

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
