"use client";

import { AdminSideBar } from "@/components";

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <AdminSideBar />
      {children}
    </>
  );
}
