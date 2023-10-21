import * as Yup from "yup";

export const loginFormSchema = Yup.object().shape({
  email: Yup.string().required("Porfavor digite su email."),
  password: Yup.string().required("Porfavor digite su contraseña."),
});

export const registerFormSchema = Yup.object().shape({
  username: Yup.string().required("Porfavor digite su email"),
  email: Yup.string().required("Porfavor digite su email"),
  dni: Yup.string()
    .required("Porfavor digite su dni")
    .test("len", "Digite un DNI válido", (val) => val.length === 8),
  password: Yup.string()
    .required("Porfavor digite una contraseña")
    .test("len", "Digite una contraseña válida", (val) => val.length <= 20),
  repassword: Yup.string()
    .required("Porfavor digite de nuevo su contraseña")
    .oneOf([Yup.ref("password")], "Las contraseñas deben coincidir"),
});
