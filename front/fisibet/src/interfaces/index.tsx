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

export interface BetOnCouponTypes {
  id: number;
  league: string;
  teamA: string;
  teamB: string;
  result: "W1" | "W2" | "Empate";
  resultCuote: number;
  date: string;
}
