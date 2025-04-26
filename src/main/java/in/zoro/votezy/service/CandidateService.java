package in.zoro.votezy.service;

import in.zoro.votezy.entity.Candidate;
import in.zoro.votezy.entity.Vote;
import in.zoro.votezy.exception.ResourceNotFoundException;
import in.zoro.votezy.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository)
    {
        this.candidateRepository = candidateRepository;
    }

    public Candidate addCandidate(Candidate candidate)
    {
        return candidateRepository.save(candidate);
    }
    public List<Candidate> getAllCandidate()
    {
        return candidateRepository.findAll();
    }
    public Candidate getCandidateById(Long id)
    {
        Candidate candidate=candidateRepository.findById(id).orElse(null);
        if(candidate == null){
            throw new ResourceNotFoundException("Candidare with id: "+id+ " not found");
        }
        return candidate;
    }
    public Candidate updateCandidate(Long id, Candidate updateCandidate){
        Candidate candidate = getCandidateById(id);
        if(updateCandidate.getName() != null){
            candidate.setName(updateCandidate.getName());
        }
        if(updateCandidate.getParty() != null){
            candidate.setParty(updateCandidate.getParty());
        }
        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id){
        Candidate candidate=getCandidateById(id);
        List<Vote> votes = candidate.getVote();
        for(Vote v: votes){
            v.setCandidate(null);
        }
        candidate.getVote().clear();
        candidateRepository.delete(candidate);
    }
}
