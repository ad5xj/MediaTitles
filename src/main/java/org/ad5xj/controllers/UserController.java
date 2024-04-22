package org.ad5xj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.ad5xj.model.User;
import org.ad5xj.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController 
{
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String listUsers(Model model) {
        List < User > userlist = userService.getUsers();
        model.addAttribute("users", userlist);
        return "list-users";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        User user = new User();
        theModel.addAttribute("user", user);
        return "register-form";
    }

    @PostMapping("/saveUser")
    public String saveCustomer(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:list-users";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") int userId, Model theModel) {
        User user = userService.getUser(userId);
        theModel.addAttribute("user", user);
        return "register-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int userId) {
        userService.deleteUser(userId);
        return "redirect:list-users";
    }
}