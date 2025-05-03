package in.zoro.votezy.controller;

import in.zoro.votezy.DTO.VoteRequestDTO;
import in.zoro.votezy.DTO.VoteResponseDTO;
import in.zoro.votezy.entity.Vote;
import in.zoro.votezy.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class votingController {
    private VotingService votingService;

    @Autowired
    public votingController(VotingService votingService){
        this.votingService=votingService;
    }
    @PostMapping("/cast")
    public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequest){

        Vote vote = votingService.castVote(voteRequest.getVoterId(), voteRequest.getCandidateId());
        VoteResponseDTO voteResponse = new VoteResponseDTO("Vote casted successfully", true, voteRequest.getVoterId(), voteRequest.getCandidateId());
        return new ResponseEntity<>(voteResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes(){
        List<Vote>voteList = votingService.getAllVotes();
        return new ResponseEntity<>(voteList, HttpStatus.OK);
    }
}
