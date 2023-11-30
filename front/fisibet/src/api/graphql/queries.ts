import { gql } from "@apollo/client";

export const GET_ALL_EVENTS = gql`
  query {
    listarEventos {
      eventType
      content {
        id
        equipoA
        equipoB
        registeredOn
      }
    }
  }
`;

export const GET_ONE_EVENT = gql`
  query {
    obtenerEventoPorId(id: 2) {
      eventType
      content {
        id
        name
        description
        equipoA
        equipoB
        liga
        fechaHora
        status
        registeredOn
        updatedOn
        bets {
          id
          eventId
          name
          description
          pay
          status
          registeredOn
          updatedOn
        }
      }
    }
  }
`;

export const CREATE_EVENT = gql`
  mutation {
    guardarEvento(
      createEventRequest: {
        equipoA: $equipoA
        equipoB: $equipoB
        liga: $liga
        fechaHora: $fechaHora
        payEquipoA: $payEquipoA
        payEquipoB: $payEquipoB
      }
    ) {
      eventType
      content {
        id
        equipoA
        equipoB
        fechaHora
        liga
        status
        registeredOn
        updatedOn
      }
    }
  }
`;

export const UPDATE_EVENT = gql`
  mutation {
    actualizarEvento(
      id: $id
      updateEventRequest: {
        name: $name
        description: $description
        equipoA: $equipoA
        equipoB: $equipoB
        liga: $liga
        fechaHora: $fechaHora
        payEquipoA: $payEquipoA
        payEquipoB: $payEquipoB
        betIdEquipoA: $betIdEquipoA
        betIdEquipoB: $betIdEquipoB
      }
    ) {
      eventType
      content {
        id
        name
        description
        equipoA
        equipoB
        liga
        fechaHora
        status
        registeredOn
        updatedOn
      }
    }
  }
`;
