import {
  AccountUserTypes,
  BetOnCouponTypes,
  EventCardTypes,
  EventTypes,
} from "@/interfaces";
import { routes } from "./navigation";
import { parseISO, addHours, format } from "date-fns";
import { FStatus } from "@/components";

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

export const getLabelFromPath = (path: string): string => {
  let label: string = "";
  for (const prop in routes) {
    if (routes[prop].path === path) {
      label = routes[prop].label;
      break;
    }
  }
  return label;
};

export const formatDate = (fecha: string): string => {
  const fechaObj = parseISO(fecha);

  // Ajustar manualmente la diferencia de tiempo para la zona horaria de Perú (5 horas)
  const fechaPeru = addHours(fechaObj, -5);

  // Formatear la fecha y hora
  const resultado = format(fechaPeru, "yyyy-MM-dd HH:mm");

  return resultado;
};

export const intersectionArrays = (array: any[]) => {
  const intersection = array?.reduce(
    (accumulator: any[], currentArray: any[]) => {
      return accumulator?.filter((element) =>
        currentArray.some(
          (x: any) => JSON.stringify(x) === JSON.stringify(element)
        )
      );
    }
  );
  return intersection;
};

export const getStatusForEvent = (idStatus: number): string => {
  let status = "";
  switch (idStatus) {
    case 1:
      status = "Abierto";

      break;
    case 2:
      status = "Cerrado";
      break;

    default:
      break;
  }
  return status;
};
export const getStatusForAccount = (idStatus: number): string => {
  let status = "";
  switch (idStatus) {
    case 1:
      status = "Habilitado";
      break;
    case 2:
      status = "Deshabilitado";
      break;
    default:
      break;
  }
  return status;
};

export const getStatusForTicket = (idStatus: number): string => {
  let status = "";
  switch (idStatus) {
    case 1:
      status = "Pendiente";
      break;
    case 2:
      status = "Concluido";
      break;
    default:
      break;
  }
  return status;
};

//defaultValues for Data

export const defaultEventValues: EventTypes = {
  equipoA: "",
  equipoB: "",
  fechaHora: "",
  id: 0,
  liga: "",
  name: "",
  registeredOn: "",
  status: 0,
  updatedOn: "",
  description: "",
  bets: [],
};

export const defaultAccountUserValues: AccountUserTypes = {
  id: 0,
  dni: "",
  email: "",
  username: "",
  password: "",
  status: 0,
  registeredOn: "",
  updatedOn: "",
};

//tables columns

export const columnsForTickets = [
  { title: "Id", dataIndex: "id", key: "id" },
  {
    title: "Usuario apostador",
    dataIndex: "idAccountUser",
    key: "idAccountUser",
  },
  {
    title: "Monto apostado",
    dataIndex: "amountBet",
    key: "amountBet",
    render: (_: any, { amountBet }: { amountBet: number }) => (
      <p>S/.{amountBet}</p>
    ),
  },
  {
    title: "Ganancia potencial",
    dataIndex: "totalFee",
    key: "TotalFee",
    render: (_: any, { totalFee }: { totalFee: number }) => (
      <p>S/.{totalFee}</p>
    ),
  },

  {
    title: "Estado de la apuesta",
    dataIndex: "status",
    key: "status",
    render: (_: any, { status }: { status: number }) => (
      <FStatus status={getStatusForTicket(status)} active={status === 1} />
    ),
  },
  {
    title: "Creado en",
    dataIndex: "registeredOn",
    key: "registeredOn",
    render: (_: any, { registeredOn }: { registeredOn: string }) => (
      <p>{formatDate(registeredOn)}</p>
    ),
  },
  {
    title: "Ultima modificación en",
    dataIndex: "updatedOn",
    key: "updatedOn",
    render: (
      _: any,
      { updatedOn, registeredOn }: { updatedOn: string; registeredOn: string }
    ) => <p>{formatDate(updatedOn || registeredOn)}</p>,
  },
];

export const columnsForEvents = [
  {
    title: "ID",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "Equipo W1",
    dataIndex: "equipoA",
    key: "equipoA",
  },
  {
    title: "Equipo W2",
    dataIndex: "equipoB",
    key: "equipoB",
  },
  {
    title: "Liga",
    dataIndex: "liga",
    key: "liga",
  },
  {
    title: "Fecha",
    dataIndex: "fechaHora",
    key: "fechaHora",
    render: (_: any, { fechaHora }: { fechaHora: string }) => (
      <p>{formatDate(fechaHora)}</p>
    ),
  },
  {
    title: "Status",
    dataIndex: "status",
    key: "status",
    render: (_: any, { status }: { status: number }) => (
      <FStatus status={getStatusForEvent(status)} active={status === 1} />
    ),
  },
  {
    title: "Creado en",
    dataIndex: "registeredOn",
    key: "registeredOn",
    render: (_: any, { registeredOn }: { registeredOn: string }) => (
      <p>{formatDate(registeredOn)}</p>
    ),
  },
  {
    title: "Ultima modificación en",
    dataIndex: "updatedOn",
    key: "updatedOn",
    render: (
      _: any,
      { updatedOn, registeredOn }: { updatedOn: string; registeredOn: string }
    ) => <p>{formatDate(updatedOn || registeredOn)}</p>,
  },
];

export const columnsForAccountsTable = [
  {
    title: "Id",
    dataIndex: "id",
    key: "id",
  },
  {
    title: "Usuario",
    dataIndex: "username",
    key: "username",
  },
  {
    title: "Correo",
    dataIndex: "email",
    key: "email",
  },
  {
    title: "DNI",
    dataIndex: "dni",
    key: "dni",
  },
  {
    title: "Contraseña",
    dataIndex: "password",
    key: "password",
  },
  {
    title: "Status",
    dataIndex: "status",
    key: "status",
    render: (_: any, { status }: { status: number }) => (
      <FStatus status={getStatusForAccount(status)} active={status === 1} />
    ),
  },
  {
    title: "Registrado en",
    dataIndex: "registeredOn",
    key: "registeredOn",
    render: (_: any, { registeredOn }: { registeredOn: string }) => (
      <p>{formatDate(registeredOn)}</p>
    ),
  },
  {
    title: "Ultima modificación en",
    dataIndex: "updateOn",
    key: "updatedOn",
    render: (
      _: any,
      { updatedOn, registeredOn }: { updatedOn: string; registeredOn: string }
    ) => <p>{formatDate(updatedOn || registeredOn)}</p>,
  },
];
