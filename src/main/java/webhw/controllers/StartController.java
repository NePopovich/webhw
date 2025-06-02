package webhw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import webhw.model.User;
import webhw.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StartController {

    private UserService userService;

    private static User currentUser;

    private static List<String> messages;

    public StartController() {
    }

    @Autowired
    public StartController(UserService userService) {
        this.userService = userService;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        StartController.currentUser = currentUser;
    }

    public static List<String> getMessages() {
        return messages;
    }

    public static void setMessages(List<String> messages) {
        StartController.messages = messages;
    }

    @GetMapping("/")
    public String startPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/info")
    public String hiPage(ModelMap model) {
        User user = currentUser;
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("messages", messages);
        } else {
            List<String> currentMessages = new ArrayList<>();
            messages.add("Не найден");
            model.addAttribute("messages", currentMessages);
        }
        return "info";
    }

}
