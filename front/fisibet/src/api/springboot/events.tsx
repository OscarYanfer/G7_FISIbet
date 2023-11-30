import { CreateEventTypes } from "@/interfaces";
import Api, { BASE_URL_BETS_SERVICE } from ".";

export default class EventsService {
  static async getAllEvents() {
    try {
      const rsp = await Api.get(`${BASE_URL_BETS_SERVICE}/event/all`);
      const rspJson = await rsp.json();
      return rspJson.content;
    } catch (error) {
      throw new Error("Error al obtener datos");
    }
  }
  static async createNewEvent(data: CreateEventTypes) {
    try {
      const rsp = await Api.post(`${BASE_URL_BETS_SERVICE}/event/create`, data);
      const rspJson = await rsp.json();
      return rspJson.message;
    } catch (error) {
      throw error;
    }
  }
}
