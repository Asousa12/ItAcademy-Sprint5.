package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain;

import cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain.Game;
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

    }

    public double winRate() {
        if (gameList == null || gameList.isEmpty()) {
            return 0.0;
        }

        long totalGames = gameList.size();
        long totalWins = gameList.stream().filter(Game::isWon).count();

        return (double) totalWins / totalGames * 100;
    }

}
