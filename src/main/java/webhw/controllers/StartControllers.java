package webhw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
public class StartControllers {

    private UserService userService;

    public StartControllers() {
    }

    @Autowired
    public StartControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String startPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping(value = "/hi")
    public String hiPage(ModelMap model) {
        User user = userService.getLastAddUser();
        model.addAttribute("user", user);
        return "hi";
    }
//
//    @PostMapping(value = "/")
//    public String saveUserToDB(@ModelAttribute("user") User user) {
//        userService.save(user);
//        return "index:/hi";
//    }
//
//    @GetMapping("/user")
//    public String userPage(ModelMap model) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Hello!");
//        messages.add("Now");
//        messages.add("All is GOOD!");
//        model.addAttribute("messages", messages);
//        return "user.html";
//    }
}
