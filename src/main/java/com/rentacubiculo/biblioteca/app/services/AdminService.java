/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.services;

import com.rentacubiculo.biblioteca.app.entities.Admin;
import com.rentacubiculo.biblioteca.app.repositories.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwinForero
 */
@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;

    //Consultar Todos los registros.
    public List<Admin> getAll() {
        return repository.getAll();
    }

    public Optional<Admin> getAdmin(int adminId) {
        return repository.getAdmin(adminId);
    }

    //Registrar 
    public Admin save(Admin admin) {
        if (admin.getIdAdmin() == null) {
            return repository.save(admin);
        } else {
            Optional<Admin> resultado = repository.getAdmin(admin.getIdAdmin());
            if (resultado.isPresent()) {
                return admin;
            } else {
                return repository.save(admin);
            }
        }
    }

    //Actualizar
    public Admin update(Admin admin) {
        if (admin.getIdAdmin() != null) {
            Optional<Admin> resultado = repository.getAdmin(admin.getIdAdmin());
            if (resultado.isPresent()) {
                if (admin.getName() != null) {
                    resultado.get().setName(admin.getName());
                }
                if (admin.getPassword() != null) {
                    resultado.get().setPassword(admin.getPassword());
                }
                if (admin.getEmail()!= null) {
                    resultado.get().setEmail(admin.getEmail());
                }

                repository.save(resultado.get());
                return admin;
            } else {
                return admin;
            }

        } else {
            return admin;
        }

    }

    public boolean delete(int id) {
        Boolean abool = getAdmin(id).map(admin -> {
            repository.delete(admin);
            return true;
        }).orElse(false);
        return abool;
    }
}
