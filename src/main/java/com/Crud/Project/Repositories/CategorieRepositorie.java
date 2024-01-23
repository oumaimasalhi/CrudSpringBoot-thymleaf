package com.Crud.Project.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Crud.Project.Entities.Categorie;

@Repository
public interface CategorieRepositorie extends JpaRepository<Categorie , Long>{

}
