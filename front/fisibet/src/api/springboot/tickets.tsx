import Api, { BASE_URL_BETS_SERVICE } from ".";

export default class TicketsService {
  static async getAllTickets() {
    const rsp = await Api.get(`${BASE_URL_BETS_SERVICE}/ticket/all`);
    const rspJson = await rsp.json();
    return rspJson.content;
  }
}
