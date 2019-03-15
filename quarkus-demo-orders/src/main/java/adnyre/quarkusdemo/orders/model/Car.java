package adnyre.quarkusdemo.orders.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Car {

    @Id
    @Column(name = "id")
    private Long id;

    private String registrationNumber;

    private String model;

    private String colour;

    private LocalDate issuedAt;

    private Long mileage;

    @OneToOne
    @MapsId
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public LocalDate getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDate issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
