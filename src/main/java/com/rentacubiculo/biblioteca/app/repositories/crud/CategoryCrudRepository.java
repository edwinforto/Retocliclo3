/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.repositories.crud;

import com.rentacubiculo.biblioteca.app.entities.Category;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author EdwinForero
 */
public interface CategoryCrudRepository extends CrudRepository<Category,Integer> {
    
}
