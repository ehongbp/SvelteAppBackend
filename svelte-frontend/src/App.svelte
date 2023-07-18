<script lang="ts">
    import {pollStore} from "./stores";
    import {onMount} from "svelte";
    import {Client} from "./api/Client";

    onMount(() => {
        Client.getAllPolls();
    });
</script>
{#each $pollStore as poll}
    {poll.question}
{/each}




