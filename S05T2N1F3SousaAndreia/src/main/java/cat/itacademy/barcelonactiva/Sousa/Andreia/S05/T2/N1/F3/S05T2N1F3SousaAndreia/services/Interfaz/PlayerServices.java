package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.services.Interfaz;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.DTO.PlayerDTO;

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




