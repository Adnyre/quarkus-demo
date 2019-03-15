package adnyre.quarkusdemo.orders.dto;

import adnyre.quarkusdemo.orders.model.Car;

import java.time.LocalDate;

public class CarDto {

    private Long id;

    private String registrationNumber;

    private String model;

    private String colour;

    private LocalDate issuedAt;

    private Long mileage;

    public static CarDto from(Car car) {
        CarDto dto = new CarDto();
        dto.setId(car.getId());
        dto.setRegistrationNumber(car.getRegistrationNumber());
        dto.setModel(car.getModel());
        dto.setColour(car.getColour());
        dto.setIssuedAt(car.getIssuedAt());
        dto.setMileage(car.getMileage());
        return dto;
    }

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

    public Car to() {
        Car car = new Car();
        car.setId(this.id);
        car.setRegistrationNumber(this.registrationNumber);
        car.setModel(this.model);
        car.setColour(this.colour);
        car.setIssuedAt(this.issuedAt);
        car.setMileage(this.mileage);
        return car;
    }
}
