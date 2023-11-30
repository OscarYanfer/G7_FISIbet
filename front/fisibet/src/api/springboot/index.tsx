export const BASE_URL_BETS_SERVICE =
  "https://4d45-20-127-200-81.ngrok-free.app";

export default class Api {
  static get(URL: string) {
    return fetch(URL, { method: "GET" });
  }
  static post(URL: string, data: any) {
    return fetch(URL, {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
  }
  static put(URL: string, data: any) {
    return fetch(URL, {
      method: "PUT",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
  }
}