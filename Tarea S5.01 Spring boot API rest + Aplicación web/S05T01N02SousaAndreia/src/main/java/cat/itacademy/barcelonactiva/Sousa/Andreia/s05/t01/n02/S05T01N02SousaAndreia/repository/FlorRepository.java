package cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.repository;

import cat.itacademy.barcelonactiva.Sousa.Andreia.s05.t01.n02.S05T01N02SousaAndreia.domain.FlorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlorRepository extends JpaRepository<FlorEntity, Integer> {
}
