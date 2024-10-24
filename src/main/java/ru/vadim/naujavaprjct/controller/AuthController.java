package ru.vadim.naujavaprjct.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vadim.naujavaprjct.service.UserService;

@Controller
public class AuthController
{
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          Model model)
    {
        try {
            userService.addUser(username, password);
            return "redirect:/login";
        } catch (Exception ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }


    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}