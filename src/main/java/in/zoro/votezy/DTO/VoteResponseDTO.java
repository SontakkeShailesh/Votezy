package in.zoro.votezy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDTO {

    private String message;
    private boolean success;
    private Long voterId;
    private Long candidateId;

}
