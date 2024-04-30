package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services;

import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.domain.Player;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.repository.PlayerRepository;
import ch.qos.logback.classic.Logger;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@Service
public class PlayerServicesImp implements PlayerServices {


    private final PlayerRepository playerRepository;

    public PlayerServicesImp(PlayerRepository playerRepository, MongoDatabase database, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.database = database;
        this.gameRepository = gameRepository;
    }

    private final MongoDatabase database;

    private final GameRepository gameRepository;
    private GameServices gameServices;
    @Autowired
    private MongoTemplate mongoTemplate;
    private static final Logger log = (Logger) LoggerFactory.getLogger(PlayerServicesImp.class);

    @Autowired
    public PlayerServicesImp(PlayerRepository playerRepository, MongoDatabase database, GameRepository gameRepository, GameServices gameServices) {
        this.playerRepository = playerRepository;
        this.database = database;
        this.gameRepository = gameRepository;
        this.gameServices = gameServices;
    }

    private Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setPlayerId(String.valueOf(playerDTO.getPlayerId()));
        player.setPlayerName(playerDTO.getPlayerName());
        player.setRegisterDate(playerDTO.getRegisterDate());
        player.setWinRate(playerDTO.getWinRate());
        return player;
    }

    private PlayerDTO convertToDto(Player updatedPlayer) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(updatedPlayer.getPlayerId());
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
        playerDTO.setRegisterDate(LocalDateTime.now());
        return convertToDto(playerRepository.save(convertToEntity(playerDTO)));
    }

    @Override
    public PlayerDTO updatePlayer(String playerId, PlayerDTO playerDTO) {
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("The player with id: " + playerId + " not founded"));

        player.setPlayerName(playerDTO.getPlayerName());

        Player updatedPlayer = playerRepository.save(player);
        return convertToDto(updatedPlayer);
    }

    @Override
    public String deletePlayer(String playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);

        if (playerOptional.isPresent()) {
            playerRepository.deleteById(playerId);
        } else {
            throw new PlayerNotFoundException("Player with id: " + playerId + " not found");
        }
        return ("Player was deleted");
    }
    @Override
    public String deletePlayerGames(String playerId) {
        System.out.println("PlayerId proporcionado: " + playerId);

        MongoCollection<Document> gamesCollection = database.getCollection("game");
        Document filter = new Document("playerId", playerId);

        System.out.println("Partidas asociadas al jugador antes de la eliminación:");
        gamesCollection.find(filter).forEach((Consumer<Document>) System.out::println);

        DeleteResult result = gamesCollection.deleteMany(filter);
        System.out.println("Número de partidas eliminadas: " + result.getDeletedCount());

        return "Se han eliminado todas las partidas del jugador con ID: " + playerId;
    }
    @Override
    public List<GameDTO> listGamesByPlayerId(String playerId) {

        MongoCollection<Document> gamesCollection = database.getCollection("game");
        Document filter = new Document("playerId", playerId);
        System.out.println("PlayerId proporcionado: " + playerId);


        System.out.println("Partidas asociadas al jugador:");
        List<GameDTO> gameDTOs = new ArrayList<>();


        FindIterable<Document> gameDocuments = gamesCollection.find(filter);
        for (Document gameDocument : gameDocuments) {
            GameDTO gameDTO = new GameDTO();

            gameDTO.setGameId(gameDocument.getObjectId("_id").toString());
            gameDTO.setDice1(gameDocument.getInteger("diceOne"));
            gameDTO.setDice2(gameDocument.getInteger("diceTwo"));
            gameDTO.setWon(gameDocument.getBoolean("Result"));
            gameDTO.setPlayerId(gameDocument.getString("playerId"));
            gameDTOs.add(gameDTO);


            System.out.println(gameDTO);
        }
        return gameDTOs;
    }
    @Override
    public PlayerDTO getWinner() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerNotFoundException("Cannot find winner, no players found");
        }
        Optional<Player> playerMaxWinRate = players.stream().max(Comparator.comparing(Player::calculateWinRate));
        if (playerMaxWinRate.isPresent()) {
            return convertToDto(playerMaxWinRate.get());
        } else {
            throw new PlayerNotFoundException("Cannot find winner");
        }
    }

    @Override
    public PlayerDTO getLoser() {
        List<Player> players = playerRepository.findAll();

        if (players.isEmpty()) {
            throw new PlayerNotFoundException("Cannot find loser, no players found");
        }

        Optional<Player> playerLessWinRate = players.stream()
                .min(Comparator.comparing(Player::calculateWinRate));

        if (playerLessWinRate.isPresent()) {
            return convertToDto(playerLessWinRate.get());
        } else {
            throw new PlayerNotFoundException("Cannot find loser");
        }
    }

    @Override
    public double getSuccessRateAverage() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerNotFoundException("Cannot calculate success rate average, no players found");
        }
        double totalWinRate = players.stream().mapToDouble(Player::calculateWinRate).sum();
        return totalWinRate / players.size();
    }

}


