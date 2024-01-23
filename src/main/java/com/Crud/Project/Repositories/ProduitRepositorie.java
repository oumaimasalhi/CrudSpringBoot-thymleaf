package com.Crud.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Crud.Project.Entities.Produit;


@Repository
public interface ProduitRepositorie extends JpaRepository<Produit, Long> {
	  boolean existsByNom(String nom);
}
