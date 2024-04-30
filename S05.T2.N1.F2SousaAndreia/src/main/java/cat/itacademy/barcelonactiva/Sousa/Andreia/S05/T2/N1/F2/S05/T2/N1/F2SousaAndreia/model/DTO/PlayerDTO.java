package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.model.DTO;


import java.time.LocalDateTime;
public class PlayerDTO {

    private String playerId;
    private String playerName;
    private LocalDateTime registerDate;
    private double winRate;

    public PlayerDTO(){

    }

    public PlayerDTO(String playerId, String playerName, LocalDateTime registerDate, double winRate) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.registerDate = registerDate;
        this.winRate = winRate;
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

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }
}
