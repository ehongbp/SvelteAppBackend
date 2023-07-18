<script>
    export let type = 'primary';
    export let flat = false;
    export let inverse = false;
</script>

<button class={type} class:flat={flat} class:inverse={inverse} on:click>
    <slot>

    </slot>
</button>


<style>
    button{
        border: 0;
        cursor: pointer;
        border-radius: 6px;
        padding: 8px 12px;
        font-weight: bold;
        box-shadow: 1px 2px 3px rgba(0,0,0,0.2);
    }
    .primary{
        background: #e65752;
        color: white;
    }
    .secondary{
        background: rgb(96, 155, 211);
        color: white;
    }
    .flat{
        box-shadow: none;
    }
    .primary.inverse{
        color:#e65752;
        background: white;
        border: 2px solid #e65752;
    }
    .secondary.inverse{
        color: rgb(96, 155, 211);
        background: white;
        border: 2px solid rgb(96, 155, 211);
    }
</style>