package com.gable.glending.controller;


import com.gable.glending.model.Item;
import com.gable.glending.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    @Autowired
    private ItemRepository repository;

    @GetMapping("/item")
    public String getItemPage(Model model) {
        model.addAttribute("items", repository.findAll());
        return "item";  // return item.html
    }

    @GetMapping("/item/add")
    public String getAddPage() {
        return "item-add";  // return item-add.html
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute Item item,
                                Model model) {
        repository.save(item);
        return "redirect:/item";
    }

}
