package com.example.tourists;

import com.example.tourists.model.Tourist;
import com.example.tourists.repository.TouristRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TouristController {

    private final TouristRepository repo;

    public TouristController(TouristRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String Home(){
        return "home";
    }

    @GetMapping("/tourists")
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
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Tourist tourist = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tourist Id:" + id));
        model.addAttribute("tourist", tourist);
        return "update"; // create update.html
    }
    @PostMapping("/update/{id}")
    public String updateTourist(@PathVariable("id") int id, @ModelAttribute Tourist tourist) {
        tourist.setId(id);
        repo.save(tourist);
        return "redirect:/tourists"; // back to list after update
    }
    @GetMapping("/delete/{id}")
    public String deleteTourist(@PathVariable("id") int id) {
        repo.deleteById(id);
        return "redirect:/tourists"; // back to list after delete
    }



}
