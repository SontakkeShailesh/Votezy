package in.zoro.votezy.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {

    @NotBlank(message = "Election name required")
    private String electionName;
}
