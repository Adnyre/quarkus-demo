package adnyre.quarkusdemo.reports.controller;

import adnyre.quarkusdemo.reports.dto.JobsByEmployee;
import adnyre.quarkusdemo.reports.dto.OrdersByMonth;
import adnyre.quarkusdemo.reports.service.ReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
public class ReportsController {

    Logger logger = LoggerFactory.getLogger(ReportsController.class);

    @Autowired
    private ReportsService reportsService;

    @GetMapping(value = "", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String healthCheck() {
        logger.warn(new SimpleDateFormat("HH:mm:ss.SSS").format(new java.util.Date(System.currentTimeMillis())));
        return "Ok";
    }

    @GetMapping(value = "/reports/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrdersByMonth> getOrdersReport() {
        return reportsService.getOrdersByMonth();
    }

    @GetMapping(value = "/reports/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobsByEmployee> getEmployeesReport() {
        return reportsService.getJobsByEmployee();
    }

}
