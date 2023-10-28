import React from "react";
import "./index.scss";

interface FIconButtonProps {
  icon: React.ReactNode;
  type?: "primary" | "secondary" | "tertiary" | "quartery" | "fifth" | "sixth";
  onClick?: () => void;
}

const FIconButton = ({
  icon,
  onClick,
  type = "fifth",
  ...props
}: FIconButtonProps) => {
  return (
    <span
      className={`icon--button icon--button--${type}IconBtn`}
      {...props}
      onClick={onClick}
    >
      {icon}
    </span>
  );
};

export default FIconButton;
