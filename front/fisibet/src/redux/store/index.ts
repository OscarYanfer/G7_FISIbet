import persistReducer from "redux-persist/es/persistReducer";
import storage from "redux-persist/lib/storage";
import rootReducer from "../reducer";
import { createStore, applyMiddleware } from "redux";
import thunk from "redux-thunk";
import { persistStore } from "redux-persist";

export const persistConfig = {
  key: "root",
  storage,
  whitelist: ["authToken", "coupon"],
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

const middleware = [thunk];

const store = createStore(persistedReducer, applyMiddleware(...middleware));
const Persistor = persistStore(store);

export { Persistor };
export default store;
