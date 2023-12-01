import { CreateEventTypes, UpdateEventTypes } from "@/interfaces";
import Api, { BASE_URL_BETS_SERVICE } from ".";

export default class EventsService {
  static async getAllEvents() {
    try {
      const rsp = await Api.get(`${BASE_URL_BETS_SERVICE}/event/all`);
      const rspJson = await rsp.json();
      return rspJson.content;
    } catch (error) {
      throw new Error("Error al obtener eventos");
    }
  }
  static async createNewEvent(data: CreateEventTypes) {
    try {
      const rsp = await Api.post(`${BASE_URL_BETS_SERVICE}/event/create`, data);
      const rspJson = await rsp.json();
      return rspJson.message;
    } catch (error) {
      throw new Error("Error al crear evento");
    }
  }
  static async updateEvent(id: number, data: UpdateEventTypes) {
    try {
      const rsp = await Api.put(`${BASE_URL_BETS_SERVICE}/event/${id}`, data);
      const rspJson = await rsp.json();
      return rspJson.message;
    } catch (error) {
      throw new Error("Error al actualizar evento");
    }
  }
  static async disableEvent(id: number) {
    try {
      const rsp = await Api.put(`${BASE_URL_BETS_SERVICE}/event/disable/${id}`, {
        state: 0,
      });
      const rspJson = await rsp.json();
      console.log("rsp",rspJson)
      return rspJson.message;
    } catch (error) {
      throw new Error("Error al deshabilitar evento");
    }
  }
}
