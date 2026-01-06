package com.example.gestionscolaire.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestionscolaire.entities.Filiere;

import com.example.gestionscolaire.services.FiliereService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("filieres", filiereService.findAll());
        return "filieres/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("filiere", new Filiere());
        return "filieres/form";
    }

    @PostMapping("/save")
    public String save(Filiere filiere, Model model) {
        try {
            filiereService.save(filiere);
            return "redirect:/filieres";
        } catch (DataIntegrityViolationException e) {

            model.addAttribute("error",
                    "❌ Code filière déjà existant");
            model.addAttribute("filiere", filiere);
            return "filieres/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("filiere", filiereService.findById(id));
        return "filieres/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        filiereService.deleteById(id);
        return "redirect:/filieres";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("filiere", filiereService.findById(id));
        return "filieres/details";
    }
}
