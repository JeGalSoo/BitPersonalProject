'use client'

import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import dynamic from "next/dynamic";
import Header from "./component/common/module/header";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { getAuth } from "./component/users/service/user.slice";
import { parseCookie } from "next/dist/compiled/@edge-runtime/cookies";
import { parseCookies } from "nookies";

const ReduxProvider = dynamic(() => import("@/redux/redux-provider"), {
  ssr: false
});

const inter = Inter({ subsets: ["latin"] });


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <div className="mt-100">
          <ReduxProvider >
            {parseCookies().message === 'SUCCESS' && <Header />}
            {children}</ReduxProvider>
        </div>
      </body>
    </html>
  );
}