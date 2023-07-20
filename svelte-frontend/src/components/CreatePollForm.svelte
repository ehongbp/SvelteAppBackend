<script>
    import { createEventDispatcher } from "svelte/internal";
    import Button from "../shared/Button.svelte";
    import {pollStore} from "../stores/pollStore";

    let dispatch = createEventDispatcher();
    let fields = {question: '', answerA: '', answerB:'',voteA: 0,voteB: 0};
    let errors = {question: '', answerA: '', answerB:'',voteA: 0,voteB: 0};
    let valid = false;

    const submitHandler = () =>{
        //validate question and answers
        valid=true;
        if(fields.question.trim().length<5){
            valid= false;
            errors.question='Question must be at least 5 characters long';
        }else{
            errors.question='';
        }
        if(fields.question.trim().length<1){
            valid= false;
            errors.answerA='Answer A cannot be empty';
        }else{
            errors.answerA='';
        }
        if(fields.question.trim().length<1){
            valid= false;
            errors.answerB='Answer B cannot be empty';
        }else{
            errors.answerB='';
        }
        //add new poll
        if(valid){
            let poll = {...fields, voteA:0,voteB:0, id: Math.random()};
            pollStore.update(currentPolls =>{
                return [poll, ...currentPolls];
            });
            dispatch('add');
        }

    }
</script>


<form on:submit|preventDefault={submitHandler}>
    <div class="form-field">
        <label for="question">Poll Question: </label>
        <input type="text" id="Question" bind:value={fields.question}>
        <div class="error">{errors.question}</div>
    </div>
    <div class="form-field">
        <label for="answer-a">Answer A: </label>
        <input type="text" id="answer-a" bind:value={fields.answerA}>
        <div class="error">{errors.answerA}</div>
    </div>
    <div class="form-field">
        <label for="answer-b">Answer B: </label>
        <input type="text" id="answer-b" bind:value={fields.answerB}>
        <div class="error">{errors.answerB}</div>
    </div>
    <Button type="secondary" flat={true}>Add Poll</Button>
</form>
<style>
    form{
        width: 400px;
        margin: 0 auto;
        text-align: center;
    }
    .form-field{
        margin: 18px auto;
    }
    input{
        width: 100%;
        border-radius: 6px;
    }
    label{
        margin: 10px auto;
        text-align: left;
    }
    .error{
        font-weight: bold;
        font-size: 12px;
        color: #e65752;
    }
</style>