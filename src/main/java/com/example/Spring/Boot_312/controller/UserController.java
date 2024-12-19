package com.example.Spring.Boot_312.controller;

import com.example.Spring.Boot_312.model.User;
import com.example.Spring.Boot_312.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/user")
    public String CreateUserForm(@ModelAttribute("user") User user) {
        return "adduser";
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        userService.save(user);
        return "redirect:/";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/userUpdate")
    public String getEditUserForm(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "updateuser";
    }

    @PostMapping("/userUpdate")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userService.update(user);
        return "redirect:/";
    }
}
