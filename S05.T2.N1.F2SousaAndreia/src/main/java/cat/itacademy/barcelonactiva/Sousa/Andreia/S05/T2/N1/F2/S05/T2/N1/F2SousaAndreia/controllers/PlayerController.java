package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.controllers;

import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.DocumentNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.GameNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.GameDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services.GameServices;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.services.PlayerServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerServices playerServices;
    private GameServices gameServices;

    @PostMapping("/add")
    public ResponseEntity<?> createPlayer (@RequestBody PlayerDTO playerDTO){
        try {
            PlayerDTO playerDTO2 = playerServices.createPlayer(playerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(playerDTO2);
        } catch (PlayerAlreadyExistsException paee) {
            System.out.println(paee.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un jugador con ese nombre");
        }
    }

    @PutMapping("update/{playerId}")
    public ResponseEntity<?> updatePlayer (@PathVariable String playerId, @RequestBody PlayerDTO PlayerDTO){
        PlayerDTO playerToUpdate = playerServices.updatePlayer(playerId, PlayerDTO);
        return new ResponseEntity<>(playerToUpdate,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers (){
        List<PlayerDTO> players = playerServices.getAllPlayers();
        return new ResponseEntity<>(players,HttpStatus.OK);
    }

    @DeleteMapping("delete/{playerId}")
    public ResponseEntity<?> deletePlayer (@PathVariable String playerId){
        playerServices.deletePlayer(playerId);
        return new ResponseEntity<>("Player deleted.",HttpStatus.OK);
    }
    @DeleteMapping("/{playerId}/games")
    public ResponseEntity<?> deletePlayerGames(@PathVariable String playerId) {
        try {
            String mensaje = playerServices.deletePlayerGames(playerId);
            return ResponseEntity.ok(mensaje);
        } catch (GameNotFoundException gnfe) {
            System.out.println(gnfe.getMessage());
            return ResponseEntity.notFound().build();
        } catch (DocumentNotFoundException dnfe) {
            System.out.println(dnfe.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{playerId}/games")
    public ResponseEntity<List<GameDTO>> listGamesByPlayerId(@PathVariable String playerId) {
        try {
            List<GameDTO> games = playerServices.listGamesByPlayerId(playerId);
            return ResponseEntity.ok(games);
        } catch (PlayerNotFoundException e) {
            String errorMessage = "No se encontraron juegos para el jugador con ID: " + playerId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()); // Devolver una lista vacía en lugar de null
        }
    }
    @GetMapping("/ranking/loser")
    public ResponseEntity<PlayerDTO> loserPlayer(){
        try {
            return ResponseEntity.ok(playerServices.getLoser());
        } catch (PlayerNotFoundException pnfe) {
            System.out.println(pnfe.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ranking/winner")
    public ResponseEntity<PlayerDTO> winnerPlayer(){
        try {
            return ResponseEntity.ok(playerServices.getWinner());
        } catch (PlayerNotFoundException pnfe) {
            System.out.println(pnfe.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/ranking")
    public ResponseEntity<Double> avgWinRate() {
        try {
            return ResponseEntity.ok(playerServices.getSuccessRateAverage());
        } catch (PlayerNotFoundException pnfe) {
            System.out.println(pnfe.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
