interface Route {
  path: string;
  label: string;
}
export const routesForFB = {
  user: {
    path: "/",
  },
  admin: {
    path: "/admin",
  },
};

export const routes: { [key: string]: Route } = {
  initial: { path: `${routesForFB.user.path}`, label: "" },
  home: { path: `${routesForFB.user.path}/home`, label: "Principal" },
  dashboard: { path: `${routesForFB.admin.path}`, label: "Dashboard" },
  events: { path: `${routesForFB.admin.path}/events`, label: "Eventos" },
  users: { path: `${routesForFB.admin.path}/users`, label: "Usuarios" },
  coupons: { path: `${routesForFB.admin.path}/coupons`, label: "Cupones" },
  tickets: { path: `${routesForFB.admin.path}/tickets`, label: "Tickets" },
};
