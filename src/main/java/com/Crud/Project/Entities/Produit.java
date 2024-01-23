package com.Crud.Project.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "t_produits")
public class Produit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Column(length = 45, nullable = false, unique = true)
    private String nom;

    
    @NotNull(message = "Le prix ne peut pas être nul")
    @Column(name = "prix")
    private Double prix;

    //@NotEmpty(message = "Au moins une categorie doit être associée")
    @ManyToOne
    @JoinColumn(name = "categorie_id") // Specify the appropriate column name
    private Categorie categorie;
    
 
    //@NotEmpty(message = "Au moins un fournisseur doit être associé")
    @ManyToOne
    @JoinColumn(name = "fournisseur_id") // Specify the appropriate column name
    private Fournisseur fournisseur;
    
   


	public Produit(@NotBlank(message = "Le nom ne peut pas être vide") String nom,
			@NotNull(message = "Le prix ne peut pas être nul") Double prix,
			@NotNull(message = "Au moins une categorie doit être associée") @Valid Categorie categorie,
			@NotNull(message = "Au moins un fournisseur doit être associée") Fournisseur fournisseur) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.fournisseur = fournisseur;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Double getPrix() {
		return prix;
	}


	public void setPrix(Double prix) {
		this.prix = prix;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public Fournisseur getFournisseur() {
		return fournisseur;
	}


	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}



	public Produit() {
		super();
	}

    

    
   
}