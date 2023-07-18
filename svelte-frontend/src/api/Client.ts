import {pollStore} from "../stores";

export class Client {

    static LOCATION = "http://localhost:8080/api/v1";

    static async getJson(relativePath: string, method: string = "GET", body: any = null): Promise<any> {
        if(method != "GET") {
            return await (await fetch(`${Client.LOCATION}/${relativePath}`, {method, body})).json();
        }
        return await (await fetch(`${Client.LOCATION}/${relativePath}`, {method})).json();
    }

    static getAllPolls() {
        Client.getJson("poll").then(jsonObject => {
            pollStore.set(jsonObject)
        });
    }
}