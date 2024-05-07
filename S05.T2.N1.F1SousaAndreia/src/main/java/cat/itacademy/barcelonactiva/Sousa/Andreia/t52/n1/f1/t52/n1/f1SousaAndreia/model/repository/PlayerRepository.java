package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.repository;

import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByPlayerName(String name);

}

