import React from "react";
import { BsInfo } from "react-icons/bs";
import { HiPencil } from "react-icons/hi";
import { FiTrash2 } from "react-icons/fi";
import "./index.scss";

interface ActionsHubProps {
  onInfo?: () => void;
  onEdit?: () => void;
  onDelete?: () => void;
}

const ActionsHub = ({ onDelete, onEdit, onInfo }: ActionsHubProps) => {
  return (
    <div className="actions--hub--container">
      {onInfo && (
        <div onClick={onInfo} className="hub hub--info">
          <BsInfo />
        </div>
      )}
      {onEdit && (
        <div onClick={onEdit} className="hub hub--edit">
          <HiPencil />
        </div>
      )}
      {onDelete && (
        <div onClick={onDelete} className="hub hub--delete">
          <FiTrash2 />
        </div>
      )}
    </div>
  );
};

export default ActionsHub;
