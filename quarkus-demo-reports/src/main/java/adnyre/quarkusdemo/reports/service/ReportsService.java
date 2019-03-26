package adnyre.quarkusdemo.reports.service;

import adnyre.quarkusdemo.reports.dto.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReportsService {

    @Inject
    @RestClient
    OrdersService ordersService;
    @Inject
    @RestClient
    JobsService jobsService;

    public List<OrdersByMonth> getOrdersByMonth() {
        List<OrderDto> orders;
        try {
            orders = ordersService.get(false);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find orders", e);
        }
        return orders.stream()
                .collect(Collectors.groupingBy(
                        order -> LocalDate.from(order.getCreatedAt())
                ))
                .entrySet().stream()
                .map(entry -> {
                    LocalDate month = entry.getKey();
                    List<OrderDto> monthlyOrders = entry.getValue();
                    return new OrdersByMonth(month, monthlyOrders.size(), monthlyOrders.stream().filter(OrderDto::getProcessed).count());
                })
                .sorted(Comparator.comparing(OrdersByMonth::getMonth))
                .collect(Collectors.toList());
    }

    public List<JobsByEmployee> getJobsByEmployee() {
        List<JobDto> jobs;
        try {
            jobs = jobsService.get();
        } catch (Exception e) {
            throw new RuntimeException("Unable to find jobs", e);
        }
        return jobs.stream()
                .filter(job -> job.getAssignee() != null)
                .collect(Collectors.groupingBy(
                        job -> job.getAssignee().getFullName())
                )
                .entrySet().stream()
                .map(entry -> {
                            String name = entry.getKey();
                            List<JobDto> employeeJobs = entry.getValue();
                            return new JobsByEmployee(
                                    name,
                                    employeeJobs.size(),
                                    employeeJobs.stream().filter(job -> JobStatus.FINISHED.equals(job.getStatus())).count(),
                                    employeeJobs.stream().filter(job -> JobStatus.DECLINED.equals(job.getStatus())).count()
                            );
                        }
                )
                .collect(Collectors.toList());
    }

}
