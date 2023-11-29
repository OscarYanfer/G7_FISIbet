import { useField } from "formik";
import React from "react";
import "./index.scss";

interface FSelectFormProps {
  name: string;
  label: string;
  defaultOption: string;
}

const FSelectForm = ({
  name,
  label,
  defaultOption = "Elegir opción",
}: FSelectFormProps) => {
  const [field, meta, helpers] = useField(name);
  const handleChange = (value: string) => {
    helpers.setValue(value);
  };
  return (
    <div className="select--form--container">
      <label htmlFor={field.name}>{label}</label>
      <select
        id={field.name}
        name={field.name}
        value={field.value}
        onChange={(e) => handleChange(e.target.value)}
      >
        <option value="">{defaultOption}</option>
        <option value="ra">Futbol</option>
        <option value="ga">Voley</option>
      </select>
      <p className="select--form--error">
        {meta.touched && meta.error ? `${meta.error}` : ""}
      </p>
    </div>
  );
};

export default FSelectForm;
