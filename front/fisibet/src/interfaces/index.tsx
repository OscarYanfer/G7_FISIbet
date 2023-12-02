export interface LoginFormTypes {
  username: string;
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

export interface CreateTicketTypes {
  amountBet: number;
  idAccountUser: number;
  totalFee: number;
  betIds: number[];
}

export interface RootReducerTypes {
  coupon: betCouponReducerTypes;
  authToken?: authTokenReducerTypes;
}
export interface betCouponReducerTypes {
  bets: BetOnCouponTypes[];
}
export interface authTokenReducerTypes {
  authToken: { id: number | null };
}

export interface AddEventTypes {
  date: string;
  type: string;
  league: string;
  teamA: string;
  teamB: string;
  cuoteA: string;
  cuoteDraw: string;
  cuoteB: string;
}

export interface UpdateEventFormTypes {
  date: string;
  type: string;
  league: string;
  teamA: string;
  teamB: string;
  cuoteA: string;
  cuoteDraw: string;
  cuoteB: string;
}
export interface UpdateEventTypes {
  betIdEmpate: number;
  betIdEquipoA: number;
  betIdEquipoB: number;
  equipoA: string;
  equipoB: string;
  fechaHora: string;
  liga: string;
  payEmpate: number;
  payEquipoA: number;
  payEquipoB: number;
}

export interface BetTypes {
  description: string;
  eventId: number;
  id: number;
  name: string;
  pay: number; // cuota...;
  registeredOn: string;
  status: number;
  updatedOn: string;
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
  [key: string]: number | string;
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
  bets: BetTypes[];
  registeredOn: string;
  updatedOn: string;
  [key: string]: number | string | BetTypes[];
}

export interface WalletUserTypes {
  accountNumber: string;
  id: number;
  registeredOn: string;
  saldo: number;
  state: number;
  updateOn: string;
}

export interface AccountUserTypes {
  dni: string;
  email: string;
  id: number;
  password: string;
  registeredOn: string;
  status: number;
  updatedOn: string;
  username: string;
  wallet: WalletUserTypes;
  [key: string]: number | string | WalletUserTypes;
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
