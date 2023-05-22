package com.gable.glending.controller;


import com.gable.glending.dto.ItemDto;
import com.gable.glending.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item")
    public String getItemPage(Model model) {
        model.addAttribute("items", itemService.getItems());
        return "item";  // return item.html
    }

    @GetMapping("/item/add")
    public String getAddPage() {
        return "item-add";  // return item-add.html
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute ItemDto item,
                                Model model) {
        itemService.create(item);
        return "redirect:/item";
    }

}
