package pl.kamil25k.pcservice.Device;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kamil25k.pcservice.User.UserDto;

@Getter
@Setter
@Builder
public class DeviceDto {

    private Long id;

    private String deviceBrand;

    private String deviceModel;

    private String type;

    private String serialNumber;

    private UserDto user;

    public DeviceDto() {
    }

    public DeviceDto(Long id, String deviceBrand, String deviceModel, String type, String serialNumber, UserDto userDto) {
        this.id = id;
        this.deviceBrand = deviceBrand;
        this.deviceModel = deviceModel;
        this.type = type;
        this.serialNumber = serialNumber;
        this.user = userDto;
    }
}
