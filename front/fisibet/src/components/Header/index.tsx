"use client";
import React, { useState } from "react";
import "./index.scss";
import { FButton, LoginForm, RegisterForm, UserHub } from "@/components";
import LogoFisibet from "../../../public/images/logofisibet.svg";
import Image from "next/image";
import Link from "next/link";
import FModal from "../FModal";
import { useSelector } from "react-redux";
import { RootReducerTypes } from "@/interfaces";

const Header = () => {
  const [showLoginModal, setShowLoginModal] = useState<boolean>(false);
  const [showRegisterModal, setShowRegisterModal] = useState<boolean>(false);
  const authToken = useSelector((state: RootReducerTypes) => state.authToken);
  console.log("acaa", authToken);
  return (
    <>
      <nav className="header--container">
        <div className="header--left--container">
          <Image src={LogoFisibet} alt="logo-fisibet" />
          <ul className="header--nav--links">
            <li>
              <Link href="/home">Apuestas deportivas</Link>
            </li>
            <li>
              <Link href="/home">Promociones</Link>
            </li>
            <li>
              <Link href="/home">Términos y condiciones</Link>
            </li>
          </ul>
        </div>
        <div className="header--cta--container">
          {authToken?.authToken?.id ? (
            <UserHub userId={authToken?.authToken?.id} />
          ) : (
            <>
              <FButton
                onClick={() => setShowLoginModal(true)}
                text="Iniciar sesión"
                border={false}
              />
              <span></span>
              <FButton
                onClick={() => setShowRegisterModal(true)}
                type="primary--inner"
                text="Registrarse"
                border={false}
              />
            </>
          )}
        </div>
      </nav>
      <FModal
        title="Iniciar Sesión"
        isOpen={showLoginModal}
        maxWidth={500}
        onClose={() => setShowLoginModal(false)}
        content={<LoginForm onSubmit={() => setShowLoginModal(false)} />}
      />
      <FModal
        title="Registrarse"
        isOpen={showRegisterModal}
        maxWidth={600}
        onClose={() => setShowRegisterModal(false)}
        content={<RegisterForm onSubmit={() => setShowRegisterModal(false)} />}
      />
    </>
  );
};

export default Header;
