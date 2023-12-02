import {
  AccountUserTypes,
  CreateAccountUser,
  LoginFormTypes,
} from "@/interfaces";
import Api, { BASE_URL_ACCOUNT_SERVICE } from ".";

export default class AccountUserService {
  static async getAllAccounts() {
    try {
      const rsp = await Api.get(`${BASE_URL_ACCOUNT_SERVICE}/accountUser/all`);
      const rspJson = await rsp.json();
      return rspJson.content;
    } catch (error) {
      throw new Error("Error al obtener los datos");
    }
  }
  static async getAccountById(id: number) {
    try {
      const rsp = await Api.get(
        `${BASE_URL_ACCOUNT_SERVICE}/accountUser/${id}`
      );
      const rspJson = await rsp.json();
      return rspJson.content;
    } catch (error) {
      throw new Error("Error al obtener los datos");
    }
  }
  static async updateUserAccount(id: number, data: CreateAccountUser) {
    try {
      const rsp = await Api.put(
        `${BASE_URL_ACCOUNT_SERVICE}/accountUser/${id}`,
        data
      );
      const rspJson = await rsp.json();
      return rspJson.message;
    } catch (error) {
      throw new Error("Error al actualizar información");
    }
  }
  static async createNewAccount(data: CreateAccountUser) {
    try {
      const rsp = await Api.post(
        `${BASE_URL_ACCOUNT_SERVICE}/accountUser/create`,
        data
      );
      const rspJson = await rsp.json();
      return rspJson;
    } catch (error) {
      throw new Error("Error al crear cuenta");
    }
  }
  static async loginAccount(data: LoginFormTypes) {
    try {
      const rsp = await Api.post(
        `${BASE_URL_ACCOUNT_SERVICE}/auth/login`,
        data
      );
      const rspJson = await rsp.json();
      return rspJson.content;
    } catch (error) {
      throw new Error("Error al iniciar sesión");
    }
  }
  static async logoutAccount(username: string) {
    try {
      const rsp = await Api.put(
        `${BASE_URL_ACCOUNT_SERVICE}/auth/closesession/${username}`
      );
      const rspJson = await rsp.json();
      return rspJson.message;
    } catch (error) {
      throw new Error("Error al cerrar sesión");
    }
  }
}
