import React from "react";
import { useField } from "formik";
import "./index.scss";

//Input creado para estar dentro de un formulario

interface FInputFormProps {
  placeholder?: string;
  name: string;
  label: string;
  icon?: React.ReactNode;
  type: "text" | "password" | "email";
}

const FInputForm = ({
  placeholder = "Input",
  name,
  type,
  label,
  icon,
}: FInputFormProps) => {
  const [field, meta, helpers] = useField(name);

  console.log("xd", field.value);
  return (
    <div className="input--form--container">
      <label>{label}</label>
      <div className="input--form--item">
        {icon && <span className="input--form--icon">{icon}</span>}
        <input
          type={type}
          onChange={(e) => helpers.setValue(e.target.value)}
          placeholder={placeholder}
          name={field.name}
          value={field.value}
        ></input>
      </div>
      <p className="input--form--error">
        {meta.touched && meta.error ? `${meta.error}` : ""}
      </p>
    </div>
  );
};

export default FInputForm;
