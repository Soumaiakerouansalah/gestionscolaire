package com.example.gestionscolaire.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.gestionscolaire.entities.Eleve;
import com.example.gestionscolaire.entities.Filiere;
import com.example.gestionscolaire.repositories.CoursRepository;
import com.example.gestionscolaire.repositories.EleveRepository;
import com.example.gestionscolaire.repositories.FiliereRepository;
import com.example.gestionscolaire.services.EleveService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/filieres")
public class FiliereController {

    @Autowired
    private FiliereRepository filiereRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("filieres", filiereRepo.findAll());
        return "filieres/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("filiere", new Filiere());
        return "filieres/form";
    }

 @PostMapping("/save")
public String save(
        Filiere filiere,
        Model model
) {
    try {
        filiereRepo.save(filiere);
        return "redirect:/filieres";
    } catch (DataIntegrityViolationException e) {

        // message d’erreur
        model.addAttribute("error",
                "❌ Code filière déjà existant");

        // renvoyer vers le formulaire
        model.addAttribute("filiere", filiere);
        return "filieres/form";
    }
}

    @GetMapping("/edit/{id}")
public String edit(@PathVariable Long id, Model model) {
    model.addAttribute("filiere", filiereRepo.findById(id).orElseThrow());
    return "filieres/form";
}

@GetMapping("/delete/{id}")
public String delete(@PathVariable Long id) {
    filiereRepo.deleteById(id);
    return "redirect:/filieres";
}

@GetMapping("/details/{id}")
public String details(@PathVariable Long id, Model model) {
    model.addAttribute("filiere",
            filiereRepo.findById(id).orElseThrow());
    return "filieres/details";
}

}
