package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.services;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.domain.Game;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.domain.Player;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception.GameNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.repository.GameRepository;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.repository.PlayerRepository;
import ch.qos.logback.classic.Logger;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PlayerServicesImp implements PlayerServices {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private GameServices gameServices;
    private static final Logger log = (Logger) LoggerFactory.getLogger(PlayerServicesImp.class);
    @Autowired
    public PlayerServicesImp(PlayerRepository playerRepository, GameRepository gameRepository, GameServices gameServices) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.gameServices =gameServices;
    }

    private Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player(0);
        player.setPlayerId(Math.toIntExact(playerDTO.getPlayerId()));
        player.setPlayerName(playerDTO.getPlayerName());
        player.setRegisterDate(playerDTO.getRegisterDate());
        player.setWinRate(playerDTO.getWinRate());
        return player;
    }

    private PlayerDTO convertToDto(Player updatedPlayer) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(Long.valueOf(updatedPlayer.getPlayerId()));
        playerDTO.setPlayerName(updatedPlayer.getPlayerName());
        playerDTO.setRegisterDate(updatedPlayer.getRegisterDate());
        playerDTO.setWinRate(updatedPlayer.getWinRate());
        return playerDTO;
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        if (playerDTO.getPlayerName().isBlank() || playerDTO.getPlayerName().equalsIgnoreCase("ANONIM")) {
            playerDTO.setPlayerName("ANONIM");
        } else if (playerRepository.existsByPlayerName(playerDTO.getPlayerName())) {
            throw new PlayerAlreadyExistsException("Ya existe un jugador con este nombre");
        }
        return convertToDto(playerRepository.save(convertToEntity(playerDTO)));
    }
    @Override
    public PlayerDTO updatePlayer(long playerId, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("The player with id: " + playerId + " not founded"));

        player.setPlayerName(playerDTO.getPlayerName());

        Player updatedPlayer = playerRepository.save(player);
        return convertToDto(updatedPlayer);
    }

    @Override
    public String deletePlayer(Long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isPresent()) {
            playerRepository.deleteById(playerId);
        } else {
            throw new PlayerNotFoundException("Player with id: " + playerId + " not found");
        }
        return ("Player was deleted");
    }
    @Transactional
    public String deletePlayerGames(long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) {
            throw new EntityNotFoundException("Player not found with ID: " + playerId);
        }

        Player player = playerOptional.get();
        List<Game> games = player.getGameList();
        if (games == null || games.isEmpty()) {
            throw new GameNotFoundException("No games found for player with ID: " + playerId);
        }

        gameRepository.deleteAll(games);
        player.setGameList(new ArrayList<>());

        return "Deleted all games for player with ID: " + playerId;
    }

    @Override
    public PlayerDTO getWinner() {
        List<Player> players = playerRepository.findAll();
        if(players.isEmpty()){
            throw new PlayerNotFoundException("Players not found in the system.");
        }
        Player winner = players.stream().max(Comparator.comparing(Player::winRate))
                .orElse(null);
        return convertToDto(winner);
    }
    @Override
    public PlayerDTO getLoser() {
        List<Player> players = playerRepository.findAll();
        if(players.isEmpty()){
            throw new PlayerNotFoundException("Players not found in the system.");
        }
        Player loser = players.stream().min(Comparator.comparing(Player::winRate))
                .orElse(null);
        return convertToDto(loser);
    }

    @Override
    public double getSuccessRateAverage() {
        List<Player> players = playerRepository.findAll();

        if (!players.isEmpty()) {
            double totalWinRate = players.stream().mapToDouble(Player::winRate).sum();
            return totalWinRate/players.size();
        } else {
            throw new PlayerNotFoundException("Not founded players");
        }
    }

}


