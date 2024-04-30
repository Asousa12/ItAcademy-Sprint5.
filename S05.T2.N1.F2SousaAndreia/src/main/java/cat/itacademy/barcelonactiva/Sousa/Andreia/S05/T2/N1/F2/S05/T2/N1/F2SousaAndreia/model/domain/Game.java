package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.domain;

import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "game")
public class Game {

    @Id
    private String gameId;
    @Field(name = "playerId")
    private String playerId;

    @Field(name = "diceOne")
    private int dice1;

    @Field(name = "diceTwo")
    private int dice2;

    @Field(name = "Result")
    private boolean won;
    @DBRef
    private Player player;

    public Game(){

    }
     public Game(Player player){
         this.playerId = player.getPlayerId();
     }
     public Game(String gameId, int dice1, int dice2, boolean won, Player player) {
        this.gameId = gameId;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.won = won;
        this.player = player;
     }

    public boolean calculateResult() {
        return this.dice1 + this.dice2 == 7;
    }

    public String getGameId() {
        return gameId;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public boolean isWon() {
        return won;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

}
