"use client";
import React from "react";
import { Formik } from "formik";
import { LoginFormTypes } from "@/interfaces";
import { loginFormSchema } from "@/schemas";
import { FInputForm, FButton } from "@/components";
import { FaUser } from "react-icons/fa";
import { RiLockPasswordFill } from "react-icons/ri";
import "./index.scss";
import Link from "next/link";
import { useDispatch } from "react-redux";
import { useMutation } from "@tanstack/react-query";
import AccountUserService from "@/api/springboot/account";
import { toast } from "react-toastify";
import { setToken } from "@/redux/actions/authTokenActions";

interface LoginFormProps {
  onSubmit: () => void;
}

const initialValuesLoginForm: LoginFormTypes = {
  username: "",
  password: "",
};

const LoginForm = ({ onSubmit }: LoginFormProps) => {
  const dispatch = useDispatch();
  const { mutate: loginAccount, isPending } = useMutation({
    mutationFn: (data: LoginFormTypes) => AccountUserService.loginAccount(data),
    onSuccess: (response) => {
      dispatch(setToken(response.id));
      onSubmit();
      toast.success("Inicio de sesión exitoso");
    },
    onError: (error) => {
      toast.error(`Error al iniciar sesión`);
    },
  });

  const handleSubmitLogin = (values: any) => {
    loginAccount(values);
  };
  return (
    <div className="login--form--container">
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
                type="text"
                icon={<FaUser />}
                name="username"
                label="Nombre de usuario"
                placeholder="Usuario"
              />
              <FInputForm
                type="password"
                icon={<RiLockPasswordFill />}
                name="password"
                label="Contraseña"
                placeholder="Contraseña"
              />
              <FButton
                isLoading={isPending}
                text="Iniciar sesión"
                onClick={handleSubmit}
              />
            </>
          );
        }}
      </Formik>
      <div className="login--form--options">
        <Link href="/home">
          <b>¿Olvidaste tu contraseña?</b>
        </Link>
      </div>
    </div>
  );
};

export default LoginForm;
