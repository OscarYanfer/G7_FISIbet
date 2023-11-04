export interface LoginFormTypes {
  email: string;
  password: string;
}
export interface RegisterFormTypes {
  username: string;
  email: string;
  dni: string;
  password: string;
  repassword: string;
}

export interface EventCardTypes {
  id: number;
  teamA: string;
  teamB: string;
  teamAscore: number;
  teamApays: number;
  teamBscore: number;
  teamBpays: number;
  drawPays: number;
  league: string;
  date: string;
}

export interface BetOnCouponTypes {
  id: number;
  league: string;
  teamA: string;
  teamB: string;
  result: "W1" | "W2" | "Empate";
  resultCuote: number;
  date: string;
}

export interface RootReducerTypes {
  coupon: betCouponReducerTypes;
}
export interface betCouponReducerTypes {
  bets: BetOnCouponTypes[];
}

export interface AddEventFormTypes {
  date: string;
  league: string;
  teamA: string;
  teamB: string;
  cuoteA: string;
  cuoteB: string;
}
