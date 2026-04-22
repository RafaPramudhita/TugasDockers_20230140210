package com.tugas6.deploy.controller;

import com.tugas6.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // Data bersifat temporary (tidak di database) [cite: 74]
    private List<User> userList = new ArrayList<>();

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password) {
        // Memastikan username adalah 'admin' DAN password adalah '20230140210'
        if ("admin".equals(username) && "20230140210".equals(password)) {
            return "redirect:/home";
        }

        // Jika salah satu salah, maka dikembalikan ke halaman login
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("users", userList);
        return "home";
    }

    @GetMapping("/form")
    public String formPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userList.add(user);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        // Hapus baris userList.clear(); agar data tidak terhapus saat logout
        return "redirect:/login";
    }
}