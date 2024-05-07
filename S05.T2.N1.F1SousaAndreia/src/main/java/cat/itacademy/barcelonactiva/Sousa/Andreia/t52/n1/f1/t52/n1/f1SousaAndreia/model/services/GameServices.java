package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.services;

import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain.Game;

import java.util.List;

public interface GameServices {

    List<GameDTO> getAllGames();
    List<GameDTO> listGamesByPlayer(long playerId);

    GameDTO getGameById(Long id);

    GameDTO createGame(long playerId);

    GameDTO updateGame(long id, GameDTO gameDTO);

    String deleteSpecificGame(long gameId);

}
