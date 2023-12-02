import { CreateTicketTypes } from "@/interfaces";
import Api, { BASE_URL_BETS_SERVICE } from ".";

export default class TicketsService {
  static async getAllTickets() {
    const rsp = await Api.get(`${BASE_URL_BETS_SERVICE}/ticket/all`);
    const rspJson = await rsp.json();
    return rspJson.content;
  }
  static async createTicket(data: CreateTicketTypes) {
    try {
      const rsp = await Api.post(
        `${BASE_URL_BETS_SERVICE}/ticket/create`,
        data
      );
      const rspJson = await rsp.json();
      return rspJson.message;
    } catch (error) {
      throw new Error("Erro al crear ticket");
    }
  }
}
