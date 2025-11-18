import HttpClient from "./HttpClient";

const API_URL = "http://localhost:8081/api/";

const http = new HttpClient(API_URL);

export default http;