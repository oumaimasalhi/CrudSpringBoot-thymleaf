package com.Crud.Project.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.Crud.Project.Entities.Fournisseur;
import com.Crud.Project.Entities.Produit;
import com.Crud.Project.Repositories.FournisseurRepositorie ;
import com.Crud.Project.Repositories.ProduitRepositorie;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {

	@Autowired
    private FournisseurRepositorie fournisseurRepositorie ;
	
	@Autowired
	private ProduitRepositorie  ProduitRepositorie;

	 
	 @GetMapping("/list")
	 public String listfournisseur(Model model) {
	     List<Fournisseur> fournisseurs = fournisseurRepositorie.findAll();
	     model.addAttribute("fournisseurs", fournisseurs);
	     return "Fournisseur/FournisseurAffiche";
	 }
	 
	 

	 
	    //ajouter
	  	@GetMapping("/add")
	  	public String add(Model model) {
	  		List<Produit> produits = ProduitRepositorie.findAll();
	  		model.addAttribute("produits", produits);
	  	    model.addAttribute("fournisseur", new Fournisseur());
	  	    return "Fournisseur/FournisseurAjout";
	  	}


	  	@PostMapping("/add")
	  	public String ajouter(@Valid @ModelAttribute("fournisseur") Fournisseur fournisseur, BindingResult result, Model model) {
	  	    if (result.hasErrors()) {
	  	        List<Produit> produits = ProduitRepositorie.findAll();
	  	        model.addAttribute("produits", produits);
	  	        return "Fournisseur/FournisseurAjout";
	  	    }
	  	  
	  	    List<Produit> produitsSelectionnes = fournisseur.getProduits();
	  	    for (Produit produit : produitsSelectionnes) {
	          produit.setFournisseur(fournisseur);
	        }
	        
	  	    fournisseurRepositorie.save(fournisseur);
	  	    
	  	    return "redirect:list";
	  	}
	  	


	      //supprimer 
	  	 @GetMapping("delete/{id}")
	  	public String supprimer(@PathVariable ("id")long id)
	  	{
	  		fournisseurRepositorie.deleteById(id);
	  		return "redirect:../list ";
	  	}
	  	
	  
	  	 
	    // modifier 
	  	 @GetMapping("/update/{id}")
	     public String edit(@PathVariable("id") long id, Model model) {
	         Optional<Fournisseur> fournisseur = fournisseurRepositorie.findById(id);
	         List<Produit> produits = ProduitRepositorie.findAll();

	         if (fournisseur.isPresent()) {
	             model.addAttribute("f", fournisseur.get());
	             model.addAttribute("produits", produits);
	             return "Fournisseur/FournisseurModif";
	         } else {
	             
	             return "redirect:/fournisseur/list";
	         }
	     }

	 
	  	 @PostMapping("/update")
	      public String modifier(@Valid @ModelAttribute("f") Fournisseur fournisseur, BindingResult result, Model model) {
	          if (result.hasErrors()) {
	              List<Produit> produits = ProduitRepositorie.findAll();
	              model.addAttribute("produits", produits);
	              return "Fournisseur/FournisseurModif";
	          }
	          
	          List<Produit> produitsSelectionnes = fournisseur.getProduits();
		  	    for (Produit produit : produitsSelectionnes) {
		          produit.setFournisseur(fournisseur);
		        }
	         
	          fournisseurRepositorie.save(fournisseur);

	          return "redirect:/fournisseur/list";
	      }
	  	 
	


	
	  	  
	  

	 	
}
