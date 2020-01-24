package pl.kamil25k.pcservice.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kamil25k.pcservice.User.UserDto;
import pl.kamil25k.pcservice.User.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;
    private final UserService userService;


    @Autowired
    public DeviceService(DeviceRepository deviceRepository, DeviceMapper deviceMapper, UserService userService) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
        this.userService = userService;
    }

    public List<DeviceDto> getAllDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(deviceMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<DeviceDto> getDeviceById(Long id) {
        return deviceRepository.findById(id)
                .map(deviceMapper::mapToDto);
    }

    public void saveDevice(DeviceDto deviceDto, long id) {
        UserDto userDto = userService.getUserById(id).get();
        deviceDto.setUser(userDto);
        Device device = deviceMapper.mapToEntity(deviceDto);
        Device savedDevice = deviceRepository.save(device);
        if (userDto.getDevice() != null) {
            userDto.getDevice().add(deviceMapper.mapToDto(savedDevice));
        } else {
            List<DeviceDto> deviceDtoList = new ArrayList<>();
            deviceDtoList.add(deviceMapper.mapToDto(savedDevice));
            userDto.setDevice(deviceDtoList);
        }
        userService.updateUser(userDto);

    }

    public void deleteDevice(long id) {
        deviceRepository.deleteById(id);
    }
}
