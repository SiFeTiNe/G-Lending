package com.gable.glending.controller;

import com.gable.glending.service.ItemService;
import com.gable.glending.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public String getItemPage(Model model) {
        model.addAttribute("members", memberService.getMembers());
        return "member";  // return member.html
    }
}
