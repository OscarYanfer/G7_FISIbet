import React, { useState } from "react";
import { useRouter } from "next/navigation";
import { MdKeyboardDoubleArrowLeft } from "react-icons/md";
import { RiHome5Line, RiCoupon5Line } from "react-icons/ri";
import { BiCalendarEvent } from "react-icons/bi";
import { TbUsers } from "react-icons/tb";
import "./index.scss";
const AdminSideBar = () => {
  const router = useRouter();
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
          <li onClick={() => router.push("/admin/events")} className="active">
            <RiHome5Line />
            <span>Inicio</span>
          </li>
          <li onClick={() => router.push("/admin/events")}>
            <BiCalendarEvent />
            <span>Eventos</span>
          </li>
          <li>
            <TbUsers />
            <span>Usuarios</span>
          </li>
          <li>
            <RiCoupon5Line />
            <span>Cupones</span>
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
