"use client";
import { Provider } from "react-redux";
import { PersistGate } from "redux-persist/integration/react";
import store, { Persistor } from "../store";

export const ReduxProvider = ({ children }: { children: React.ReactNode }) => {
  return (
    <PersistGate loading={null} persistor={Persistor}>
      <Provider store={store}>{children}</Provider>
    </PersistGate>
  );
};
