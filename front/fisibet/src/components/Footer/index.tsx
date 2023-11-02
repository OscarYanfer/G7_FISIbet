"use client";
import React from "react";
import Link from "next/link";
import "./index.scss";

const Footer = () => {
  return (
    <footer className="footer--container">
      <div className="footer--left--container">
        <ul className="footer--nav--links">
          <li>
            <Link href="/home">Política de privacidad</Link>
          </li>
          <li>
            <Link href="/home">Términos y condiciones</Link>
          </li>
        </ul>
      </div>
      <div className="footer--right--container">
        <p>&copy; {new Date().getFullYear()} Tu Empresa</p>
      </div>
    </footer>
  );
};

export default Footer;