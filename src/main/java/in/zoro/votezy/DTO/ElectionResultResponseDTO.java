package in.zoro.votezy.DTO;

import lombok.Data;

@Data
public class ElectionResultResponseDTO {

    private String electionName;
    private int totalVotes;
    private long winnerId;
    private int winnerVotes;
}
