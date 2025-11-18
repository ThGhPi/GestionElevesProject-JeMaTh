export default class HttpClient {
  constructor(baseURL) {
    this.baseURL = baseURL;
  }

  async request(endpoint, options = {}) {
    const url = `${this.baseURL}${endpoint}`;

    const config = {
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        ...(options.headers || {}),
      },
      ...options,
    };
    console.log(config);
    const response = await fetch(url, config);

    // Failed requests
    if (!response.ok) {
      const message = await response.text();
      throw new Error(message || "Request failed");
    }

    // Try to parse JSON. If empty, return null
    try {
      return await response.json();
    } catch {
      return null;
    }
  }

  get(endpoint) {
    return this.request(endpoint, { 
      method: "GET",
      credentials: "include"
    });
  }

  post(endpoint, body) {
    console.log({
      method: "POST",
      credentials: "include",
      body: JSON.stringify(body),
    })
    return this.request(endpoint, {
      method: "POST",
      credentials: "include",
      body: JSON.stringify(body),
    });
  }

  put(endpoint, body) {
    return this.request(endpoint, {
      method: "PUT",
      credentials: "include",
      body: JSON.stringify(body),
    });
  }

  delete(endpoint) {
    return this.request(endpoint, {
      method: "DELETE",
      credentials: "include"
    });
  }
}