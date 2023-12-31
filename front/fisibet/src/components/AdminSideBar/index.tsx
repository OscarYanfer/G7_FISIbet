import React, { useState } from "react";
import { usePathname, useRouter } from "next/navigation";
import { MdKeyboardDoubleArrowLeft } from "react-icons/md";
import { RiHome5Line, RiCoupon5Line } from "react-icons/ri";
import { BiCalendarEvent } from "react-icons/bi";
import { TbUsers } from "react-icons/tb";
import "./index.scss";
import { routes } from "@/helpers/navigation";
const AdminSideBar = () => {
  const router = useRouter();
  const pathname = usePathname();
  const [resize, setResize] = useState<boolean>(false);

  return (
    <div
      className={
        resize
          ? "admin--sidebar--container resized"
          : "admin--sidebar--container"
      }
    >
      <div className="admin--sidebar--content">
        <ul className="admin--sidebar--links--list">
          <li
            onClick={() => router.push(routes.dashboard.path)}
            className={pathname === routes.dashboard.path ? "active" : ""}
          >
            <RiHome5Line />
            <span>{routes.dashboard.label}</span>
          </li>
          <li
            onClick={() => router.push(routes.events.path)}
            className={pathname === routes.events.path ? "active" : ""}
          >
            <BiCalendarEvent />
            <span>{routes.events.label}</span>
          </li>
          <li
            onClick={() => router.push(routes.users.path)}
            className={pathname === routes.users.path ? "active" : ""}
          >
            <TbUsers />
            <span>{routes.users.label}</span>
          </li>
          <li
            onClick={() => router.push(routes.tickets.path)}
            className={pathname === routes.tickets.path ? "active" : ""}
          >
            <RiCoupon5Line />
            <span>{routes.tickets.label}</span>
          </li>
        </ul>
      </div>
      <div
        className={
          resize ? "admin--sidebar--button resized" : "admin--sidebar--button"
        }
        onClick={() => setResize(!resize)}
      >
        <MdKeyboardDoubleArrowLeft />
      </div>
      <div className="admin--sidebar--logout"></div>
    </div>
  );
};

export default AdminSideBar;
