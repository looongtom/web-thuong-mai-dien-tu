package com.example.admin.controller;

import com.example.library.dto.AdminDTO;
import com.example.library.model.Admin;
import com.example.library.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title","Login");
        return "login";
    }

    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("title","Home page");
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title","Register");
        model.addAttribute("adminDTO",new AdminDTO());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title","Forgot password");
        return "forgot-password";
    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDTO") AdminDTO adminDTO,
                              BindingResult result,
                              Model model){
        try{
            if(result.hasErrors()){
                model.addAttribute("adminDTO",adminDTO);
                result.toString();
                return "register";
            }
            Admin admin=adminService.findByUserName(adminDTO.getUsername());
            if(admin !=null){
                model.addAttribute("adminDTO",adminDTO);
                System.out.println("admin not null");
                model.addAttribute("emailError","Your email has been registered");
                return "register";
            }
            if(adminDTO.getPassword().equals(adminDTO.getRepeatPassword())){
                adminDTO.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
                adminService.save(adminDTO);
                System.out.println("success");
                model.addAttribute("adminDTO",adminDTO);
                model.addAttribute("success","Register successfully");
            }else{
                model.addAttribute("adminDTO",adminDTO);
                model.addAttribute("passwordError","Password is not the same!");
                System.out.println("password not same");
                return "register";
            }

        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors","Server is error, please try again later");
        }
        return "register";
    }
}
