package adnyre.quarkusdemo.orders.dto;

import adnyre.quarkusdemo.orders.model.Client;

import java.time.LocalDate;

public class ClientDto {
    private Long id;

    private String fullName;

    private LocalDate birthDate;

    private String license;

    public static ClientDto from(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setFullName(client.getFullName());
        dto.setBirthDate(client.getBirthDate());
        dto.setLicense(client.getLicense());
        return dto;
    }

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

    public Client to() {
        Client client = new Client();
        client.setId(this.id);
        client.setFullName(this.fullName);
        client.setBirthDate(this.birthDate);
        client.setLicense(this.license);
        return client;
    }
}
