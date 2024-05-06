package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.services.Interfaz;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.DTO.GameDTO;

import java.util.List;

public interface GameServices {

    List<GameDTO> getAllGames();
    List<GameDTO> listGamesByPlayer(long playerId);

    GameDTO getGameById(Long id);

    GameDTO createGame(long playerId);

    GameDTO updateGame(long id, GameDTO gameDTO);

    String deleteSpecificGame(long gameId);

}


