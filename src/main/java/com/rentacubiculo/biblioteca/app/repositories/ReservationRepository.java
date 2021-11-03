/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.repositories;

import com.rentacubiculo.biblioteca.app.entities.Client;
import com.rentacubiculo.biblioteca.app.entities.Reservation;
import com.rentacubiculo.biblioteca.app.reports.CountClients;
import com.rentacubiculo.biblioteca.app.repositories.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EdwinForero
 */
@Repository
public class ReservationRepository {
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    public Optional<Reservation> getReservation(int id){
        return reservationCrudRepository.findById(id);
    }
    public List<Reservation> getReservationsPeriod(Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a,b);
}
    public List<Reservation> getReservationsByStatus(String status){
        return reservationCrudRepository.findByStatus(status);
    }
    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }
    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }

     public List<CountClients> getTopClient(){
        List<CountClients> clientList = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();
        for(int i=0;i<report.size();i++){
            clientList.add(new CountClients((Long) report.get(i)[1] ,(Client) report.get(i)[0]));
            }
        return clientList;
    }
    
}
