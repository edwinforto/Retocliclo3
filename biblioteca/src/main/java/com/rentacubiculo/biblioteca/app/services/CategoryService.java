/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.services;

import com.rentacubiculo.biblioteca.app.entities.Category;
import com.rentacubiculo.biblioteca.app.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwinForero
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //Consultar Todos los registros.
    public List<Category> getAll() {
        return repository.getAll();
    }

    public Optional<Category> getCategory(int categoryId) {
        return repository.getCategory(categoryId);
    }

    //Registrar 
    public Category save(Category category) {
        if (category.getId() == null) {
            return repository.save(category);
        } else {
            Optional<Category> resultado = repository.getCategory(category.getId());
            if (resultado.isPresent()) {
                return category;
            } else {
                return repository.save(category);
            }
        }
    }

    //Actualizar
    public Category update(Category category) {
        if (category.getId() != null) {
            Optional<Category> resultado = repository.getCategory(category.getId());
            if (resultado.isPresent()) {
                if (category.getName() != null) {
                    resultado.get().setName(category.getName());
                }
                if (category.getDescription() != null) {
                    resultado.get().setDescription(category.getDescription());
                }
                repository.save(resultado.get());
                return category;
            } else {
                return category;
            }

        } else {
            return category;
        }

    }

    public boolean delete(int id) {
        Boolean abool = getCategory(id).map(category -> {
            repository.delete(category);
            return true;
        }).orElse(false);
        return abool;
    }
}
