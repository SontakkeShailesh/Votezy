package in.zoro.votezy.service;

import in.zoro.votezy.entity.Candidate;
import in.zoro.votezy.entity.Vote;
import in.zoro.votezy.entity.Voter;
import in.zoro.votezy.exception.ResourceNotFoundException;
import in.zoro.votezy.exception.voteNotAllowedException;
import in.zoro.votezy.repository.CandidateRepository;
import in.zoro.votezy.repository.VoteRepository;
import in.zoro.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingService {

    private VoteRepository voteRepository;
    private VoterRepository voterRepository;
    private CandidateRepository candidateRepository;

    public VotingService(VoteRepository voteRepository, VoterRepository voterRepository, CandidateRepository candidateRepository)
    {
        this.voteRepository = voteRepository;
        this.candidateRepository = candidateRepository;
        this.voterRepository = voterRepository;
    }
    @Transactional
    public Vote castVote(Long voterId, Long candidateId)
    {
        if(!voterRepository.existsById(voterId)){
            throw new ResourceNotFoundException("Voter not found with Id: " +voterId);
        }
        if(!candidateRepository.existsById(candidateId)){
            throw new ResourceNotFoundException("Candidate not found with Id: "+candidateId);
        }

        Voter voter = voterRepository.findById(voterId).get();
        if(voter.isHasVoted()){
            throw new voteNotAllowedException("Voter Id: " + voterId +"has already casted vote");
        }

        Candidate candidate = candidateRepository.findById(candidateId).get();
        Vote vote=new Vote();
        vote.setCandidate(candidate);
        vote.setVoter(voter);
        //voteRepository.save(vote);

        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepository.save(candidate);
        voter.setVote(vote);
        voter.setHasVoted(true);
        voterRepository.save(voter);
        return vote;
    }

    public List<Vote> getAllVotes(){
        return voteRepository.findAll();
    }

}
