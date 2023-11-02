import { BetOnCouponTypes, betCouponReducerTypes } from "@/interfaces";
import { createSlice } from "@reduxjs/toolkit";

const betCouponInitialState: betCouponReducerTypes = {
  bets: [],
};

export const couponSlice = createSlice({
  name: "coupon",
  initialState: betCouponInitialState,
  reducers: {
    addBetToCoupon: (state, action) => {
      const isAlreadyOnCoupon = state.bets.find(
        (bet) => bet.id === action.payload.id
      );
      isAlreadyOnCoupon
        ? (state.bets = state.bets.map((bet: BetOnCouponTypes) =>
            bet.id === action.payload.id
              ? {
                  ...bet,
                  result: action.payload.result,
                  resultCuote: action.payload.resultCuote,
                }
              : bet
          ))
        : (state.bets = [...state.bets, action.payload]);
    },
    removeBetFromCoupon: (state, action) => {
      const updateCoupon = state.bets.filter(
        (bet) => bet.id !== action.payload
      );
      state.bets = updateCoupon;
    },
    clearBetsFromCoupon: (state, action) => {
      state.bets = action.payload;
    },
  },
});

export const { addBetToCoupon, removeBetFromCoupon, clearBetsFromCoupon } =
  couponSlice.actions;
