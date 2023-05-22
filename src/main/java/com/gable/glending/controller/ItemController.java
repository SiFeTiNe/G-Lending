package com.gable.glending.controller;


import com.gable.glending.dto.ItemDto;
import com.gable.glending.model.Item;
import com.gable.glending.model.Member;
import com.gable.glending.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
                             Model model) {
        itemService.borrowItem(auth, id);
        return "redirect:/item";
    }

    @PostMapping("/item/{id}/return")
    public String returnItemById(@PathVariable(name = "id") UUID id, Authentication auth,
                             Model model) {
        itemService.returnItem(auth, id);
        return "redirect:/item";
    }

    @GetMapping("/item/{id}/borrowers")
    public String getBorrowersPageById(@PathVariable(name = "id") UUID id, Model model) {
        model.addAttribute("borrowers", itemService.getBorrowers(id));
        model.addAttribute("item", itemService.getItemDtoById(id));
        return "borrower";  // return item.html
    }
}
