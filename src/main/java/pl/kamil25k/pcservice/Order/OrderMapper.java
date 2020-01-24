package pl.kamil25k.pcservice.Order;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class OrderMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public OrderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderDto mapToDto(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        OrderDto orderDto;
        if (order.getFinishDate() != null) {
            String newFinishDate = order.getFinishDate().format(formatter);
            String newStartDate = order.getStartDate().format(formatter);
            orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setStartDate(newStartDate);
            orderDto.setFinishDate(newFinishDate);
        } else {
            String newStartDate = order.getStartDate().format(formatter);
            orderDto = modelMapper.map(order, OrderDto.class);
            orderDto.setStartDate(newStartDate);
        }
        return orderDto;
    }

    public Order mapToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
