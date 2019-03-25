package adnyre.quarkusdemo.reports.service;

import adnyre.quarkusdemo.reports.dto.*;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.YearMonth;
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
        List<OrderDto> orders = ordersService.get(false);
        return orders.stream()
                .collect(Collectors.groupingBy(
                        order -> YearMonth.from(order.getCreatedAt())
                ))
                .entrySet().stream()
                .map(entry -> {
                    YearMonth month = entry.getKey();
                    List<OrderDto> monthlyOrders = entry.getValue();
                    return new OrdersByMonth(month, monthlyOrders.size(), monthlyOrders.stream().filter(order -> order.getProcessed()).count());
                })
                .sorted(Comparator.comparing(OrdersByMonth::getMonth))
                .collect(Collectors.toList());
    }

    public List<JobsByEmployee> getJobsByEmployee() {
        List<JobDto> jobs = jobsService.get();
        return jobs.stream()
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
