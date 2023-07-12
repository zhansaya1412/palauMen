package kz.batyrbek.palaumen.palaumen.service;
import kz.batyrbek.palaumen.palaumen.dto.OrderDto;
import kz.batyrbek.palaumen.palaumen.mapper.OrderMapper;
import kz.batyrbek.palaumen.palaumen.model.Order;
import kz.batyrbek.palaumen.palaumen.repository.OrderRepository;
import lombok.RequiredArgsConstructor;;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;

    private final OrderRepository orderRepository;

    public List<OrderDto> getOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orderMapper.toDtoList(orders);
        return orderDtos;
    }
}
