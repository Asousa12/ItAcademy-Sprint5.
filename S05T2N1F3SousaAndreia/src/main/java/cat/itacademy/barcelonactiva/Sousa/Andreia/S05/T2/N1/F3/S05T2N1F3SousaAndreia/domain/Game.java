package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.domain;


import jakarta.persistence.*;
import lombok.*;


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


    public boolean gameResult() {
        return this.dice1 + this.dice2 == 7;
    }


}
