package com.ehong.poll.Poll;

import com.ehong.poll.PollApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PollConfig {

    private static final Logger log = LoggerFactory.getLogger(PollApplication.class);
    @Bean
    public CommandLineRunner demo(PollRepository repository) {
        return (args) -> {
            // save a few Polls
            repository.save(new Poll("Twitter or Thread?", "Twitter","Thread"));
            repository.save(new Poll("Do you like little seals?", "yes", "yes"));
            repository.save(new Poll("Pineapple on pizza?", "torturing Italian is my hobby", "excuse me?"));
            repository.save(new Poll("Svelte or React?", "Svelte", "React"));
            repository.save(new Poll("Maven or Gradle?", "Maven", "Gradle"));

            // fetch all Polls
            log.info("Polls found with findAll():");
            log.info("-------------------------------");
            for (Poll poll : repository.findAll()) {
                log.info(poll.toString());
            }
            log.info("");

            // fetch an individual Poll by ID
            Poll poll = repository.findById(1L);
            log.info("Poll found with findById(1L):");
            log.info("--------------------------------");
            log.info(poll.toString());
            log.info("");

            // fetch Polls by key words
            log.info("Poll found with findByQuestionContains('seal'):");
            log.info("--------------------------------------------");
            repository.findByQuestionContains("seal").forEach(seal -> {
                log.info(seal.toString());
            });
            for (Poll seal : repository.findByQuestionContains("seal")) {
                log.info(seal.toString());
            }
        };
    }
}
