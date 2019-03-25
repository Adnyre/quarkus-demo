package adnyre.quarkusdemo.reports.dto;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDate;

public class OrdersByMonth {

    @JsonbDateFormat("MMMM yyyy")
    private LocalDate month;

    private long orders;

    private long unprocessedOrders;

    public OrdersByMonth() {
    }

    public OrdersByMonth(LocalDate month, long orders, long unprocessedOrders) {
        this.month = month;
        this.orders = orders;
        this.unprocessedOrders = unprocessedOrders;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public long getOrders() {
        return orders;
    }

    public void setOrders(long orders) {
        this.orders = orders;
    }

    public long getUnprocessedOrders() {
        return unprocessedOrders;
    }

    public void setUnprocessedOrders(long unprocessedOrders) {
        this.unprocessedOrders = unprocessedOrders;
    }
}
