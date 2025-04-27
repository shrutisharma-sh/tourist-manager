package com.example.tourists;

import com.example.tourists.model.Tourist;
import com.example.tourists.repository.TouristRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TouristController {

    private final TouristRepository repo;

    public TouristController(TouristRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String listTourists(Model model) {
        model.addAttribute("tourists", repo.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("tourist", new Tourist());
        return "add";
    }
    @PostMapping("/save")
    public String saveTourist(@ModelAttribute Tourist tourist) {
        repo.save(tourist);
        return "redirect:/";  // after saving, go back to homepage
    }




}
