import { authTokenReducerTypes } from "@/interfaces";
import { SET_TOKEN } from "../types";

const authTokenInitialState: authTokenReducerTypes = {
  authToken: {
    id: null,
  },
};

export default function authTokenReducer(
  state = authTokenInitialState,
  action: any
): authTokenReducerTypes {
  switch (action.type) {
    case SET_TOKEN: {
      return {
        authToken: {
          id: action.payload,
        },
      };
    }
    default: {
      return state;
    }
  }
}
