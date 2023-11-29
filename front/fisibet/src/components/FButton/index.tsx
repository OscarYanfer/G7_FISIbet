"use client";
import React from "react";
import "./index.scss";

interface FButtonProps {
  type?: "primary" | "primary--inner" | "secondary";
  text?: string;
  disabled?: boolean;
  icon?: React.ReactNode;
  isLoading?: boolean;
  direction?: "row" | "row-reverse" | "column" | "column-reverse";
  onClick?: () => void;
}

const FButton = ({
  type = "primary",
  text = "Botón",
  disabled = false,
  isLoading = false,
  icon,
  direction = "row",
  onClick,
}: FButtonProps) => {
  return (
    <button
      type="button"
      disabled={disabled || isLoading}
      onClick={onClick}
      style={{ flexDirection: direction }}
      className={`button button--${type}Btn`}
    >
      {isLoading ? (
        <div className="button--loading--spinner"></div>
      ) : (
        <>
          <p>{text}</p>
          {icon}
        </>
      )}
    </button>
  );
};

export default FButton;
