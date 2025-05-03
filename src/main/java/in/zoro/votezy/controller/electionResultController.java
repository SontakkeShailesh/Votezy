package in.zoro.votezy.controller;


import in.zoro.votezy.DTO.ElectionResultRequestDTO;
import in.zoro.votezy.DTO.ElectionResultResponseDTO;
import in.zoro.votezy.entity.ElectionResult;
import in.zoro.votezy.service.ElectionResultService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin
public class electionResultController {

    private ElectionResultService electionResultService;

    @Autowired
    public electionResultController(ElectionResultService electionResultService) {
        this.electionResultService = electionResultService;
    }

    @PostMapping("/declare")
    public ResponseEntity<ElectionResultResponseDTO> declareElectionResult(@RequestBody @Valid ElectionResultRequestDTO electionResultDTO){
        ElectionResult result = electionResultService.declareElectionResult(electionResultDTO.getElectionName());
        ElectionResultResponseDTO responseDTO = new ElectionResultResponseDTO();
        responseDTO.setElectionName(result.getElectionName());
        responseDTO.setTotalVotes(result.getTotalVotes());
        responseDTO.setWinnerId(result.getWinnerId());
        responseDTO.setWinnerVotes(result.getWinner().getVoteCount());
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ElectionResult>> getAllResults(){
        List <ElectionResult> results = electionResultService.getAllResults();
        return ResponseEntity.ok(results);
    }
}
