package pl.kamil25k.pcservice.Device;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public DeviceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DeviceDto mapToDto(Device device) {
        return modelMapper.map(device, DeviceDto.class);
    }

    public Device mapToEntity(DeviceDto deviceDto) {
        return modelMapper.map(deviceDto, Device.class);
    }
}
