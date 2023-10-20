"use client";
import React from "react";
import "./index.scss";

interface FButtonProps {
  type?: "primaryBtn" | "secondaryBtn" | "tertiaryBtn";
  text?: string;
  disabled?: boolean;
  icon?: React.ReactNode;
  direction?: "row" | "row-reverse" | "column" | "column-reverse";
  onClick?: () => void;
}

const FButton = ({
  type = "primaryBtn",
  text = "Botón",
  disabled = false,
  icon,
  direction = "row",
  onClick,
}: FButtonProps) => {
  return (
    <button
      type="button"
      disabled={disabled}
      onClick={onClick}
      style={{ flexDirection: direction }}
      className={`button button--${type}`}
    >
      {text}
      {icon}
    </button>
  );
};

export default FButton;
