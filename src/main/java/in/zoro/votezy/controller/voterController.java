package in.zoro.votezy.controller;

import in.zoro.votezy.entity.Voter;
import in.zoro.votezy.service.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class voterController {

    private VoterService voterService;

    @Autowired
    public voterController(VoterService voterService)
    {
        this.voterService=voterService;
    }

    @PostMapping("/register")
    public ResponseEntity<Voter> registerVoter(@RequestBody @Valid Voter voter)
    {
        Voter savedVoter = voterService.registerVoter(voter);
        return new ResponseEntity<>(savedVoter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voter> getVoterById(@PathVariable Long id)
    {
        Voter voter = voterService.getVoterById(id);
        return new ResponseEntity<>(voter, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Voter>> getAllVoters()
    {
        List<Voter> voterList = voterService.getAllVoters();
        return new ResponseEntity<>(voterList, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable Long id, @RequestBody Voter voter)
    {
        Voter updatedVoter = voterService.updateVoter(id, voter);
        return new ResponseEntity<>(updatedVoter, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable Long id)
    {
        voterService.deleteVoter(id);
        return new ResponseEntity<>("Voter with id: "+id+ " deleted", HttpStatus.OK);
    }
}
