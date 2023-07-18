package com.ehong.poll.Poll;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PollService {

    private final PollRepository pollRepository;

    @Autowired
    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public List<Poll> getPolls() {
        return pollRepository.findAll();
    }

//test
    public Optional<Poll> getPoll(Long id) {
        return pollRepository.findById(id);
    }

    public void addNewPoll(Poll poll) {
        List<Poll> pollOptional = pollRepository
                .findPollByQuestion(poll.getQuestion());
        if (!pollOptional.isEmpty()) {
            throw new IllegalStateException("Question taken");
        }
        pollRepository.save(poll);
    }

    public void deletePoll(Long pollId) {
        boolean pollExists = pollRepository.existsById(pollId);
        if (!pollExists) {
            throw new IllegalStateException("This Poll ID: " + pollId + " does not exist.");
        }
        pollRepository.deleteById(pollId);
    }

    @Transactional
    public void updatePoll(Long pollId, String pollQuestion, String answerA, String answerB) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new IllegalStateException("Poll ID: " + pollId + " not found."));

        if (pollQuestion!=null&&pollQuestion.length() <= 5) {
                throw new IllegalStateException("Question is too short.");
            }
            if (pollQuestion!=null&&!poll.getQuestion().equals(pollQuestion)) {
                List<Poll> existingPolls = pollRepository.findPollByQuestion(pollQuestion);
                if (!existingPolls.isEmpty()) {
                    throw new IllegalStateException("Question already taken.");
                }
                poll.setQuestion(pollQuestion);
            }


        if (answerA != null && !answerA.equals(poll.getAnswerA())&&!Objects.equals(answerA,poll.getAnswerA())) {
            poll.setAnswerA(answerA);
        }

        if (answerB != null && !answerB.equals(poll.getAnswerB())&&!Objects.equals(answerB,poll.getAnswerB())) {
            poll.setAnswerB(answerB);
        }
    }


    //Just for fun to clean up
    public void deleteAll() {
        pollRepository.deleteAll();
    }
}
