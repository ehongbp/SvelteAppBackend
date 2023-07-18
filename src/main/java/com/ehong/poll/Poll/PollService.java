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
    public void updatePoll(Long pollId, String question, String answerA, String answerB, Integer voteA, Integer voteB) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new IllegalStateException("Poll ID: " + pollId + " not found."));

        if (question!=null&&question.length() <= 5) {
                throw new IllegalStateException("Question is too short.");
            }
            if (question!=null&&!poll.getQuestion().equals(question)) {
                List<Poll> existingPolls = pollRepository.findPollByQuestion(question);
                if (!existingPolls.isEmpty()) {
                    throw new IllegalStateException("Question already taken.");
                }
                poll.setQuestion(question);
            }


        if (answerA != null && !answerA.equals(poll.getAnswerA())&&!Objects.equals(answerA,poll.getAnswerA())) {
            poll.setAnswerA(answerA);
        }

        if (answerB != null && !answerB.equals(poll.getAnswerB())&&!Objects.equals(answerB,poll.getAnswerB())) {
            poll.setAnswerB(answerB);
        }
        if(voteA!=null&&!Objects.equals(poll.getVoteA(), voteA)&&!voteA.equals(poll.getVoteA())) poll.setVoteA(voteA);
        if(voteB!=null&&!Objects.equals(poll.getVoteB(), voteB)&&!voteB.equals(poll.getVoteB())) poll.setVoteB(voteB);
    }


    //Just for fun to clean up
    public void deleteAll() {
        pollRepository.deleteAll();
    }

}
