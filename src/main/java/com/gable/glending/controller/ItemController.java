package com.gable.glending.controller;

import com.gable.glending.dto.ItemDto;
import com.gable.glending.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public String getItemPage(Authentication auth, Model model) {
        model.addAttribute("items", itemService.getItems(auth));
        return "item";  // return item.html
    }

    @GetMapping("/item/add")
    public String getAddPage() {
        return "item-add";  // return item-add.html
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute ItemDto itemDto,
                                Model model) {
        itemService.create(itemDto);
        return "redirect:/item";
    }

    @PostMapping("/item/{id}/borrow")
    public String borrowItemById(@PathVariable(name = "id") UUID id, Authentication auth,
                             Model model, @RequestHeader String referer) {
        itemService.borrowItem(auth, id);
        // return "redirect:/item";
        return "redirect:"+ referer;
    }

    @PostMapping("/item/{id}/return")
    public String returnItemById(@PathVariable(name = "id") UUID id, Authentication auth,
                             Model model, @RequestHeader String referer) {
        itemService.returnItem(auth, id);
        // return "redirect:/item";
        return "redirect:"+ referer;
    }

    @GetMapping("/item/{id}/borrowers")
    public String getBorrowersPageById(@PathVariable(name = "id") UUID id, Model model) {
        model.addAttribute("borrowers", itemService.getBorrowers(id));
        model.addAttribute("item", itemService.getItemDtoById(id));
        return "borrower";  // return borrower.html
    }

    @GetMapping("/item/{username}/borrowing")
    public String getItemPageByUsername(@PathVariable String username, Authentication auth, Model model) {
        model.addAttribute("items", itemService.getItemsByUsername(auth, username));
        model.addAttribute("username", username);
        return "borrowing";  // return borrowing.html
    }
}
