package com.ehong.poll.Poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {

private final PollRepository pollRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @GetMapping
    public List<Poll> getPolls(){
        return pollRepository.findAll();
    }

    public void addNewPoll(Poll poll) {
        Optional<Poll> pollOptional = pollRepository
                .findPollByQuestion(poll.getquestion());
        if(pollOptional.isPresent()){
            throw new IllegalStateException("Question taken");
        }
        pollRepository.save(poll);
    }
}
