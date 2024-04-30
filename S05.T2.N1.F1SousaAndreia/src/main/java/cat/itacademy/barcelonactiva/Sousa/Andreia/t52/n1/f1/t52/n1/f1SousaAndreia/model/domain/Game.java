package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.model.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name ="games")
public class Game {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameId;
    @Column(name = "diceOne")
    private int dice1;
    @Column(name = "diceTwo")
    private int dice2;
    @Getter
    @Column(name = "Result", columnDefinition = "BIT(1)")
    private boolean won;
    @ManyToOne
    @JoinColumn(name = "playerId", nullable = false)

    private Player player;


    public boolean calculateResult() {
        if (this.dice1 + this.dice2 == 7) {
            this.won = true;
        } else {
            this.won = false;
        }
        return this.won;
    }


}
