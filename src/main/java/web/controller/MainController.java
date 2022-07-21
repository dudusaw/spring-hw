package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String home(ModelMap model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
