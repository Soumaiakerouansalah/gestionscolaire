package com.example.gestionscolaire.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gestionscolaire.entities.Cours;
import com.example.gestionscolaire.entities.Eleve;
import com.example.gestionscolaire.repositories.CoursRepository;
import com.example.gestionscolaire.repositories.FiliereRepository;
import com.example.gestionscolaire.services.CoursService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @Autowired
    private FiliereRepository filiereRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cours", coursService.findAll());
        return "cours/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("filieres", filiereRepo.findAll());
        return "cours/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Cours cours, Model model) {

        boolean codeExiste = coursService.existsByCode(cours.getCode());

        if (codeExiste && cours.getId() == null) {
            model.addAttribute("error", "❌ Ce code de cours existe déjà");
            model.addAttribute("cours", cours);
            model.addAttribute("filieres", filiereRepo.findAll());
            return "cours/form";
        }

        coursService.save(cours);
        return "redirect:/cours";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("cours", coursService.findById(id));
        model.addAttribute("filieres", filiereRepo.findAll());
        return "cours/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        coursService.deleteById(id);
        return "redirect:/cours";
    }

    @GetMapping("/by-filiere/{id}")
    @ResponseBody
    public List<Cours> getCoursByFiliere(@PathVariable Long id) {
        return coursService.findByFiliereId(id);
    }
}
