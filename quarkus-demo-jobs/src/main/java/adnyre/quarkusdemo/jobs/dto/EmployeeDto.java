package adnyre.quarkusdemo.jobs.dto;

import adnyre.quarkusdemo.jobs.model.Employee;

public class EmployeeDto {
    public Long id;

    public String fullName;

    public static EmployeeDto from(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setFullName(employee.getFullName());
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

    public Employee to() {
        Employee employee = new Employee();
        employee.setId(this.id);
        employee.setFullName(this.fullName);
        return employee;
    }
}
