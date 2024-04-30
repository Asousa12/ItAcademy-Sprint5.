package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.GameDTO;

import java.util.List;

public interface GameServices {

    List<GameDTO> getAllGames();

    GameDTO getGameById(String id);

    GameDTO createGame(String playerId);

    GameDTO updateGame(String id, GameDTO gameDTO);

    String deleteSpecificGame(String gameId);
}
