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
  type: string;
  league: string;
  teamA: string;
  teamB: string;
  cuoteA: string;
  cuoteDraw: string;
  cuoteB: string;
}

export interface UpdateAccountTypes {
  dni: string;
  email: string;
  password: string;
  username: string;
}

export interface TicketTypes {
  amountBet: number;
  id: number;
  idAccountUser: number;
  number: string;
  registeredOn: string;
  status: number;
  totalFee: number;
  updatedOn: string;
}
export interface EventTypes {
  id: number;
  name: string;
  description: string;
  equipoA: string;
  equipoB: string;
  liga: string;
  fechaHora: string;
  status: number;
  bets: [];
  registeredOn: string;
  updatedOn: string;
}

export interface AccountUserTypes {
  id: number;
  username: string;
  email: string;
  dni: string;
  password: string;
  status: number;
  registeredOn: string;
  updatedOn: string;
}

export interface CreateAccountUser {
  dni: string;
  email: string;
  password: string;
  username: string;
}

export interface CreateEventTypes {
  equipoA: string;
  equipoB: string;
  fechaHora: string;
  liga: string;
  payEquipoA: number;
  payEquipoB: number;
}
