package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.controllers;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.DocumentNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.GameNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.repository.PlayerRepository;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services.GameServices;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services.PlayerServices;
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
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/getAll")
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameServices.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}/games")
    public ResponseEntity<GameDTO> getGameById(@PathVariable String id) {
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

    @PostMapping("/{playerId}")
    public ResponseEntity<?> createGame(@PathVariable String playerId) {
        try {
            GameDTO createdGame = gameServices.createGame(playerId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Game created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create the game due to an internal server error");
        }
    }


    @PutMapping("update/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable String id, @RequestBody GameDTO gameDTO) {
        GameDTO updatedGame = gameServices.updateGame(id, gameDTO);
        if (updatedGame != null) {
            return ResponseEntity.ok(updatedGame);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{gameId}")
    public ResponseEntity<String> deleteSpecificGame(@PathVariable String gameId) {
        try {
            String mensaje = gameServices.deleteSpecificGame(gameId);
            return ResponseEntity.ok(mensaje);
        } catch (GameNotFoundException gnfe) {
            System.out.println(gnfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (DocumentNotFoundException dnfe) {
            System.out.println(dnfe.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}
