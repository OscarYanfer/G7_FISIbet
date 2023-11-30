import { AccountUserTypes, UpdateAccountTypes } from "@/interfaces";
import { AccountFormSchema } from "@/schemas";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { Formik } from "formik";
import React from "react";
import { FButton, FInputForm } from "..";
import AccountUserService from "@/api/springboot/account";

interface UpdateAccountFormProps {
  onSubmit: () => void;
  initialValues: AccountUserTypes;
}

const UpdateAccountForm = ({
  onSubmit,
  initialValues,
}: UpdateAccountFormProps) => {
  const initialValuesForForm: UpdateAccountTypes = {
    dni: initialValues?.dni || "",
    email: initialValues?.email || "",
    password: initialValues?.password || "",
    username: initialValues?.username || "",
  };

  const queryClient = useQueryClient();

  const { mutate: submitUpdateAccount, isPending } = useMutation({
    mutationFn: (data: UpdateAccountTypes) =>
      AccountUserService.updateUserAccount(initialValues?.id, data),
    onSuccess: () => {
      queryClient.invalidateQueries({
        predicate: (query) => query.queryKey.includes("accounts"),
      });
      onSubmit();
    },
  });
  const handleSubmitUpdateAccount = (values: UpdateAccountTypes) => {
    submitUpdateAccount(values);
  };
  return (
    <div className="updateaccount--form--container">
      <Formik
        onSubmit={handleSubmitUpdateAccount}
        validationSchema={AccountFormSchema}
        initialValues={initialValuesForForm}
      >
        {({ handleSubmit }) => {
          return (
            <>
              <div className="form--input--group--double">
                <FInputForm
                  type="text"
                  name="username"
                  label="Nombre de usuario"
                  placeholder="Usuario"
                />
                <FInputForm
                  type="text"
                  name="email"
                  label="Correo electrónico"
                  placeholder="Email"
                />
              </div>
              <div className="form--input--group--double">
                <FInputForm
                  type="text"
                  name="dni"
                  label="DNI"
                  placeholder="DNI"
                />
                <FInputForm
                  type="password"
                  name="password"
                  label="Contraseña"
                  placeholder="Contraseña"
                />
              </div>
              <FButton
                isLoading={isPending}
                text="Actualizar cuenta"
                onClick={handleSubmit}
              />
            </>
          );
        }}
      </Formik>
    </div>
  );
};

export default UpdateAccountForm;
