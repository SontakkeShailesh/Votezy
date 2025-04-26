package in.zoro.votezy.repository;

import in.zoro.votezy.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {


}
