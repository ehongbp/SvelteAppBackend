<script>
    import  {fade, slide, scale} from 'svelte/transition';
    import {pollStore} from '../stores/pollStore.js'
    import {flip} from 'svelte/animate';
    import PollDetails from "./PollDetails.svelte";
    export let polls =[];


</script>


<div class="poll-list">
    {#each $pollStore as poll (poll.id)}
        <div in:fade out:scale|local animate:flip>
            <PollDetails {poll} />
        </div>
    {/each}
</div>

<style>
    .poll-list{
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-gap: 20px
    }
</style>