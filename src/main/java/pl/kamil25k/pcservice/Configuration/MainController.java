package pl.kamil25k.pcservice.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kamil25k.pcservice.Order.OrderDto;
import pl.kamil25k.pcservice.Order.OrderService;
import pl.kamil25k.pcservice.User.Role;
import pl.kamil25k.pcservice.User.User;
import pl.kamil25k.pcservice.User.UserDto;
import pl.kamil25k.pcservice.User.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public MainController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "register";
        } else {
            userService.saveUser(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/denied")
    public String accessDenied(){
        return "denied";
    }

    @GetMapping("")
    public String getHome(Principal principal, Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        if (user.getRole() == Role.ROLE_ADMIN) {
            model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
            return "home/adminHome";
        } else {
            List<OrderDto> orders = orderService.getOrderByUserId(user.getId());
            model.addAttribute("user", user);
            model.addAttribute("order", orders);
            return "home/userHome";
        }
    }
}
