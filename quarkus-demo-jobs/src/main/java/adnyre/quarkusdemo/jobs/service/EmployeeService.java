package adnyre.quarkusdemo.jobs.service;

import adnyre.quarkusdemo.jobs.dto.EmployeeDto;
import adnyre.quarkusdemo.jobs.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeDto getById(long id) {
        Employee employee = Employee.findById(id);
        return EmployeeDto.from(employee);
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = Employee.listAll();
        LOGGER.info("Found " + employees.size() + " employees");
        return employees.stream()
                .map(EmployeeDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeDto save(EmployeeDto dto) {
        Employee employee = dto.to();
        employee.persist();
        return EmployeeDto.from(employee);
    }
}
