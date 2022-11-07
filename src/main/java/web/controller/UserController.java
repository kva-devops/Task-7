package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printIndex(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/create")
    public String loadPageForCreateUser(Model model) {
        model.addAttribute("user", new User());
        return "create";
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(name = "id") String id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String loadPageForUpdateUser(@RequestParam(name = "id") String id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "update";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam(name = "id") String id, @ModelAttribute User user) {
        userService.updateUser(id, user);
        return "redirect:/";
    }

}