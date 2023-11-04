import React from "react";
import "./index.scss";

interface FIconButtonProps {
  icon: React.ReactNode;
  type?: "primary" | "secondary" | "tertiary" | "quartery" | "fifth" | "sixth";
  onClick?: () => void;
  bgColor?: string;
}

const FIconButton = ({
  icon,
  onClick,
  type = "fifth",
  bgColor,
  ...props
}: FIconButtonProps) => {
  return (
    <span
      className={`icon--button icon--button--${type}IconBtn`}
      style={{ backgroundColor: bgColor }}
      {...props}
      onClick={onClick}
    >
      {icon}
    </span>
  );
};

export default FIconButton;
