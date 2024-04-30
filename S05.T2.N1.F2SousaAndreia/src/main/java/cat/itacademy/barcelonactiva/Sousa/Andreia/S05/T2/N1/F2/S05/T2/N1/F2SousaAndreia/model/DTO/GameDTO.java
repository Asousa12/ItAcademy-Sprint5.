package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO;

import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.domain.Game;

public class GameDTO {

    private String gameId;
    private String playerId;
    private int dice1;
    private int dice2;
    private boolean won;
    public GameDTO(Game game){

    }
    public GameDTO(String gameId, String playerId, int dice1, int dice2, boolean won) {
        this.gameId = gameId;
        this.playerId=playerId;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.won = won;
    }

    public GameDTO() {

    }

    public String getGameId() {
        return gameId;
    }

    public String getPlayerId() {
        return playerId;
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

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
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


