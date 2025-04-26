package in.zoro.votezy.service;

import in.zoro.votezy.entity.Candidate;
import in.zoro.votezy.entity.Vote;
import in.zoro.votezy.entity.Voter;
import in.zoro.votezy.exception.DuplicateResourceException;
import in.zoro.votezy.exception.ResourceNotFoundException;
import in.zoro.votezy.repository.CandidateRepository;
import in.zoro.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService{

    private VoterRepository voterRepository;
    private CandidateRepository candidateRepository;

    @Autowired
    public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository)
    {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public Voter registerVoter(Voter voter)
    {
        if(voterRepository.existsByEmail(voter.getEmail()))
        {
            throw new DuplicateResourceException("Voter with Email "+ voter.getEmail() + " already exist");
        }
        return voterRepository.save(voter);
    }

    public List<Voter> getAllVoters()
    {
      return voterRepository.findAll();
    }

    public Voter getVoterById(Long id)
    {
        Voter voter = voterRepository.findById(id).orElse(null);
        if(voter == null){
            throw new ResourceNotFoundException("Voter with Id: " + id + " not found");
        }
        return voter;
    }

    public Voter updateVoter(Long id, Voter updatedVoter)
    {
        Voter voter= voterRepository.findById(id).orElse(null);
        if(voter == null){
            throw new ResourceNotFoundException("Voter with id: "+id+ " not found");
        }
        if(updatedVoter.getName() != null){
            voter.setName(updatedVoter.getName());
        }
        if(updatedVoter.getEmail() != null){
            voter.setEmail(updatedVoter.getEmail());
        }
        return voterRepository.save(voter);
    }

    @Transactional
    public void deleteVoter(Long id){
        Voter voter = voterRepository.findById(id).orElse(null);
        if(voter == null){
            throw new ResourceNotFoundException("cannot delete voter with id: " +id + " as it does not exist");
        }
        Vote vote = voter.getVote();
        if (vote != null) {
            Candidate candidate= vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount()-1);
            candidateRepository.save(candidate);
        }
        voterRepository.delete(voter);
    }
}
