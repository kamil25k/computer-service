package pl.kamil25k.pcservice.Order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.kamil25k.pcservice.Device.DeviceDto;
import pl.kamil25k.pcservice.User.UserDto;

@Getter
@Setter
@ToString
@Builder
public class OrderDto {

    private Long id;

    private UserDto user;

    private String description;

    private DeviceDto device;

    private String startDate;

    private String finishDate;

    private int expectedCost;

    private int finalCost;

    private Status status;

    public OrderDto(Long id, UserDto user, String description, DeviceDto device, String startDate, String finishDate, int expectedCost, int finalCost, Status status) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.device = device;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.expectedCost = expectedCost;
        this.finalCost = finalCost;
        this.status = status;
    }

    public OrderDto() {
    }
}
