package com.gable.glending.controller;

import com.gable.glending.dto.SignupDto;
import com.gable.glending.model.Member;
import com.gable.glending.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String getSignupPage(SignupDto user) {
        return "signup"; // return signup.html
    }

    @PostMapping("/signup")
    public String signupMember(@Valid SignupDto member, BindingResult result, Model model) {
        if (result.hasErrors())
            return "signup";

        String signupError = null;

        if (!signupService.isUsernameAvailable(member.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            signupService.createMember(member);
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        model.addAttribute("signupDto", new SignupDto());
        return "signup";
    }
}
