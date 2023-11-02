import { configureStore } from "@reduxjs/toolkit";
import { couponSlice } from "./slice";

export const store = configureStore({
  reducer: {
    coupon: couponSlice.reducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
