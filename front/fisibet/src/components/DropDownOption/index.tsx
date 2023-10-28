import React, { useState } from "react";
import { MdOutlineKeyboardArrowDown } from "react-icons/md";
import "./index.scss";

interface DropDownOptionProps {
  //leagueListbyOption
  optionTitle: string;
  icon: React.ReactNode;
}

const DropDownOption = ({ optionTitle }: DropDownOptionProps) => {
  const [openDropDown, setOpenDropDown] = useState<boolean>(false);
  return (
    <div
      className="drop--down--option--container"
      onClick={() => setOpenDropDown(!openDropDown)}
    >
      <div className="drop--down--option--header">
        <p>{optionTitle}</p>
        <MdOutlineKeyboardArrowDown
          className={openDropDown ? "drop--arrow open" : "drop--arrow"}
        />
      </div>
      {openDropDown && (
        <ul className="drop--down--list">
          <li className="drop--down--item">
            <p>NBA</p>
          </li>
          <li className="drop--down--item">
            <p>NCAA-Femenino</p>
          </li>
        </ul>
      )}
    </div>
  );
};

export default DropDownOption;
