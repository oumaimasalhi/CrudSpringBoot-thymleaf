package com.Crud.Project.Controllers;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.Crud.Project.Entities.Categorie;
import com.Crud.Project.Entities.Produit;
import com.Crud.Project.Repositories.CategorieRepositorie;
import com.Crud.Project.Repositories.ProduitRepositorie;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategorieController {
	@Autowired
    private CategorieRepositorie categorieRepository;

    @Autowired
    private ProduitRepositorie produitRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<Categorie> categories = categorieRepository.findAll();
        model.addAttribute("categories", categories);
        return "Categorie/CategorieAffiche";
    }


    //Ajouter
    
    @GetMapping("/add")
    public String addCategory(Model model) {
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits", produits);
        model.addAttribute("categorie", new Categorie());
        return "Categorie/CategorieAjout";
    }


       @PostMapping("/add")
       public String ajouter(@Valid @ModelAttribute("categorie") Categorie categorie, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Produit> produits = produitRepository.findAll();
            model.addAttribute("produits", produits);
            return "Categorie/CategorieAjout";
        }
        List<Produit> produitsSelectionnes = categorie.getProduits();
        for (Produit produit : produitsSelectionnes) {
        	produit.setCategorie(categorie);
        }
        categorieRepository.save(categorie);

        return "redirect:list";
    }


      
      //supprimer 
  	 @GetMapping("delete/{id}")
  	public String supprimer(@PathVariable ("id")long id)
  	{
  		categorieRepository.deleteById(id);
  		return "redirect:../list ";
  	}
  	
  	
  	 
    // modifier 
 	@GetMapping("update/{id}")
 	public String modifier(Model model ,@PathVariable ("id")long id)
 	{
 		Optional<Categorie> Categorie =categorieRepository.findById(id);
 		List<Produit> produits = produitRepository.findAll();
 		if (Categorie.isPresent()) {
            model.addAttribute("c", Categorie.get());
            model.addAttribute("produits", produits);
            return "Categorie/CategorieModif";
        } else {
 		
 		
 		return "redirect:/category/list";
 	}
 	
 	}

 	
 	@PostMapping("/update")
 	public String missAJour(@Valid @ModelAttribute("c") Categorie categorie, BindingResult result, Model model) {
 	    if (result.hasErrors()) {
 	        List<Produit> produits = produitRepository.findAll();
 	        model.addAttribute("produits", produits);
 	        return "Categorie/CategorieModif";
 	    }

 	    List<Produit> produitsSelectionnes = categorie.getProduits();
 	    for (Produit produit : produitsSelectionnes) {
 	        produit.setCategorie(categorie);
 	        
 	    }

 	     categorieRepository.save(categorie);

 	    return "redirect:/category/list";
 	}

 	
 	

 	 
}
