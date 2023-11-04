import { AddEventFormTypes } from "@/interfaces";
import React from "react";
import { Formik } from "formik";
import { AddEventFormSchema } from "@/schemas";
import { FButton, FInputForm } from "..";
import "./index.scss";

const initialValuesLoginForm: AddEventFormTypes = {
  date: "",
  league: "",
  teamA: "",
  teamB: "",
  cuoteA: "",
  cuoteB: "",
};

const AddEventForm = () => {
  const handleSubmitAddEvent = (values: AddEventFormTypes) => {
    console.log("valores", values);
  };
  return (
    <div className="addevent--form--container">
      <strong>Agregar evento</strong>
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
                  name="cuoteB"
                  label="Cuota del Equipo B"
                  placeholder="Ingresa una cuota"
                />
              </div>

              <FButton text="Crear Evento" onClick={handleSubmit} />
            </>
          );
        }}
      </Formik>
    </div>
  );
};

export default AddEventForm;
