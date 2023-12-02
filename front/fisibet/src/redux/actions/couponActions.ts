import { BetOnCouponTypes, BetTypes } from "@/interfaces";
import {
  ADD_BET_TO_COUPON,
  CLEAR_BETS_FROM_COUPON,
  REMOVE_BET_FROM_COUPON,
} from "../types";

export const addBetToCoupon = (bet: BetOnCouponTypes) => ({
  type: ADD_BET_TO_COUPON,
  payload: bet,
});
export const removeBetFromCoupon = (id: number) => ({
  type: REMOVE_BET_FROM_COUPON,
  payload: id,
});

export const clearBetsFromCoupon = () => ({
  type: CLEAR_BETS_FROM_COUPON,
});
