import { usePathname } from "next/navigation";
import React from "react";
import { getLabelFromPath } from "@/helpers";
import "./index.scss";

const AdminBreadCumb = () => {
  const pathname = usePathname();
  const location = getLabelFromPath(pathname);

  return (
    <div className="admin--breadcumb--container">
      <strong>{location}</strong>
      <div className="admin--breadcumb--location--info">
        <p>Admin</p>
        <p>{location}</p>
      </div>
    </div>
  );
};

export default AdminBreadCumb;
