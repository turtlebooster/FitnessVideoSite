// axios 객체 만들기
import axios from "axios";

function createApi() {
    return axios.create({
        baseURL: process.env.VUE_APP_API_SERVER,
        headers: {
            "Content-Type": "application/json",
        }
    });
}

export { createApi };