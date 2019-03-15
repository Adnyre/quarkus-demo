package adnyre.quarkusdemo.orders.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Client {

    @Id
    @Column(name = "id")
    private Long id;

    private String fullName;

    private LocalDate birthDate;

    private String license;

    @OneToOne
    @MapsId
    private Order order;


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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
