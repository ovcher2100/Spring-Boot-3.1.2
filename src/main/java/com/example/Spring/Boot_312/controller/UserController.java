package com.example.Spring.Boot_312.controller;

import com.example.Spring.Boot_312.model.User;
import com.example.Spring.Boot_312.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



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

    @GetMapping("/adduser")
    public String CreateUserForm(@ModelAttribute("user") User user) {
        return "adduser";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "adduser";
        }
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/updateuser")
    public String getEditUserForm(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "updateuser";
    }

    @PostMapping("/updateuser")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userService.update(user);
        return "redirect:/";
    }
}
