package com.ehong.poll.Poll;

import jakarta.persistence.*;

@Entity
@Table(name="poll")
public class Poll {

    //@SequenceGenerator(name="poll_sequence",sequenceName = "poll_sequence", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "poll_sequence")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "poll_idseq_generator")
    @SequenceGenerator(name = "poll_idseq_generator", sequenceName = "poll_idseq")
    private Long id;
    private String question;
    private String answerA;
    private String answerB;

    protected Poll() {}

    public Poll(String question, String answerA, String answerB) {
        this.question = question;
        this.answerA = answerA;
        this.answerB=answerB;
    }

    @Override
    public String toString() {
        return String.format(
                "Poll[id=%d, question='%s', answerA='%s', answerB='%s']",
                id, question, answerA, answerB);
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerA() {
        return answerA;
    }
    public String getAnswerB() {
        return answerB;
    }

    public void setQuestion(String question) {
        this.question=question;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
}
