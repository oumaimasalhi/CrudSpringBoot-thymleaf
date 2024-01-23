package com.Crud.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Crud.Project.Entities.Fournisseur;


@Repository
public interface FournisseurRepositorie extends JpaRepository< Fournisseur, Long> {
 
	 
}
