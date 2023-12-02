"use client";
import React, { useEffect, useState } from "react";
import { BetCouponItem, FButton, FIconButton } from "@/components";
import { MdSettings } from "react-icons/md";
import { RiDeleteBin5Fill, RiCoupon5Line } from "react-icons/ri";
import { AiOutlineClose } from "react-icons/ai";
import { AnimatePresence, motion } from "framer-motion";
import { FaHeartBroken } from "react-icons/fa";
import { useDispatch, useSelector } from "react-redux";
import { RootReducerTypes } from "@/interfaces";
import { getTotalCuoteFromCoupon } from "@/helpers";

import "./index.scss";
import { clearBetsFromCoupon } from "@/redux/actions/couponActions";

const BetCoupon = () => {
  const [amountValue, setAmountValue] = useState<string>("");
  const [showCoupon, setShowCoupon] = useState<boolean>(true);
  const coupon = useSelector((state: RootReducerTypes) => state.coupon);
  const dispatch = useDispatch();

  const handleAmountChange = (value: string) => {
    const regex = /^(?:[0-9]+(?:\.[0-9]*)?|\.[0-9]+)$/;
    if (regex.test(value)) {
      setAmountValue(value);
    } else if (value.length === 0) {
      setAmountValue("");
    }
  };
  const handleResize = () => {
    if (window.innerWidth >= 870) {
      setShowCoupon(true);
    } else {
      setShowCoupon(false);
    }
  };

  useEffect(() => {
    window.addEventListener("resize", handleResize);
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  return (
    <>
      <AnimatePresence>
        {showCoupon && (
          <div className="bet--coupon--container">
            <div className="bet--coupon--header">
              <b>Cupón</b>
              <div className="bet--coupon--count">
                <span>{coupon.bets.length}</span>
              </div>
              <AiOutlineClose onClick={() => setShowCoupon(false)} />
            </div>
            <div className="bet--coupon--actions">
              {coupon.bets.length === 0 ? null : (
                <p>{coupon.bets.length === 1 ? "Simple" : "Multiple"}</p>
              )}
              <div className="bet--coupon--buttons">
                <FIconButton icon={<MdSettings />} />
                <FIconButton
                  onClick={() => dispatch(clearBetsFromCoupon())}
                  icon={<RiDeleteBin5Fill />}
                />
              </div>
            </div>
            <div className="bet--coupon--main">
              {coupon.bets.length > 0 ? (
                <>
                  <div className="bet--coupon--items--list">
                    {coupon &&
                      coupon.bets.map((bet) => {
                        return (
                          <BetCouponItem key={bet.id} betOnCouponData={bet} />
                        );
                      })}
                  </div>
                  <div className="bet--coupon--footer">
                    <div className="bet--coupon--amount--input">
                      <input
                        onChange={(e) => handleAmountChange(e.target.value)}
                        value={amountValue}
                        placeholder="Apuesta"
                        type="text"
                      ></input>
                    </div>
                    <div className="bet--coupon--summary">
                      <div className="bet--coupon--summary--item">
                        <span>Total de cuotas</span>
                        <b>{getTotalCuoteFromCoupon(coupon.bets).toFixed(2)}</b>
                      </div>
                      <div className="bet--coupon--summary--item">
                        <span>Apuesta</span>
                        <b>{amountValue || 0}</b>
                      </div>
                      <div className="bet--coupon--summary--item">
                        <span>Total Return</span>
                        <b>
                          {(
                            getTotalCuoteFromCoupon(coupon.bets) *
                            Number(amountValue)
                          ).toFixed(2)}
                        </b>
                      </div>
                      <div className="bet--coupon--bet--button">
                        <FButton text="Terminar apuesta" />
                      </div>
                    </div>
                  </div>
                </>
              ) : (
                <div className="bet--coupon--no--bets">
                  <FaHeartBroken />
                  <b>No hay apuestas por mostrar</b>
                  <p>
                    Al parecer aún no has seleccionado tus jugadas ganadoras.
                  </p>
                </div>
              )}
            </div>
          </div>
        )}
      </AnimatePresence>
      <div className="bet--coupon--modal--button">
        <FIconButton
          type="primary"
          onClick={() => setShowCoupon(true)}
          icon={<RiCoupon5Line />}
        />
      </div>
    </>
  );
};

export default BetCoupon;
