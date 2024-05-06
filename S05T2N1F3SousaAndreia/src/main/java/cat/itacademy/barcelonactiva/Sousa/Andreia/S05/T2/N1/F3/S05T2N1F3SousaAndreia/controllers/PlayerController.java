package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.controllers;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.DTO.PlayerDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.exception.PlayerAlreadyExistsException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.services.Interfaz.GameServices;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.services.Interfaz.PlayerServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> updatePlayer (@PathVariable long playerId, @RequestBody PlayerDTO PlayerDTO){
        PlayerDTO playerToUpdate = playerServices.updatePlayer(playerId, PlayerDTO);
        return new ResponseEntity<>(playerToUpdate,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PlayerDTO>> getAllPlayers (){
        List<PlayerDTO> players = playerServices.getAllPlayers();
        return new ResponseEntity<>(players,HttpStatus.OK);
    }

    @DeleteMapping("delete/{playerId}")
    public ResponseEntity<?> deletePlayer (@PathVariable long playerId){
        playerServices.deletePlayer(playerId);
        return new ResponseEntity<>("Player deleted.",HttpStatus.OK);
    }
    @DeleteMapping("/{playerId}/games")
    public ResponseEntity<?> deletePlayerGames(@PathVariable Long playerId) {
        try {
            String resultMessage = playerServices.deletePlayerGames(playerId);
            return ResponseEntity.ok(resultMessage);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete player games due to an internal server error");
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