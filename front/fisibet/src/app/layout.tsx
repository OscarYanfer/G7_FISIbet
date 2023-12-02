"use client";
import "./globals.scss";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import TanstackProvider from "@/components/Providers/TanstackProvider";
import { ReduxProvider } from "@/redux/provider";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <body>
        <TanstackProvider>
          <ReduxProvider>
            <>
              {children}
              <ToastContainer />
            </>
          </ReduxProvider>
        </TanstackProvider>
      </body>
    </html>
  );
}
