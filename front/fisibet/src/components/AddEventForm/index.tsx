import { AddEventFormTypes, CreateEventTypes } from "@/interfaces";
import React from "react";
import { Formik } from "formik";
import { AddEventFormSchema } from "@/schemas";
import { FButton, FInputForm, FSelectForm } from "..";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import EventsService from "@/api/springboot/events";
import "./index.scss";

interface AddEventFormProps {
  onSubmit: () => void;
}

export const initialValuesLoginForm: AddEventFormTypes = {
  date: "",
  type: "",
  league: "",
  teamA: "",
  teamB: "",
  cuoteA: "",
  cuoteDraw: "",
  cuoteB: "",
};

const AddEventForm = ({ onSubmit }: AddEventFormProps) => {
  const queryClient = useQueryClient();
  const { mutate: submitEvent, isPending } = useMutation({
    mutationFn: (data: CreateEventTypes) => EventsService.createNewEvent(data),
    onSuccess: () => {
      queryClient.invalidateQueries({
        predicate: (query) => query.queryKey.includes("events"),
      });
      onSubmit();
    },
  });
  const handleSubmitAddEvent = (values: AddEventFormTypes) => {
    const dataToSend = {
      equipoA: values.teamA,
      equipoB: values.teamB,
      payEquipoA: Number(values.cuoteA),
      payEquipoB: Number(values.cuoteB),
      fechaHora: values.date,
      liga: values.league,
    };
    submitEvent(dataToSend);
  };
  return (
    <div className="addevent--form--container">
      <Formik
        initialValues={initialValuesLoginForm}
        onSubmit={handleSubmitAddEvent}
        validationSchema={AddEventFormSchema}
      >
        {({ handleSubmit }) => {
          return (
            <>
              <div className="form--input--group--double ">
                <FInputForm
                  type="text"
                  name="teamA"
                  label="Equipo A"
                  placeholder="Nombre del equipo"
                />
                <FInputForm
                  type="text"
                  name="teamB"
                  label="Equipo B"
                  placeholder="Nombre del equipo"
                />
                <FSelectForm
                  label="Tipo de Evento"
                  name="type"
                  defaultOption="--Seleccionar tipo--"
                />
              </div>
              <div className="form--input--group--double ">
                <FInputForm
                  type="datetime-local"
                  name="date"
                  label="Fecha y Hora"
                />
                <FInputForm
                  type="text"
                  name="league"
                  label="Liga"
                  placeholder="Ingrese liga perteneciente"
                />
              </div>

              <div className="form--input--group--double">
                <FInputForm
                  type="text"
                  regex={/^(?:[0-9]+(?:\.[0-9]*)?|\.[0-9]+)$/}
                  name="cuoteA"
                  label="Cuota del Equipo A"
                  placeholder="Ingresa una cuota"
                />
                <FInputForm
                  type="text"
                  regex={/^(?:[0-9]+(?:\.[0-9]*)?|\.[0-9]+)$/}
                  name="cuoteDraw"
                  label="Cuota del Empate"
                  placeholder="Ingresa una cuota"
                />
                <FInputForm
                  type="text"
                  regex={/^(?:[0-9]+(?:\.[0-9]*)?|\.[0-9]+)$/}
                  name="cuoteB"
                  label="Cuota del Equipo B"
                  placeholder="Ingresa una cuota"
                />
              </div>
              <FButton
                isLoading={isPending}
                text="Crear Evento"
                onClick={handleSubmit}
              />
            </>
          );
        }}
      </Formik>
    </div>
  );
};

export default AddEventForm;
