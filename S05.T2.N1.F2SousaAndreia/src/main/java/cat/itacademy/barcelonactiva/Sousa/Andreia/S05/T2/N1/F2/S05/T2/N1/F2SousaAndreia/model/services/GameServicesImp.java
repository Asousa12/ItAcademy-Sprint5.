package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.GameNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.domain.Game;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.domain.Player;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.repository.PlayerRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameServicesImp implements GameServices {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameServicesImp(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }
    private static GameDTO convertToDTO(Game game) {
        GameDTO gameDTO = new GameDTO(game);

        gameDTO.setGameId(game.getGameId());
        gameDTO.setDice1(game.getDice1());
        gameDTO.setDice2(game.getDice2());
        gameDTO.setWon(game.isWon());

        return gameDTO;
    }

    @Override
    public List<GameDTO> getAllGames() {
        List<Game> games = gameRepository.findAll();
        List<GameDTO> gameDTOs = new ArrayList<>();
        for (Game game : games) {
            gameDTOs.add(convertToDTO(game));
        }

        return gameDTOs;
    }


    @Override
    public GameDTO getGameById(String id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            return convertToDTO(gameOptional.get());
        } else {
            throw new GameNotFoundException("Game with ID: " + id + " not found");
        }
    }

    @Override
    @Transactional
    public GameDTO createGame(String playerId) {
        Optional<Player> player = playerRepository.findByPlayerId(playerId);

        if (player.isPresent()) {
            Game game = new Game(player.get());
            dicePlayGame(game);
            gameRepository.save(game);
            return new GameDTO(game);
        } else {
            throw new PlayerNotFoundException(playerId);
        }
    }
    public void dicePlayGame(Game game) {
        Random random = new Random();
        int diceOne = random.nextInt(6) + 1;
        int diceTwo = random.nextInt(6) + 1;

        game.setDice1(diceOne);
        game.setDice2(diceTwo);

        game.setWon(game.calculateResult());
    }

    @Override
    public GameDTO updateGame(String id, GameDTO gameDTO) {
        Optional<Game> gameOptional = gameRepository.findById(id);
        if (gameOptional.isPresent()) {
            Game gameToUpdate = gameOptional.get();

            gameToUpdate.setDice1(gameDTO.getDice1());
            gameToUpdate.setDice2(gameDTO.getDice2());
            gameToUpdate.setWon(gameDTO.isWon());

            Game updatedGame = gameRepository.save(gameToUpdate);

            return convertToDTO(updatedGame);
        } else {
            throw new GameNotFoundException("Game with ID: " + id + " not found");
        }
    }
    public String deleteSpecificGame(String gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);

        if (gameOptional.isPresent()) {
            gameRepository.deleteById(gameId);
        } else {
            throw new GameNotFoundException("Game with id: " + gameId + " not found");
        }
        return ("Game was deleted");
    }



}
