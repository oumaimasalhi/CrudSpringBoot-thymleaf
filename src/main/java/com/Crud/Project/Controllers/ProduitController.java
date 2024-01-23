package com.Crud.Project.Controllers;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Crud.Project.Entities.Categorie;
import com.Crud.Project.Entities.Fournisseur;
import com.Crud.Project.Entities.Produit;
import com.Crud.Project.Repositories.CategorieRepositorie;
import com.Crud.Project.Repositories.FournisseurRepositorie;
import com.Crud.Project.Repositories.ProduitRepositorie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/produit")
public class ProduitController {
	

	@Autowired
	ProduitRepositorie produitRepository;

    @Autowired
    CategorieRepositorie categorieRepository;
    
    @Autowired
    FournisseurRepositorie fournisseurRepositorie ;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private static final Logger logger = LoggerFactory.getLogger(ProduitController.class);
    
    @GetMapping("/list")
    public String list(Model model) {
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits", produits);
        return "Produit/ProduitAffiche";
    }
    

    //ajout
    @GetMapping("/add")
    public String add(Model model) {
        List<Categorie> categories = categorieRepository.findAll();
        model.addAttribute("categories", categories);
        List<Fournisseur> fournisseurs = fournisseurRepositorie.findAll();
	     model.addAttribute("fournisseurs", fournisseurs);
        model.addAttribute("produit", new Produit()); 

        return "Produit/ProduitAjout";
    }
    
    
    @Transactional
    @PostMapping("/add")
    public String addProduit(@Valid @ModelAttribute("produit") Produit produit,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categorieRepository.findAll());
            model.addAttribute("fournisseurs", fournisseurRepositorie.findAll());
            return "Produit/ProduitAjout";
        }

        try {
            produitRepository.save(produit);
            logger.info("New product added: {}", produit);
            return "redirect:/produit/list";
        } catch (Exception e) {
            logger.error("Error adding product", e);
            model.addAttribute("categories", categorieRepository.findAll());
            model.addAttribute("fournisseurs", fournisseurRepositorie.findAll());
            return "Produit/ProduitAjout";
        }
    }


 

    // supprimer 
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
  
        produitRepository.deleteById(id);

        return "redirect:/produit/list";
   
        }
    
    
    
    //Modifier 
    @GetMapping("update/{id}")
    public String Modification(@PathVariable("id") long id, Model model) {
    	 
         Optional<Produit> produit = produitRepository.findById(id);
    	 List<Categorie> categories = categorieRepository.findAll();
    	 model.addAttribute("categories", categories);
    	 List<Fournisseur> fournisseurs = fournisseurRepositorie.findAll();
    	 model.addAttribute("fournisseurs", fournisseurs);
         
        
        
        if (produit.isPresent()) {
            model.addAttribute("produit", produit.get());
            model.addAttribute("categories", categories);
            model.addAttribute("fournisseurs", fournisseurs);
            return "Produit/ProduitModif";
        } else {
           
        	 return "redirect:/produit/list";
        }
    }

    @PostMapping("/update")
    @Transactional
    public String modifierProduit(@Valid @ModelAttribute("produit") Produit produit, BindingResult result, Model model) {

        Produit existingProduit = produitRepository.findById(produit.getId()).orElse(null);

        if (existingProduit != null) {
            existingProduit.setNom(produit.getNom());
            existingProduit.setPrix(produit.getPrix());
            existingProduit.setCategorie(produit.getCategorie());
            existingProduit.setFournisseur(produit.getFournisseur());

            existingProduit = entityManager.merge(existingProduit);

            produitRepository.save(existingProduit);
        }

        return "redirect:/produit/list";
    }




}
