/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.services;

import com.rentacubiculo.biblioteca.app.entities.Client;
import com.rentacubiculo.biblioteca.app.repositories.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fdomoreno
 */
@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    //Consultar Todos los registros.
    public List<Client> getAll() {
        return repository.getAll();
    }

    public Optional<Client> getClient(int clientId) {
        return repository.getClient(clientId);
    }

    //Registrar 
    public Client save(Client client) {
        if (client.getIdClient() == null) {
            return repository.save(client);
        } else {
            Optional<Client> resultado = repository.getClient(client.getIdClient());
            if (resultado.isPresent()) {
                return client;
            } else {
                return repository.save(client);
            }
        }
    }

    //Actualizar
    public Client update(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> resultado = repository.getClient(client.getIdClient());
            if (resultado.isPresent()) {
                if (client.getName() != null) {
                    resultado.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    resultado.get().setAge(client.getAge());
                }
                if (client.getPassword() != null) {
                    resultado.get().setPassword(client.getPassword());
                }
                if (client.getEmail()!= null) {
                    resultado.get().setEmail(client.getEmail());
                }
                if (client.getMessages()!= null) {
                    resultado.get().setMessages(client.getMessages());
                }
                if (client.getReservations()!= null) {
                    resultado.get().setReservations(client.getReservations());
                }
                repository.save(resultado.get());
                return client;
            } else {
                return client;
            }

        } else {
            return client;
        }

    }

    //Eliminar
    public boolean delete(int id) {
        Boolean abool = getClient(id).map(client -> {
            repository.delete(client);
            return true;
        }).orElse(false);
        return abool;
    }
}
