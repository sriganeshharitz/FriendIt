package com.uttara.project.FriendIt.Controllers;

import com.uttara.project.FriendIt.Model.*;
import com.uttara.project.FriendIt.Services.PasswordValidationService;
import com.uttara.project.FriendIt.Services.TokenService;
import com.uttara.project.FriendIt.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
public class HomeController {
    @Autowired
    private PasswordValidationService passwordValidationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @RequestMapping({"/",""})
    public String showHome() {
        System.out.println("In showHOme()");
        return "Home";
    }

    @RequestMapping("/registration")
    public String showRegister(Model model){
        System.out.println("In showRegister()");
        User user = new User();
        PersonalInfo personalInfo = new PersonalInfo();
        ProfileInfo profileInfo = new ProfileInfo();
        model.addAttribute("user",user);
        model.addAttribute("personalInfo",personalInfo);
        model.addAttribute("profileInfo",profileInfo);
        return "Register";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user, BindingResult result, Model model, @ModelAttribute("personalInfo") @Valid PersonalInfo personalInfo, BindingResult result2,@ModelAttribute("profileInfo") @Valid ProfileInfo profileInfo,BindingResult result3,@RequestParam("pic") MultipartFile multipartFile) {
        System.out.println("Inside register");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getRepeatedPassword());
        System.out.println(personalInfo);
        System.out.println(profileInfo);
        if(result.hasErrors()||result2.hasErrors()||result3.hasErrors()){
            System.out.println("Results have error");
            return "Register";
        }
        else if(passwordValidationService.verifyPassword(user).equals(Status.FAILURE)) {
            model.addAttribute("passwordError","Passwords don't match");
            return "Register";
        }
        else{
            String str = userService.checkUserExists(user.getEmail());
            if(!str.equals(Constants.SUCCESS)){
                model.addAttribute("error",str);
                return "Failure";
            }
            String status = null;
            try {
                status = tokenService.createTokenAndSendEmail(user,personalInfo,profileInfo,multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error","Error while uploading picture");
                return "Failure";
            }
            if(status.equals(Constants.SUCCESS)) {
                return "Success";
            }
            else {
                model.addAttribute("error",status);
                return "Failure";
            }
        }

    }
    @RequestMapping("/registrationConfirmation")
    public String confirmRegistration(@RequestParam(name = "token") String token,Model model) {
        String status = tokenService.verifyTokenAndRegister(token);
        if(status.equals(Constants.SUCCESS)) {
            return "Success";
        }
        else {
            model.addAttribute("error",status);
            return "Failure";
        }
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("In login controller");
        return "Login";
    }

    @RequestMapping("/secret")
    public String secret(){
        return "Success";
    }

    @RequestMapping("/showProfile")
    public String showProfile(Principal principal){

        return "Profile";
    }
}
