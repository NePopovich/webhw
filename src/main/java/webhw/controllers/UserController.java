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

    @PostMapping("/")
    public String saveUser(@ModelAttribute("user") User user) {
        List<String> messages = new ArrayList<>();
        messages.add("Здравствуйте!");
        messages.add("Добро пожаловать!");
        messages.add("Выберите дальнейшее действие");
        StartController.setMessages(messages);
        userService.save(user);
        return "redirect:/info";
    }

    @GetMapping("/users")
    public String showAllUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/motion")
    public String showChangeUser() {
        return "motion";
    }

    @PostMapping("/motion")
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

    @GetMapping("/change")
    public String showChangeUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "change";
    }

    @PostMapping("/change")
    public String changeUser(@ModelAttribute("user") User user) {
        List<String> messages = new ArrayList<>();
        messages.add("Обновлен в БД!");
        messages.add("Выберите дальнейшее действие");
        StartController.setMessages(messages);
        userService.updateUser(user, StartController.getCurrentUser().getId());
        return "redirect:/info";
    }

    @GetMapping("/delete")
    public String showDeleteUser(ModelMap model) {
        model.addAttribute("user", StartController.getCurrentUser());
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("action") String action){
        if ("yes".equals(action)) {
            List<String> messages = new ArrayList<>();
            messages.add("Удален из БД!");
            messages.add("Выберите дальнейшее действие");
            StartController.setMessages(messages);
            userService.deleteUser(StartController.getCurrentUser());
            return "redirect:/info";
        } else {
            List<String> messages = new ArrayList<>();
            messages.add("Не был удален из БД!");
            messages.add("Возможно правильно=)");
            messages.add("Выберите дальнейшее действие");
            StartController.setMessages(messages);
            return "redirect:/info";
        }
    }
}
