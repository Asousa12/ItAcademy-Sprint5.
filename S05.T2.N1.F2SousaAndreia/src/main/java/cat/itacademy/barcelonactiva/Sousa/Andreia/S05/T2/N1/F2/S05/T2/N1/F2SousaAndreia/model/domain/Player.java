package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.domain;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "player")
public class Player {

    @Id
    private String playerId;

    @Field(name = "PlayerName")
    private String playerName;

    @Field(name = "RegisterDate")
    private LocalDateTime registerDate;
    @Field(name = "winRate")
    private double winRate;

    @DBRef
    private List<Game> gameList;


    public Player(){
        this.gameList=new ArrayList<>();
        this.winRate = 0.0;
    }
    public Player(String playerId, String playerName, LocalDateTime registerDate) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.registerDate = registerDate;
    }

    public double calculateWinRate() {
        if (gameList == null || gameList.isEmpty()) {
            return 0.0;
        }

        long totalGames = gameList.size();
        long totalWins = gameList.stream().filter(Game::isWon).count();

        return ((double) totalWins / totalGames) * 100;
    }


    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}