package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO;

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