package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.controller;

import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception.GameNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.services.GameServices;
import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.services.PlayerServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameServices gameServices;
    @Autowired
    private PlayerServices playerServices;

    @GetMapping("/getAll")
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameServices.getAllGames();
        return ResponseEntity.ok(games);
    }
    @GetMapping("/{id}/games")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        try {
            GameDTO game = gameServices.getGameById(id);
            return ResponseEntity.ok(game);
        } catch (GameNotFoundException gnfe) {
            System.out.println(gnfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException enfe) {
            System.out.println(enfe.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/players/{id}/games")
    public ResponseEntity<List<GameDTO>> listGamesByPlayer(@PathVariable("id") long playerId) {
        List<GameDTO> games = gameServices.listGamesByPlayer(playerId);
        return ResponseEntity.ok(games);
    }

    @PostMapping("/{playerId}/games")
    public ResponseEntity<?> createGame(@PathVariable Long playerId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameServices.createGame(playerId));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        GameDTO updatedGame = gameServices.updateGame(id, gameDTO);
        if (updatedGame != null) {
            return ResponseEntity.ok(updatedGame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{gameId}")
    public ResponseEntity<Void> deleteSpecificGame(@PathVariable long gameId) {
        boolean deleted = Boolean.parseBoolean(gameServices.deleteSpecificGame(gameId));
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
