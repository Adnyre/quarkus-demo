package adnyre.quarkusdemo.jobs.service;

import adnyre.quarkusdemo.jobs.dao.EmployeeDao;
import adnyre.quarkusdemo.jobs.dto.EmployeeDto;
import adnyre.quarkusdemo.jobs.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Inject
    EmployeeDao employeeDao;

    public EmployeeDto getById(long id) {
        Employee employee = employeeDao.getById(id);
        return EmployeeDto.from(employee);
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        LOGGER.info("Found " + employees.size() + " employees");
        return employees.stream()
                .map(EmployeeDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmployeeDto save(EmployeeDto dto) {
        Employee employee = dto.to();
        employeeDao.save(employee);
        return EmployeeDto.from(employee);
    }
}
