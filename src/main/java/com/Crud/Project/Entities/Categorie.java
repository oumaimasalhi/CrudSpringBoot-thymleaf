package com.Crud.Project.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;




@Entity
@Table(name = "t_categories")
public class Categorie {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@NotBlank(message = "Le nom est obligatoire")
    @Column(length = 45, unique = true)
    private String nom;



	@NotEmpty(message = "Au moins un produit doit être associé")
	@OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
	private List<Produit> produits;
	
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


	public List<Produit> getProduits() {
		return produits;
	}


	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}


	public Categorie( String nom, List<Produit> produits) {
		super();
		this.nom = nom;
		this.produits = produits;
	}


	public Categorie() {
		super();
	}
	

	
	
	

	
	 


   
}