package pl.kamil25k.pcservice.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kamil25k.pcservice.Device.DeviceDto;
import pl.kamil25k.pcservice.Device.DeviceService;
import pl.kamil25k.pcservice.User.UserDto;
import pl.kamil25k.pcservice.User.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/order")
public class OrderController {

    final private OrderService orderService;
    final private UserService userService;
    final private DeviceService deviceService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, DeviceService deviceService) {
        this.orderService = orderService;
        this.userService = userService;
        this.deviceService = deviceService;
    }

    @GetMapping("/all")
    public String getAllOrders(Model model) {
        model.addAttribute("order", orderService.getAllOrders());
        return "order/orders";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable("id") long id, Model model) {
        orderService.getOrderById(id).ifPresent(order -> model.addAttribute("order", order));
        return "order/order";
    }

    @GetMapping("/new/{id}/{userId}")
    public String crateOrder(@PathVariable("id") long id, @PathVariable("userId") long userId, Model model) {
        DeviceDto deviceDto = deviceService.getDeviceById(id).get();
        UserDto userDto = userService.getUserById(userId).get();
        OrderDto orderDto = OrderDto.builder()
                .user(userDto)
                .device(deviceDto)
                .build();

        model.addAttribute("order", orderDto);
        return "order/orderForm";
    }

    @PostMapping("/save")
    public String saveOrder(@Valid OrderDto orderDto, Long user, Long device, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("W formularzu pojawiły się błędy");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + error.getDefaultMessage());
            });
            return "order/orderForm";
        } else {

//            orderService.saveOrders(orderDto, user, device);
            orderService.saveOrders(orderDto);
            return "redirect:/order/all";
        }
    }

    @PostMapping("/update")
    public String updateOrder(@Valid OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("W formularzu pojawiły się błędy");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + error.getDefaultMessage());
            });
            return "order/orderForm";
        } else {

            orderService.updateOrder(orderDto);
            return "redirect:/order/all";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id) {
        try {
            orderService.deleteOrder(id);
        } catch (Exception e) {

        }
        return "redirect:/order/all";
    }

    @PostMapping("/modify/{id}")
    public String modifyOrder(@PathVariable("id") long id, Model model) {
        orderService.getOrderById(id).ifPresent(order -> model.addAttribute("order", order));
        return "order/orderModifyForm";
    }

    @PostMapping("/finish/{id}")
    public String finishOrder(@PathVariable("id") Long id) {
        orderService.finishOrder(id);
        return "redirect:/order/all";
    }
}



