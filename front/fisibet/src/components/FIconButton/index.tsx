import React from "react";
import "./index.scss";

interface FIconButtonProps {
  icon: React.ReactNode;
  onClick?: () => void;
}

const FIconButton = ({ icon, onClick }: FIconButtonProps) => {
  return (
    <span className="icon--button--container" onClick={onClick}>
      {icon}
    </span>
  );
};

export default FIconButton;
