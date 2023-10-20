"use client";
import { LoginForm } from "@/components";
import LoginImage from "../../../public/images/login.svg";
import React from "react";
import Image from "next/image";
import "./index.scss";

const LoginPage = () => {
  return (
    <div className="login--page--container page--container">
      <Image src={LoginImage} alt="login-image" />
      <LoginForm />
    </div>
  );
};

export default LoginPage;
