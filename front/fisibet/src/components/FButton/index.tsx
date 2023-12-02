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
  border?: boolean;
}

const FButton = ({
  type = "primary",
  text = "",
  disabled = false,
  isLoading = false,
  border = true,
  icon,
  direction = "row",
  onClick,
}: FButtonProps) => {
  return (
    <button
      type="button"
      disabled={disabled || isLoading}
      onClick={onClick}
      style={{
        flexDirection: direction,
        borderWidth: border === false ? "0px" : "",
      }}
      className={`button button--${type}Btn`}
    >
      {isLoading ? (
        <div className="button--loading--spinner"></div>
      ) : (
        <>
          {text && <p>{text}</p>}
          {icon}
        </>
      )}
    </button>
  );
};

export default FButton;
