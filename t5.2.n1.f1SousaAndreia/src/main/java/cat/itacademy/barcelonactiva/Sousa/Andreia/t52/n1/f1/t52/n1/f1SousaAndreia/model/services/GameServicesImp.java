package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.services;


import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception.GameNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain.Game;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain.Player;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameServicesImp implements GameServices {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameServicesImp(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }
    private static GameDTO convertToDTO(Game game) {
        GameDTO gameDTO = new GameDTO();

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
    public List<GameDTO> listGamesByPlayer(long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        List<Game> games = player.get().getGameList();
        if (player.isPresent() && !games.isEmpty()) {
            return games.stream().map(GameServicesImp::convertToDTO).collect(Collectors.toList());

        } else if (games.isEmpty()) {

            throw new GameNotFoundException("No se han encontrado partidas en el jugador");
        } else {

            throw new EntityNotFoundException("No se ha encontrado el jugador");
        }
    }

    @Override
    public GameDTO getGameById(Long id) {
        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            return convertToDTO(gameOptional.get());
        } else {
            throw new GameNotFoundException("Game with ID: " + id + " not found");
        }
    }

    @Override
    @Transactional
    public GameDTO createGame(long playerId)  {

        Optional<Player> playerOptional = playerRepository.findById(playerId);
        Player newplayer = playerOptional.orElseThrow(() -> new PlayerNotFoundException("player not founded"));

        Game newgame = new Game();
        dicePlayGame(newgame);
        newgame.setPlayer(newplayer);
        newplayer.addingGame(newgame);
        newgame.calculateResult();
        gameRepository.save(newgame);

        return convertToDTO(newgame);
    }
    public void dicePlayGame(Game game) {

        Random random = new Random();
        int diceOne = random.nextInt(6) + 1;
        int diceTwo = random.nextInt(6) + 1;

        game.setDice1(diceOne);
        game.setDice2(diceTwo);
    }

    @Override
    public GameDTO updateGame(long id, GameDTO gameDTO) {
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
    public String deleteSpecificGame(long gameId) {
        Optional<Game> gameOptional = gameRepository.findById(gameId);
        if (gameOptional.isEmpty()) {
            throw new GameNotFoundException("Game not found");
        }

        gameRepository.delete(gameOptional.get());
        return "Deleted game with ID " + gameId;
    }

}
