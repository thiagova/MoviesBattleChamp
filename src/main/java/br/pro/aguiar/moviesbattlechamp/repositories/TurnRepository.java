package br.pro.aguiar.moviesbattlechamp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.pro.aguiar.moviesbattlechamp.models.Turn;
import br.pro.aguiar.moviesbattlechamp.models.User;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {
    Optional<List<Turn>> findByUser(User user);
}
