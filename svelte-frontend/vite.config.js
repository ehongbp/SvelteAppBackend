import { svelte } from '@sveltejs/vite-plugin-svelte';

/** @type {import('vite').UserConfig} */
const config = {
	plugins: [svelte()],
	build: {
		outDir: "../src/main/resources/static"
	}
};

export default config;
