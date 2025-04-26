package in.zoro.votezy.controller;

import in.zoro.votezy.entity.Candidate;
import in.zoro.votezy.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class candidateController {

    private CandidateService candidateService;;

    public candidateController(CandidateService candidateService)
    {
        this.candidateService = candidateService;
    }

    @PostMapping("/add")
    public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate)
    {
        Candidate savedCandidate=candidateService.addCandidate(candidate);
        return new ResponseEntity<Candidate>(savedCandidate, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Candidate>> getAllCandidates()
    {
        List<Candidate> candidateList=this.candidateService.getAllCandidate();
        return new ResponseEntity<List<Candidate>>(candidateList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id)
    {
        Candidate candidate = this.candidateService.getCandidateById(id);
        return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Candidate> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidate)
    {
        Candidate updateCandidate = candidateService.updateCandidate(id, candidate);
        return new ResponseEntity<>(updateCandidate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id)
    {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate with id: " + id + " deleted", HttpStatus.OK);
    }
}
