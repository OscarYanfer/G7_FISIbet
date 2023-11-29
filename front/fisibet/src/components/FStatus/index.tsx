import React from "react";
import "./index.scss";

interface FStatusProps {
  status: "Pendiente" | "Cerrado";
}

const FStatus = ({ status }: FStatusProps) => {
  return (
    <>
      {status === "Pendiente" ? (
        <p className="status status--ongoing">Pendiente</p>
      ) : (
        <p className="status status--closed">Cerrado</p>
      )}
    </>
  );
};

export default FStatus;
