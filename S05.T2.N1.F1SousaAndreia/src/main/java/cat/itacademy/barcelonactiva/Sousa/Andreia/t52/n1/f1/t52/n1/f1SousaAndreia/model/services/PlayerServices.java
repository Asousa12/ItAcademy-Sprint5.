package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.services;

import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.DTO.PlayerDTO;

import java.util.List;

public interface PlayerServices {

    List<PlayerDTO> getAllPlayers();

    PlayerDTO createPlayer(PlayerDTO playerDTO);

    PlayerDTO updatePlayer (long playerId, PlayerDTO playerDTO);

    String deletePlayer(Long playerId);
    String deletePlayerGames(long playerId);
    double getSuccessRateAverage ();
    PlayerDTO getWinner ();
    PlayerDTO getLoser ();

}




