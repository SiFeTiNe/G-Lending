package com.gable.glending.controller;

import com.gable.glending.service.BorrowReturnHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class BorrowReturnHistoryController {

    @Autowired
    private BorrowReturnHistoryService historyService;

    @GetMapping("/history/all")
    public String getAllHistoryPage(Model model) {
        model.addAttribute("histories", historyService.getHistories());
        return "history-all";  // return history-all.html
    }

    @GetMapping("/history")
    public String getHistoryPage(Authentication auth, Model model) {
        model.addAttribute("histories", historyService.getHistory(auth));
        return "history";  // return history-all.html
    }
}
