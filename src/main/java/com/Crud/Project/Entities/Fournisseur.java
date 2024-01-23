package com.Crud.Project.Entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "t_fournisseur")
public class Fournisseur {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@NotBlank(message = "Le nom est obligatoire")
	@Column(name = "nom", unique = true)
    private String nom;
	
	@NotBlank(message = "L'adresse est obligatoire")
    @Column(name = "adresse")
    private String adresse;
    
	
	@NotNull(message = "Le numéro de téléphone est obligatoire")
    @Positive(message = "Le numéro de téléphone doit être positif")
    private Integer telephone;

	 @NotEmpty(message = "Au moins un produit doit être associé")
	 @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL) // Corrected mappedBy attribute
	  private List<Produit> produits;
	 
	public Fournisseur() {
		super();
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Fournisseur(String nom, String adresse, Integer telephone) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Integer getTelephone() {
		return telephone;
	}

	public void setTelephone(Integer telephone) {
		this.telephone = telephone;
	}
}
