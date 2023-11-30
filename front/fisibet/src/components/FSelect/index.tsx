import React from "react";
import "./index.scss";

interface FSelectOption {
  label: string;
  value: string;
}

interface FSelectProps {
  onChange: (value: string) => void;
  options: FSelectOption[];
  label?: string;
  disabled?: boolean;
  defaultValue?: string;
  value: string;
}

const FSelect = ({
  onChange,
  value,
  defaultValue,
  label,
  options,
  disabled = false,
}: FSelectProps) => {
  const handleChange = (option: string) => {
    onChange(option);
  };
  return (
    <div className="select--input--container">
      {label && <p>{label}</p>}
      <select
        disabled={disabled}
        value={value}
        onChange={(e) => handleChange(e.target.value)}
      >
        {defaultValue && <option value="">{defaultValue}</option>}
        {options?.map((option, index) => {
          return (
            <option key={index} value={option.value}>
              {option.label}
            </option>
          );
        })}
      </select>
    </div>
  );
};

export default FSelect;
