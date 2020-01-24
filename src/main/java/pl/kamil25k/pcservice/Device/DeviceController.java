package pl.kamil25k.pcservice.Device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamil25k.pcservice.User.UserDto;
import pl.kamil25k.pcservice.User.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/device")
public class DeviceController {

    final private DeviceService deviceService;
    final private UserService userService;

    @Autowired
    public DeviceController(DeviceService deviceService, UserService userService) {
        this.deviceService = deviceService;
        this.userService = userService;
    }

    @RequestMapping("/all")
    public String getAllDevices(Model model) {
        model.addAttribute("device", deviceService.getAllDevices());
        return "device/devices";
    }

    @RequestMapping("/{id}")
    public String getDevice(@PathVariable("id") Long id, Model model) {
        deviceService.getDeviceById(id).ifPresent(device -> model.addAttribute("device", device));
        return "device/device";
    }

    @RequestMapping("/new/{id}")
    public String createDevice(@PathVariable("id") Long id, Model model) {
        UserDto userDto = userService.getUserById(id).get();
        DeviceDto deviceDto = DeviceDto.builder().user(userDto).build();
        model.addAttribute("device", deviceDto);
        return "device/deviceForm";
    }

    @PostMapping("/save")
    public String saveDevice(@Valid DeviceDto device, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "device/deviceForm";
        } else {
            long id = device.getUser().getId();
            deviceService.saveDevice(device, id);
            return "redirect:/device/all/";
        }
    }


    @RequestMapping("/delete/{id}")
    public String deleteDevice(@PathVariable("id") long id) {
        try {
            deviceService.deleteDevice(id);
        } catch (Exception e) {

        }
        return "redirect:/device/all/";
    }

    @RequestMapping("/modify/{id}")
    public String modifyDevice(@PathVariable("id") long id, Model model) {
        deviceService.getDeviceById(id).ifPresent(device -> model.addAttribute("device", device));
        return "device/deviceModifyForm";
    }
}
