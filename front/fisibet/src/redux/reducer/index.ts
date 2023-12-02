import { combineReducers } from "redux";
import couponReducer from "./couponReducer";
import { RootReducerTypes } from "@/interfaces";
import authTokenReducer from "./authTokenReducer";

const rootReducer = combineReducers<RootReducerTypes>({
  coupon: couponReducer,
  authToken: authTokenReducer,
});

export default rootReducer;
