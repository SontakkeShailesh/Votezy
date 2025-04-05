package in.zoro.votezy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "voter_id", unique = true)
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
