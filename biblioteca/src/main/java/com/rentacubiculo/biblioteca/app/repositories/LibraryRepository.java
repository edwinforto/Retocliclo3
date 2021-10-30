/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.repositories;

import com.rentacubiculo.biblioteca.app.entities.Library;
import com.rentacubiculo.biblioteca.app.repositories.crud.LibraryCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fdomoreno
 */
@Repository
public class LibraryRepository  {
    @Autowired
    private LibraryCrudRepository libraryCrudRepository;
    
    public List<Library> getAll(){
        return (List<Library>) libraryCrudRepository.findAll();
    }
    public Optional<Library> getLibrary(int id){
        return libraryCrudRepository.findById(id);
    }
    public Library save(Library library){
        return libraryCrudRepository.save(library);
    }
    public void delete(Library library){
        libraryCrudRepository.delete(library);
    }
    
    
}


