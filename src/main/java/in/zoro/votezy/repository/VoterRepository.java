package in.zoro.votezy.repository;

import in.zoro.votezy.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {

    boolean existsByEmail(String email);
}
