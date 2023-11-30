"use client";
import React from "react";
import { FButton, FInputForm } from "@/components";
import { Formik } from "formik";
import { registerFormSchema } from "@/schemas";
import { RegisterFormTypes } from "@/interfaces";
import { RiLockPasswordFill } from "react-icons/ri";
import { FaUser, FaIdCard } from "react-icons/fa";
import { MdEmail } from "react-icons/md";
import "./index.scss";
import Link from "next/link";

const initialValuesLoginForm: RegisterFormTypes = {
  username: "",
  email: "",
  dni: "",
  password: "",
  repassword: "",
};

const RegisterForm = () => {
  const handleSubmitRegister = (values: any) => {
    console.log("valores", values);
  };

  return (
    <div className="register--form--container">
      <div className="register--form--separator"></div>
      <span className="">o</span>
      <Formik
        initialValues={initialValuesLoginForm}
        onSubmit={handleSubmitRegister}
        validationSchema={registerFormSchema}
      >
        {({ handleSubmit }) => {
          return (
            <>
              <FInputForm
                type="text"
                name="username"
                label="Username"
                icon={<FaUser />}
                placeholder="Username"
              />
              <FInputForm
                type="email"
                icon={<MdEmail />}
                name="email"
                label="Correo electrónico"
                placeholder="Email"
              />
              <FInputForm
                type="text"
                icon={<FaIdCard />}
                name="dni"
                label="DNI"
                placeholder="DNI"
              />
              <FInputForm
                type="password"
                icon={<RiLockPasswordFill />}
                name="password"
                label="Contraseña"
                placeholder="Contraseña"
              />
              <FInputForm
                type="password"
                icon={<RiLockPasswordFill />}
                name="repassword"
                label="Confirmar Contraseña"
                placeholder="Contraseña"
              />

              <FButton text="Registrarse" onClick={handleSubmit} />
            </>
          );
        }}
      </Formik>
    </div>
  );
};

export default RegisterForm;
