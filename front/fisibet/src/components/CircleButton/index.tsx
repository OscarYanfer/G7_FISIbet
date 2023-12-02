import React from "react";
import "./index.scss";

interface CircleButtonProps {
  icon?: React.ReactNode;
  type?: "primary" | "secondary" | "tertiary" | "quartery" | "white";
  iconColor?: "primary" | "secondary" | "tertiary" | "quartery" | "white";
  text?: string;
  borderRadius?: string;
  loading?: boolean;
  onClick?: () => void;
}

const CircleButton = ({
  icon,
  text,
  type = "primary",
  borderRadius = "100%",
  onClick,
  iconColor,
  loading = false,
}: CircleButtonProps) => {
  return (
    <div
      onClick={onClick}
      style={{ borderRadius: borderRadius }}
      className={
        loading
          ? `circle--button--container circle--button--container--${type} loading`
          : `circle--button--container circle--button--container--${type}`
      }
    >
      {icon && (
        <span
          className={`circle--button--icon circle--button--icon--${iconColor}`}
        >
          {icon}
        </span>
      )}
      {text && <p className="circle--button--text">{text}</p>}
    </div>
  );
};

export default CircleButton;
