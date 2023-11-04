"use client";

import { AdminBreadCumb, AdminSideBar } from "@/components";
import "./layout.scss";
export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="admin--panel--page--container">
      <AdminSideBar />
      <div className="admin--panel--content--container">
        <AdminBreadCumb />
        {children}
      </div>
    </div>
  );
}
