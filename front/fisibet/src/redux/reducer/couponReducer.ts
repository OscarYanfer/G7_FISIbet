import { BetOnCouponTypes, betCouponReducerTypes } from "@/interfaces";
import {
  ADD_BET_TO_COUPON,
  CLEAR_BETS_FROM_COUPON,
  REMOVE_BET_FROM_COUPON,
} from "../types";

const betCouponInitialState: betCouponReducerTypes = {
  bets: [],
};

export default function couponReducer(
  state = betCouponInitialState,
  action: any
): betCouponReducerTypes {
  switch (action.type) {
    case ADD_BET_TO_COUPON: {
      const isAlreadyOnCoupon = state.bets.find(
        (bet) => bet.id === action.payload.id
      );
      return {
        bets: isAlreadyOnCoupon
          ? (state.bets = state.bets.map((bet: BetOnCouponTypes) =>
              bet.id === action.payload.id
                ? {
                    ...bet,
                    result: action.payload.result,
                    resultCuote: action.payload.resultCuote,
                  }
                : bet
            ))
          : (state.bets = [...state.bets, action.payload]),
      };
    }
    case REMOVE_BET_FROM_COUPON: {
      const updateCoupon = state.bets.filter(
        (bet) => bet.id !== action.payload
      );
      return {
        bets: updateCoupon,
      };
    }
    case CLEAR_BETS_FROM_COUPON: {
      return {
        bets: [],
      };
    }
    default: {
      return state; // Ensure a default return for cases not handled
    }
  }
}
