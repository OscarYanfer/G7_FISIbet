"use client";
import React from "react";
import { FButton, FInputForm } from "@/components";
import { Formik } from "formik";
import { registerFormSchema } from "@/schemas";
import {
  CreateAccountUser,
  LoginFormTypes,
  RegisterFormTypes,
} from "@/interfaces";
import { RiLockPasswordFill } from "react-icons/ri";
import { FaUser, FaIdCard } from "react-icons/fa";
import { MdEmail } from "react-icons/md";
import { useMutation } from "@tanstack/react-query";
import AccountUserService from "@/api/springboot/account";
import "./index.scss";
import { toast } from "react-toastify";
import { useDispatch } from "react-redux";
import { setToken } from "@/redux/actions/authTokenActions";

interface RegisterFormProps {
  onSubmit: () => void;
}

const initialValuesRegisterForm: RegisterFormTypes = {
  username: "",
  email: "",
  dni: "",
  password: "",
  repassword: "",
};

const RegisterForm = ({ onSubmit }: RegisterFormProps) => {
  const dispatch = useDispatch();

  // const { mutate: loginAccount, isPending: loadingLogin } = useMutation({
  //   mutationFn: (data: LoginFormTypes) => AccountUserService.loginAccount(data),
  //   onSuccess: (response) => {
  //     dispatch(setToken(response.id));
  //   },
  // });

  const { mutate: submitAccount, isPending } = useMutation({
    mutationFn: (data: CreateAccountUser) =>
      AccountUserService.createNewAccount(data),
    onSuccess: (response) => {
      console.log({ response });
      // loginAccount({
      //   username: response?.username,
      //   password: response?.password,
      // });
      toast.success("Registro exitoso");
      onSubmit();
    },
    onError: () => {
      toast.error("Hubo un error al crear cuenta");
    },
  });

  const handleSubmitRegister = (values: RegisterFormTypes) => {
    const dataToSend = {
      dni: values.dni,
      email: values.email,
      password: values.password,
      username: values.username,
    };
    submitAccount(dataToSend);
  };

  return (
    <div className="register--form--container">
      <div className="register--form--separator"></div>
      <span className="">o</span>
      <Formik
        initialValues={initialValuesRegisterForm}
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

              <FButton
                isLoading={isPending}
                text="Registrarse"
                onClick={handleSubmit}
              />
            </>
          );
        }}
      </Formik>
    </div>
  );
};

export default RegisterForm;
