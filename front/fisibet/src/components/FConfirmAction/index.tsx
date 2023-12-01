import React from "react";
import "./index.scss";
import { FButton } from "..";
interface FConfirmActionProps {
  description?: string;
  onConfirm?: () => void;
  onReject?: () => void;
  isLoading?: boolean;
}

const FConfirmAction = ({
  description,
  isLoading,
  onConfirm,
  onReject,
}: FConfirmActionProps) => {
  return (
    <div className="confirm--action--container">
      {description && <p>{description}</p>}
      <div className="confirm--action--options">
        <FButton onClick={onReject} text="Cancelar" type="primary--inner" />
        <FButton isLoading={isLoading} onClick={onConfirm} text="Confirmar" type="primary" />
      </div>
    </div>
  );
};

export default FConfirmAction;
