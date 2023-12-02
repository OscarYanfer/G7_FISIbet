import * as Yup from "yup";

export const loginFormSchema = Yup.object().shape({
  username: Yup.string().required("Porfavor digite su nombre de usuario."),
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

export const AddEventFormSchema = Yup.object().shape({
  date: Yup.string().required("Digite una fecha válida"),
  type: Yup.string().required("Es necesario completar"),
  league: Yup.string().required("Es necesario completar"),
  teamA: Yup.string().required("Es necesario completar"),
  teamB: Yup.string().required("Es necesario completar"),
  cuoteA: Yup.string().required("Digite una cuota válida"),
  cuoteDraw: Yup.string().required("Digite una cuota válida"),
  cuoteB: Yup.string().required("Digite una cuota válida"),
});

export const UpdateEventFormSchema = Yup.object().shape({
  date: Yup.string().required("Digite una fecha válida"),
  type: Yup.string().required("Es necesario completar"),
  league: Yup.string().required("Es necesario completar"),
  teamA: Yup.string().required("Es necesario completar"),
  teamB: Yup.string().required("Es necesario completar"),
  cuoteA: Yup.string().required("Digite una cuota válida"),
  cuoteDraw: Yup.string().required("Digite una cuota válida"),
  cuoteB: Yup.string().required("Digite una cuota válida"),
});

export const AccountFormSchema = Yup.object().shape({
  username: Yup.string().required("Es necesario este campo"),
  email: Yup.string().required("Es necesario este campo"),
  dni: Yup.string()
    .required("Es necesario este campo")
    .test("len", "Digite un DNI válido", (val) => val.length === 8),
});
