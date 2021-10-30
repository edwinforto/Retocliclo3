/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rentacubiculo.biblioteca.app.repositories;

import com.rentacubiculo.biblioteca.app.entities.Score;
import com.rentacubiculo.biblioteca.app.repositories.crud.ScoreCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EdwinForero
 */
@Repository
public class ScoreRepository {
    @Autowired
    private ScoreCrudRepository ScoreCrudRepository;
    
    public List<Score> getAll(){
        return (List<Score>) ScoreCrudRepository.findAll();
    }
    public Optional<Score> getScore(int id){
        return ScoreCrudRepository.findById(id);
    }
    public Score save(Score score){
        return ScoreCrudRepository.save(score);
    }
    public void delete(Score score){
        ScoreCrudRepository.delete(score);
    }


    
    
}
