package webhw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webhw.model.User;
import webhw.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController() {
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showHello(ModelMap model) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("All");
        list.add("Is GOOD!");
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping(value = "/")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/hi";
    }
}
