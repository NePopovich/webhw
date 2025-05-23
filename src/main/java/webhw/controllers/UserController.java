package webhw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webhw.model.User;
import webhw.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/")
    public String saveUser(@ModelAttribute("user") User user) {
        List<String> messages = new ArrayList<>();
        messages.add("Здравствуйте!");
        messages.add("Добро пожаловать!");
        messages.add("Выберите дальнейшее действие");
        userService.setMessages(messages);
        userService.save(user);
        return "redirect:/hi";
    }

    @GetMapping(value = "/users")
    public String showAllUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping(value = "/motion")
    public String showChangeUser() {
        return "motion";
    }

    @PostMapping(value = "/motion")
    public String motionWithUser(@RequestParam("action") String action,
                                 @RequestParam("name") String name,
                                 @RequestParam("lastName") String lastName){
        if ("delete".equals(action)) {
            userService.getUserByNameAndLastName(name,lastName);
            return "redirect:/delete";
        }else {
            userService.getUserByNameAndLastName(name,lastName);
            return "redirect:/change";
        }
    }

    @GetMapping(value = "/change")
    public String showChangeUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "change";
    }

    @PostMapping(value = "/change")
    public String changeUser(@ModelAttribute("user") User user) {
        List<String> messages = new ArrayList<>();
        messages.add("Обновлен в БД!");
        messages.add("Выберите дальнейшее действие");
        userService.setMessages(messages);
        userService.updateUser(user, userService.getCurrentUser().getId());
        return "redirect:/hi";
    }

    @GetMapping(value = "/delete")
    public String showDeleteUser(ModelMap model) {
        model.addAttribute("user", userService.getCurrentUser());
        return "delete";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("action") String action){
        if ("yes".equals(action)) {
            List<String> messages = new ArrayList<>();
            messages.add("Удален из БД!");
            messages.add("Выберите дальнейшее действие");
            userService.setMessages(messages);
            userService.deleteUser(userService.getCurrentUser());
            return "redirect:/hi";
        } else {
            List<String> messages = new ArrayList<>();
            messages.add("Не был удален из БД!");
            messages.add("Возможно правильно=)");
            messages.add("Выберите дальнейшее действие");
            userService.setMessages(messages);
            return "redirect:/hi";
        }
    }
}
