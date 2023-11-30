import React from "react";
import "./index.scss";

interface FStatusProps {
  status: string;
  active?: boolean;
}

const FStatus = ({ status, active }: FStatusProps) => {
  return (
    <>
      <p
        className={active ? "status status--ongoing" : "status status--closed"}
      >
        {status}
      </p>
    </>
  );
};

export default FStatus;
