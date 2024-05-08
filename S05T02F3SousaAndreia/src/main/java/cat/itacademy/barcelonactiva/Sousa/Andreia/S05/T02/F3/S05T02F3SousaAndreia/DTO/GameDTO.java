package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDTO {

    private long gameId;
    private long playerId;
    private int dice1;
    private int dice2;
    private boolean won;

}


