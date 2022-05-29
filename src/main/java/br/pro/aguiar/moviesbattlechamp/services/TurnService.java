package br.pro.aguiar.moviesbattlechamp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pro.aguiar.moviesbattlechamp.repositories.TurnRepository;
import br.pro.aguiar.moviesbattlechamp.models.Turn;
import br.pro.aguiar.moviesbattlechamp.models.User;

@Service
public class TurnService {
    
    @Autowired
    private TurnRepository turnRepository;

    public List<Turn> findAll() {
        return turnRepository.findAll();
    }

    public Turn findById(long id) {
        return turnRepository.findById(id).get();
    }

    public List<Turn> findByUser(User user) {
        return turnRepository.findByUser(user).get();
    }

    public Turn save(Turn turn) {
        return turnRepository.save(turn);
    }

}
