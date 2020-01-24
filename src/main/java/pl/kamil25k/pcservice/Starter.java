package pl.kamil25k.pcservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.kamil25k.pcservice.Device.DeviceDto;
import pl.kamil25k.pcservice.Device.DeviceService;
import pl.kamil25k.pcservice.Order.OrderDto;
import pl.kamil25k.pcservice.Order.OrderService;
import pl.kamil25k.pcservice.User.UserDto;
import pl.kamil25k.pcservice.User.UserService;

import static pl.kamil25k.pcservice.User.Role.ROLE_ADMIN;

@Component
public class Starter implements CommandLineRunner {

    private UserService userService;
    private DeviceService deviceService;
    private OrderService orderService;

    @Autowired
    public Starter(UserService userService, DeviceService deviceService, OrderService orderService) {
        this.userService = userService;
        this.deviceService = deviceService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) {

        UserDto user1 = UserDto.builder()
                .name("Adam")
                .lastName("Kowalski")
                .username("admin")
                .password("admin")
                .phoneNumber(111111111)
                .email("adam@kowalski.com")
                .role(ROLE_ADMIN)
                .build();
        userService.saveUser(user1);

        UserDto user2 = UserDto.builder()
                .name("Jan")
                .lastName("Gorski")
                .username("user1")
                .password("user1")
                .phoneNumber(222222222)
                .email("jan@gorski.pl")
                .build();
        userService.saveUser(user2);


        UserDto user3 = UserDto.builder()
                .name("Tomasz")
                .lastName("Nowak")
                .username("user2")
                .password("user2")
                .email("tomasz@nowak.pl")
                .phoneNumber(333333333)
                .build();
        userService.saveUser(user3);

        DeviceDto device1 = DeviceDto.builder()
                .deviceBrand("Lenovo")
                .deviceModel("Legion Y520")
                .type("laptop")
                .serialNumber("H7HH6SA87H")
                .build();
        deviceService.saveDevice(device1, 3L);

        DeviceDto device2 = DeviceDto.builder()
                .deviceBrand("ASUS")
                .deviceModel("ZenBook")
                .type("laptop")
                .serialNumber("XGHS65GD")
                .build();
        deviceService.saveDevice(device2, 2L);

        DeviceDto device3 = DeviceDto.builder()
                .deviceBrand("DELL")
                .deviceModel("XPS 13")
                .type("ultrabook")
                .serialNumber("H7H2HHFDA")
                .build();
        deviceService.saveDevice(device3, 2L);

        OrderDto orders1 = OrderDto.builder()
                .user(userService.getUserById(3).get())
                .device(deviceService.getDeviceById(1L).get())
                .description("Broken screen")
                .expectedCost(700)
                .build();
        orderService.saveOrders(orders1);

        OrderDto orders2 = OrderDto.builder()
                .user(userService.getUserById(2).get())
                .device(deviceService.getDeviceById(2L).get())
                .description("Broken keyboard")
                .expectedCost(300)
                .build();
        orderService.saveOrders(orders2);

        OrderDto orders3 = OrderDto.builder()
                .user(userService.getUserById(2).get())
                .device(deviceService.getDeviceById(3L).get())
                .description("Laptop doesn't turn on.")
                .expectedCost(1000)
                .build();
        orderService.saveOrders(orders3);



    }
}

