package pl.kamil25k.pcservice.Device;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.kamil25k.pcservice.User.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DEVICE_BRAND")
    private String deviceBrand;

    @Column(name = "DEVICE_MODEL")
    private String deviceModel;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    public Device() {
    }

    public Device(Long id, String deviceBrand, String deviceModel, String type, String serialNumber, User user) {
        this.id = id;
        this.deviceBrand = deviceBrand;
        this.deviceModel = deviceModel;
        this.type = type;
        this.serialNumber = serialNumber;
        this.user = user;
    }
}
