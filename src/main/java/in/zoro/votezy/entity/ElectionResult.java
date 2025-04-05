package in.zoro.votezy.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class ElectionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Election name is required")
    private String electionName;

    @OneToOne
    @JoinColumn(name = "winner_id")
    private Candidate winner;

    private int totalVotes;
}
