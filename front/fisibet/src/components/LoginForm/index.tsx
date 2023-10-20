"use client";
import React from "react";
import { Formik } from "formik";
import { LoginFormTypes } from "@/interfaces";
import { loginFormSchema } from "@/schemas";
import { FInputForm, FButton } from "@/components";
import { FaUser } from "react-icons/fa";
import { RiLockPasswordFill } from "react-icons/ri";
import "./index.scss";

const initialValuesLoginForm: LoginFormTypes = {
  email: "",
  password: "",
};

const LoginForm = () => {
  const handleSubmitLogin = (values: any) => {
    console.log("valores", values);
  };
  return (
    <div className="login--form--container">
      <strong>Inicia sesión</strong>
      <br></br>
      <div className="login--form--separator"></div>
      <span className="">o</span>
      <Formik
        initialValues={initialValuesLoginForm}
        onSubmit={handleSubmitLogin}
        validationSchema={loginFormSchema}
      >
        {({ handleSubmit }) => {
          return (
            <>
              <FInputForm
                type="email"
                icon={<FaUser />}
                name="email"
                label="Correo electrónico"
                placeholder="Email"
              />
              <FInputForm
                type="password"
                icon={<RiLockPasswordFill />}
                name="password"
                label="Contraseña"
                placeholder="Contraseña"
              />
              <FButton text="Iniciar sesión" onClick={handleSubmit} />
            </>
          );
        }}
      </Formik>
      <div className="login--form--options">
        <p>¿Olvidaste tu contraseña?</p>
        <p>
          ¿No tienes una cuenta?{" "}
          <a href="/">
            <b>Registrate aquí</b>
          </a>
        </p>
      </div>
    </div>
  );
};

export default LoginForm;
