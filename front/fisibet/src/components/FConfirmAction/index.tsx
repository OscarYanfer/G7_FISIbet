import React from "react";
import "./index.scss";
import { FButton } from "..";
interface FConfirmActionProps {
  description?: string;
  onConfirm?: () => void;
  onReject?: () => void;
}

const FConfirmAction = ({
  description,
  onConfirm,
  onReject,
}: FConfirmActionProps) => {
  return (
    <div className="confirm--action--container">
      {description && <p>{description}</p>}
      <div className="confirm--action--options">
        <FButton onClick={onReject} text="Cancelar" type="primary--inner" />
        <FButton onClick={onConfirm} text="Confirmar" type="primary" />
      </div>
    </div>
  );
};

export default FConfirmAction;
