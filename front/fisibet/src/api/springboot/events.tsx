import { CreateEventTypes } from "@/interfaces";
import Api, { BASE_URL_BETS_SERVICE } from ".";

export default class EventsService {
  static async getAllEvents() {
    const rsp = await Api.get(`${BASE_URL_BETS_SERVICE}/event/all`);
    const rspJson = await rsp.json();
    return rspJson.content;
  }
  static async createNewEvent(data: CreateEventTypes) {
    const rsp = await Api.post(`${BASE_URL_BETS_SERVICE}/event/create`, data);
    const rspJson = await rsp.json();
    return rspJson.message;
  }
}
