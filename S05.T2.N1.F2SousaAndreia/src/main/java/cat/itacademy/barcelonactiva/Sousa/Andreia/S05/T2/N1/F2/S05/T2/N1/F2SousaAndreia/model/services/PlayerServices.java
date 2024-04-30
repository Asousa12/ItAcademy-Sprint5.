package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.domain.Game;

import java.util.List;

public interface PlayerServices {

    List<PlayerDTO> getAllPlayers();

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    PlayerDTO updatePlayer(String playerId, PlayerDTO playerDTO);

    String deletePlayer(String playerId);

    String deletePlayerGames(String playerId);
    List<GameDTO> listGamesByPlayerId(String playerId);
    double getSuccessRateAverage();

    PlayerDTO getWinner();

    PlayerDTO getLoser();
}



