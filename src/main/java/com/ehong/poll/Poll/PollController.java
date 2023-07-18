package com.ehong.poll.Poll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/poll")
@CrossOrigin("http://localhost:5173")
public class PollController {

    public final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping
    public List<Poll> getPolls() {
        return pollService.getPolls();
    }
    //test
    @GetMapping(path = "{pollId}")
    public Optional<Poll> getPollById(@PathVariable("pollId") Long pollId) {
        return pollService.getPoll(pollId);
    }

    @PostMapping
    public void addPoll(@RequestBody Poll poll) {
        pollService.addNewPoll(poll);
    }

    @DeleteMapping(path = "{pollId}")
    public void deletePoll(
            @PathVariable("pollId") Long pollId) {
        pollService.deletePoll(pollId);
    }
    //just for fun
    @DeleteMapping(path = "deleteAll")
    public void deleteAll() {
        pollService.deleteAll();
    }

    @PutMapping(path = "{pollId}")
    public void updatePoll(
            @PathVariable("pollId") Long pollId,
            @RequestParam(required = false) String question,
            @RequestParam(required = false) String answerA,
            @RequestParam(required = false) String answerB,
            @RequestParam(required = false) Integer voteA,
            @RequestParam(required = false) Integer voteB

    ) {
        pollService.updatePoll(pollId,question,answerA,answerB,voteA,voteB);
    }

}
