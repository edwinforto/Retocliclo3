/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.services;

import com.rentacubiculo.biblioteca.app.entities.Library;
import com.rentacubiculo.biblioteca.app.repositories.LibraryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fdomoreno
 */
@Service
public class LibraryService {
    
    @Autowired
    private LibraryRepository repository;

    //Consultar Todos los registros.
    public List<Library> getAll() {
        return repository.getAll();
    }

    public Optional<Library> getLibrary(int libraryId) {
        return repository.getLibrary(libraryId);
    }

    //Registrar 
    public Library save(Library library) {
        if (library.getId()== null) {
            return repository.save(library);
        } else {
            Optional<Library> resultado = repository.getLibrary(library.getId());
            if (resultado.isPresent()) {
                return library;
            } else {
                return repository.save(library);
            }
        }
    }

    //Actualizar
    public Library update(Library library) {
        if (library.getId()!= null) {
            Optional<Library> resultado = repository.getLibrary(library.getId());
            if (resultado.isPresent()) {
                if (library.getTarget()!= null) {
                    resultado.get().setTarget(library.getTarget());
                }
                if (library.getCapacity()!= null) {
                    resultado.get().setCapacity(library.getCapacity());
                }
                if (library.getCategory()!= null) {
                    resultado.get().setCategory(library.getCategory());
                }
                if (library.getDescription()!= null) {
                    resultado.get().setDescription(library.getDescription());
                }
                if (library.getName()!= null) {
                    resultado.get().setName(library.getName());
                }
                if (library.getMessages()!= null) {
                    resultado.get().setMessages(library.getMessages());
                }
                if (library.getReservations()!= null) {
                    resultado.get().setReservations(library.getReservations());
                }
                repository.save(resultado.get());
                return library;
            } else {
                return library;
            }

        } else {
            return library;
        }

}
    public boolean delete(int id) {
        Boolean abool = getLibrary(id).map(library -> {
            repository.delete(library);
            return true;
        }).orElse(false);
        return abool;
    }
}
