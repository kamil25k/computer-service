package pl.kamil25k.pcservice.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    final private OrderRepository orderRepository;
    final private OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Collection<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<OrderDto> getOrderById(long id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapToDto);
    }

    public void saveOrders(OrderDto orderDto) {
        orderDto.setStatus(Status.IN_PROGRESS);
        Order order = orderMapper.mapToEntity(orderDto);
        order.setStartDate(LocalDateTime.now());
        orderRepository.save(order);
    }

    public void updateOrder(OrderDto orderDto) {
        LocalDateTime startTime = orderRepository.findById(orderDto.getId()).get().getStartDate();
        LocalDateTime finishTime = orderRepository.findById(orderDto.getId()).get().getFinishDate();
        Order order = orderMapper.mapToEntity(orderDto);
        order.setStartDate(startTime);
        order.setFinishDate(finishTime);
        orderRepository.save(order);
    }

    public void finishOrder(Long id) {
        Order order = orderRepository.findById(id).get();
        order.setFinishDate(LocalDateTime.now());
        order.setStatus(Status.COMPLETE);
        orderRepository.save(order);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDto> getOrderByUserId(Long id) {
        return orderRepository.findAllByUserId(id)
                .stream()
                .map(orderMapper::mapToDto)
                .collect(Collectors.toList());
    }


}

