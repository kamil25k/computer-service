package pl.kamil25k.pcservice.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        userService.getUserById(id).ifPresent(user -> model.addAttribute("user", user));
        return "user/user";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/userForm";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/userForm";
        } else {
            userService.saveUser(user);
            return "redirect:/user/all";
        }
    }

    @GetMapping("/update")
    public String updateUser(@Valid UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "user/userForm";
        } else {
            userService.updateUser(user);
            return "redirect:/user/all";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {

        }
        return "redirect:/user/all";
    }

    @GetMapping("/modify/{id}")
    public String modifyUser(@PathVariable("id") long id, Model model) {
        userService.getUserById(id).ifPresent(user -> model.addAttribute("user", user));
        return "user/userModifyForm";
    }

    @GetMapping("/device/{id}")
    public String getUserDevices(@PathVariable("id") long id, Model model) {
        UserDto user = userService.getUserById(id).get();
        model.addAttribute("device", user.getDevice());
        model.addAttribute("user", user);
        return "device/deviceUser";
    }
}
