package webhw.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StartControllers {

    @GetMapping(value = "/")
    public String startPage(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Now");
        messages.add("All is GOOD!");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping("/user")
    public String userPage(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("Now");
        messages.add("All is GOOD!");
        model.addAttribute("messages", messages);
        return "user.html";
    }
}
