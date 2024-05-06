package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.DTO;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerDTO {

    private Long playerId;
    private String playerName;
    private LocalDateTime registerDate;
    private double winRate;

}