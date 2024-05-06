package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;
    @Column(name = "PlayerName")
    private String playerName;
    @Column(name = "RegisterDate")
    private LocalDateTime registerDate;
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> gameList;
    private double winRate;

    public Player(long playerId) {
        this.playerId = playerId;
    }

    @PrePersist
    public void prePersist() {
        this.registerDate = LocalDateTime.now();
    }

    public void addingGame(Game game) {
        if (gameList == null) {
            gameList = new ArrayList<>();
        }
        gameList.add(game);
        game.setPlayer(this);

    }

    public double winRate() {
        if (gameList != null && !gameList.isEmpty()) {
            long totalGames = gameList.size();
            long totalWins = gameList.stream().filter(Game::isWon).count();

            return (double) totalWins / totalGames * 100;
        } else {
            return 0.0;
        }
    }
}
