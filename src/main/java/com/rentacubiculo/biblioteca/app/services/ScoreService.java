/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.services;

import com.rentacubiculo.biblioteca.app.entities.Score;
import com.rentacubiculo.biblioteca.app.repositories.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwinForero
 */
@Service
public class ScoreService {

    @Autowired
    private ScoreRepository repository;

    //Consultar Todos los registros.
    public List<Score> getAll() {
        return repository.getAll();
    }

    public Optional<Score> getScore(int scoreId) {
        return repository.getScore(scoreId);
    }

    //Registrar 
    public Score save(Score score) {
        if (score.getIdScore() == null) {
            return repository.save(score);
        } else {
            Optional<Score> resultado = repository.getScore(score.getIdScore());
            if (resultado.isPresent()) {
                return score;
            } else {
                return repository.save(score);
            }
        }
    }

    //Actualizar
    public Score update(Score score) {
        if (score.getIdScore() != null) {
            Optional<Score> resultado = repository.getScore(score.getIdScore());
            if (resultado.isPresent()) {
                if (score.getMessageText() != null) {
                    resultado.get().setMessageText(score.getMessageText());
                }
                if (score.getStars() != null) {
                    resultado.get().setStars(score.getStars());
                }
                if (score.getReservation() != null) {
                    resultado.get().setReservation(score.getReservation());
                }

                repository.save(resultado.get());
                return score;
            } else {
                return score;
            }

        } else {
            return score;
        }

    }

    public boolean delete(int id) {
        Boolean abool = getScore(id).map(score -> {
            repository.delete(score);
            return true;
        }).orElse(false);
        return abool;
    }
}
