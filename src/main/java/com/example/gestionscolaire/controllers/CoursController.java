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

import org.springframework.ui.Model;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired private CoursRepository coursRepo;
    @Autowired private FiliereRepository filiereRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cours", coursRepo.findAll());
        return "cours/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("filieres", filiereRepo.findAll());
        return "cours/form";
    }

  @PostMapping("/save")
public String save(
        @ModelAttribute Cours cours,
        Model model
) {

    // 🔴 Vérifier si le code existe déjà (cas ajout ou modification)
    boolean codeExiste = coursRepo.existsByCode(cours.getCode());

    if (codeExiste && cours.getId() == null) {
        model.addAttribute("error", "❌ Ce code de cours existe déjà");
        model.addAttribute("cours", cours);
        model.addAttribute("filieres", filiereRepo.findAll());
        return "cours/form"; // ⚠️ rester sur le formulaire
    }

    coursRepo.save(cours);
    return "redirect:/cours";
}


    @GetMapping("/edit/{id}")
public String edit(@PathVariable Long id, Model model) {
    model.addAttribute("cours", coursRepo.findById(id).orElseThrow());
    model.addAttribute("filieres", filiereRepo.findAll());
    return "cours/form";
}

@GetMapping("/delete/{id}")
public String delete(@PathVariable Long id) {

    coursRepo.deleteById(id);

    return "redirect:/cours";
}



    public CoursController(CoursRepository coursRepo) {
        this.coursRepo = coursRepo;
    }

    @GetMapping("/by-filiere/{id}")
    @ResponseBody
    public List<Cours> getCoursByFiliere(@PathVariable Long id) {
        return coursRepo.findByFiliereId(id);
    }
}
