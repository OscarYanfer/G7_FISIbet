import { EventTypes, UpdateEventTypes } from "@/interfaces";
import { UpdateEventFormSchema } from "@/schemas";
import { Formik } from "formik";
import React from "react";
import { FButton, FInputForm, FSelectForm } from "..";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import EventsService from "@/api/springboot/events";

interface UpdateEventFormProps {
  onSubmit: () => void;
  initialValues: EventTypes;
}

const UpdateEventForm = ({ onSubmit, initialValues }: UpdateEventFormProps) => {
  const queryClient = useQueryClient();
  const { mutate: updateEvent, isPending } = useMutation({
    mutationFn: (data: UpdateEventTypes) =>
      EventsService.updateEvent(initialValues.id, data),
    onSuccess: () => {
      queryClient.invalidateQueries({
        predicate: (query) => query.queryKey.includes("events"),
      });
      onSubmit();
    },
  });
  const initialValuesForForm: UpdateEventTypes = {
    date: initialValues.fechaHora,
    type: "",
    teamA: initialValues.equipoA,
    teamB: initialValues.equipoB,
    league: initialValues.liga,
    cuoteA: "",
    cuoteB: "",
    cuoteDraw: "",
  };
  const handleSubmitUpdateEvent = (values: UpdateEventTypes) => {
    updateEvent(values);
  };
  return (
    <div className="update--event--form--container">
      <Formik
        onSubmit={handleSubmitUpdateEvent}
        validationSchema={UpdateEventFormSchema}
        initialValues={initialValuesForForm}
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
                text="Actualizar Evento"
                onClick={handleSubmit}
              />
            </>
          );
        }}
      </Formik>
    </div>
  );
};

export default UpdateEventForm;
