import React from "react";
import { FiSearch } from "react-icons/fi";
import "./index.scss";

interface FSearchProps {
  value?: string;
  onChange: (value: string) => void;
  placeholder?: string;
}

const FSearch = ({ value, onChange, placeholder }: FSearchProps) => {
  const handleChange = (value: string) => {
    onChange(value);
  };
  return (
    <div className="search--input--container">
      <input
        type="text"
        onChange={(e) => handleChange(e.target.value)}
        placeholder={placeholder}
        value={value}
      />
      <span>{<FiSearch />}</span>
    </div>
  );
};

export default FSearch;
