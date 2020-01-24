package pl.kamil25k.pcservice.User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.kamil25k.pcservice.Device.DeviceDto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class UserDto {

    private Long id;

    private String name;

    private String lastName;

    private String username;

    private String password;

    private int phoneNumber;

    private String email;

    private List<DeviceDto> device;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserDto() {
    }

    public UserDto(Long id, String name, String lastName, String username, String password, int phoneNumber, String email, List<DeviceDto> device, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.device = device;
        this.role = role;
    }
}
