package adnyre.quarkusdemo.reports.dto;

import java.time.YearMonth;

public class OrdersByMonth {
    private YearMonth month;

    private long orders;

    private long unprocessedOrders;

    public OrdersByMonth() {
    }

    public OrdersByMonth(YearMonth month, long orders, long unprocessedOrders) {
        this.month = month;
        this.orders = orders;
        this.unprocessedOrders = unprocessedOrders;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
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
