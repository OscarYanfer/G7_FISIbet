"use client";
import { Providers } from "@/store/provider";
import "./globals.scss";
import { ApolloWrapper } from "./lib/apollo-wrapper";
import TanstackProvider from "@/components/Providers/TanstackProvider";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="en">
      <Providers>
        <ApolloWrapper>
          <TanstackProvider>
            <body>{children}</body>
          </TanstackProvider>
        </ApolloWrapper>
      </Providers>
    </html>
  );
}
