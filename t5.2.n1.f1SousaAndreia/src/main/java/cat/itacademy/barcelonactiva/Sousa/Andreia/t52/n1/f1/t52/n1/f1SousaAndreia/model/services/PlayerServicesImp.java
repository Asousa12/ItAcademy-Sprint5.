package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.services;

import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception.GameNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain.Game;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain.Player;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.repository.PlayerRepository;
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
        Optional<Player> playerMaxWinRate;

        if (!players.isEmpty()) {
            playerMaxWinRate = players.stream().sorted(Comparator.comparing(Player::winRate).reversed()).findFirst();
            return convertToDto(playerMaxWinRate.get());
        } else {
            throw new PlayerNotFoundException("Cannot found players");
        }
    }
    @Override
    public PlayerDTO getLoser() {
        List<Player> players = playerRepository.findAll();
        Optional<Player> playerLessWinRate;

        if (!players.isEmpty()) {
            playerLessWinRate = players.stream().sorted(Comparator.comparing(Player::winRate)).findFirst();
            return convertToDto(playerLessWinRate.get());
        } else {
            throw new PlayerNotFoundException("Not founded players");
        }
    }
    @Override
    public double getSuccessRateAverage() {
        List<Player> players = playerRepository.findAll();

        if (!players.isEmpty()) {
            double totalWinRate = players.stream().mapToDouble(Player::winRate).sum(); //mapToDouble devuelve un flujo de "doubles". Por cada jugador, calcula su winRate.
            return totalWinRate/players.size();
        } else {
            throw new PlayerNotFoundException("Not founded players");
        }
    }

}


