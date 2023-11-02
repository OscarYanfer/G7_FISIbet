import { BetOnCouponTypes, EventCardTypes } from "@/interfaces";

export const getBetInfo = (
  event: EventCardTypes,
  result: "W1" | "W2" | "Empate",
  resultCuote: number
): BetOnCouponTypes => {
  const { id, league, teamA, teamB, date } = event;
  return {
    id,
    league,
    teamA,
    teamB,
    date,
    result,
    resultCuote,
  };
};

export const getTotalCuoteFromCoupon = (
  betsOnCoupon: BetOnCouponTypes[]
): number => {
  console.log(betsOnCoupon);
  const totalCuote = betsOnCoupon.reduce((acccumulator, currentValue) => {
    return acccumulator * currentValue.resultCuote;
  }, 1);
  console.log(totalCuote);
  return totalCuote;
};

export const checkBetOnCoupon = (
  betsOnCoupon: BetOnCouponTypes[],
  id: number,
  result: "W1" | "W2" | "Empate"
): boolean => {
  const isOnCoupon = betsOnCoupon.find(
    (bet) => bet.id === id && bet.result === result
  );
  return isOnCoupon ? true : false;
};
export const checkResultOnCoupon = (
  betsOnCoupon: BetOnCouponTypes[],
  id: number
): "W1" | "Empate" | "W2" | "" => {
  const betResult = betsOnCoupon.find((bet) => bet.id === id);
  return betResult?.result || "";
};
