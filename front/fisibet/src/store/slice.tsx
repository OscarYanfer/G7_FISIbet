import { BetOnCouponTypes } from "@/interfaces";
import { createSlice } from "@reduxjs/toolkit";

interface betCouponInitialStateTypes {
  bets: BetOnCouponTypes[];
}

const betCouponInitialState: betCouponInitialStateTypes = {
  bets: [],
};

export const Slice = createSlice({
  name: "coupon",
  initialState: betCouponInitialState,
  reducers: {
    addBetToCoupon: (state, action) => {
      state.bets = [...state.bets, action.payload];
    },
    removeBetFromCoupon: (state, action) => {
      const updateCoupon = state.bets.filter(
        (bet) => bet.id !== action.payload
      );
      state.bets = updateCoupon;
    },
  },
});

export const { addBetToCoupon, removeBetFromCoupon } = Slice.actions;
