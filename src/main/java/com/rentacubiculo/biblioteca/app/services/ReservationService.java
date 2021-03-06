/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.services;

import com.rentacubiculo.biblioteca.app.entities.Reservation;
import com.rentacubiculo.biblioteca.app.reports.CountClients;
import com.rentacubiculo.biblioteca.app.repositories.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwinForero
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    //Consultar Todos los registros.
    public List<Reservation> getAll() {
        return repository.getAll();
    }

    public Optional<Reservation> getReservation(int reservationId) {
        return repository.getReservation(reservationId);
    }
    
    public List<Reservation> getReservationsPeriod(String dataA, String dataB){
           SimpleDateFormat parser = new SimpleDateFormat ("yyyy-MM-dd");
           Date a = new Date();
           Date b = new Date();
           try {
               a = parser.parse(dataA);
               b = parser.parse(dataB);
           }
           catch (ParseException e){
               e.printStackTrace();
           }
           if (a.before(b)){
               return repository.getReservationsPeriod(a,b);
           }
           else{
               return new ArrayList<>();
           }
    }
    public LinkedHashMap<String,Integer> longitudCompletedCancelled()
    {
         LinkedHashMap<String, Integer> resultado = new LinkedHashMap<String, Integer>();
         int completed = repository.getReservationsByStatus("completed").size();
         resultado.put("completed", completed);
         int cancelled = repository.getReservationsByStatus("cancelled").size();
         resultado.put("cancelled", cancelled);
         return resultado;
    }
    //Registrar 
    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {
            return repository.save(reservation);
        } else {
            Optional<Reservation> resultado = repository.getReservation(reservation.getIdReservation());
            if (resultado.isPresent()) {
                return reservation;
            } else {
                return repository.save(reservation);
            }
        }
    }

    //Actualizar
    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> resultado = repository.getReservation(reservation.getIdReservation());
            if (resultado.isPresent()) {
                if (reservation.getStartDate() != null) {
                    resultado.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    resultado.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    resultado.get().setStatus(reservation.getStatus());
                }
                if (reservation.getLib() != null) {
                    resultado.get().setLib(reservation.getLib());
                }
                if (reservation.getClient() != null) {
                    resultado.get().setClient(reservation.getClient());
                }
                if (reservation.getScore() != null) {
                    resultado.get().setScore(reservation.getScore());
                }

                repository.save(resultado.get());
                return reservation;
            } else {
                return reservation;
            }

        } else {
            return reservation;
        }

    }

    public boolean delete(int id) {
        Boolean abool = getReservation(id).map(reservation -> {
            repository.delete(reservation);
            return true;
        }).orElse(false);
        return abool;
    }
    public List<CountClients> getTopClients(){
       return repository.getTopClient();
    }
    
}
