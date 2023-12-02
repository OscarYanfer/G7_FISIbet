import { SET_TOKEN } from "../types";

export const setToken = (id: number | null) => ({
  type: SET_TOKEN,
  payload: id,
});
