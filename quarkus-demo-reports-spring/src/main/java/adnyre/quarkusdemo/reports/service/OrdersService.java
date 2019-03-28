package adnyre.quarkusdemo.reports.service;

import adnyre.quarkusdemo.reports.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.services.orders.url}")
    private String ordersServiceUrl;

    public OrderDto get(long id) {
        return restTemplate.getForEntity(ordersServiceUrl + "/orders/" + id, OrderDto.class).getBody();
    }

    public List<OrderDto> get(boolean unprocessedOnly) {
        ResponseEntity<List<OrderDto>> response = restTemplate.exchange(
                ordersServiceUrl + "/orders?unprocessedOnly" + unprocessedOnly,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<OrderDto>>() {
                });
        return response.getBody();
    }

}
