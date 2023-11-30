import { BetOnCouponTypes, EventCardTypes, EventTypes } from "@/interfaces";
import { routes } from "./navigation";
import { ActionsHub, FStatus } from "@/components";

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
  const fechaObj = new Date(fecha);

  // Opciones para el formato de fecha
  const opcionesFecha: Intl.DateTimeFormatOptions = {
    day: "2-digit",
    month: "2-digit",
    year: "2-digit",
  };

  // Opciones para el formato de hora
  const opcionesHora: Intl.DateTimeFormatOptions = {
    hour: "2-digit",
    minute: "2-digit",
  };

  // Formatear fecha y hora por separado
  const fechaFormateada = new Intl.DateTimeFormat(
    "es-ES",
    opcionesFecha
  ).format(fechaObj);
  const horaFormateada = new Intl.DateTimeFormat("es-ES", opcionesHora).format(
    fechaObj
  );

  // Retornar la fecha y hora formateada
  return `${fechaFormateada} ${horaFormateada}`;
};

export const getStatusByNumber = (idStatus: number): string => {
  let status = "";
  switch (idStatus) {
    case 1:
      status = "Pendiente";

      break;
    case 2:
      status = "Cerrado";
      break;

    default:
      break;
  }
  return status;
};

export const transformEventDataForTable = (data: EventTypes[]) => {
  const newData = data?.map((event) => ({
    ...event,
    key: event.id,
    status: getStatusByNumber(event.status),
    fechaHora: formatDate(event.fechaHora),
    registeredOn: formatDate(event.registeredOn),
    updateOn: formatDate(event.updateOn || event.registeredOn),
  }));
  return newData;
};

//tables columns
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
  },
  {
    title: "Status",
    dataIndex: "status",
    key: "status",
    render: (_: any, { status }: { status: any }) => (
      <FStatus status={status} />
    ),
  },
  {
    title: "Creado en",
    dataIndex: "registeredOn",
    key: "registeredOn",
  },
  {
    title: "Ultima modificación en",
    dataIndex: "updateOn",
    key: "updateOn",
  },
];
