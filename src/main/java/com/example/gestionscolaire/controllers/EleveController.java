package com.example.gestionscolaire.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestionscolaire.entities.Cours;
import com.example.gestionscolaire.entities.Eleve;
import com.example.gestionscolaire.repositories.CoursRepository;
import com.example.gestionscolaire.repositories.EleveRepository;
import com.example.gestionscolaire.repositories.FiliereRepository;
import com.example.gestionscolaire.services.EleveService;
import org.springframework.ui.Model;



@Controller
@RequestMapping("/eleves")
public class EleveController {

    @Autowired private EleveRepository eleveRepo;
    @Autowired private FiliereRepository filiereRepo;
    @Autowired private CoursRepository coursRepo;
    @Autowired private EleveService eleveService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("eleves", eleveRepo.findAll());
        return "eleves/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("eleve", new Eleve());
        model.addAttribute("filieres", filiereRepo.findAll());
        model.addAttribute("cours", coursRepo.findAll());
        return "eleves/form";
    }



@PostMapping("/save")
public String save(
        @ModelAttribute Eleve eleve,
        @RequestParam(required = false) List<Long> coursIds
) {
    eleveService.saveEleve(eleve, coursIds);
    return "redirect:/eleves";
}





    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("eleve", eleveRepo.findById(id).get());
        return "eleves/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        eleveRepo.deleteById(id);
        return "redirect:/eleves";
    }
    @GetMapping("/edit/{id}")
public String edit(@PathVariable Long id, Model model) {
    Eleve eleve = eleveRepo.findById(id).orElseThrow();
    model.addAttribute("eleve", eleve);
    return "eleves/form-edit";
}

@PostMapping("/update")
public String update(@ModelAttribute Eleve eleve) {

    // 🔒 récupérer l'élève existant
    Eleve existing = eleveRepo.findById(eleve.getId())
            .orElseThrow();

    // ✅ modifier uniquement nom et prénom
    existing.setNom(eleve.getNom());
    existing.setPrenom(eleve.getPrenom());

    // ❌ on ne touche PAS filière / cours / dossier
    eleveRepo.save(existing);

    return "redirect:/eleves";
}

}
