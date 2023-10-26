import React, { useState } from "react";
import { BetCouponItem, FButton, FIconButton } from "@/components";
import { MdSettings } from "react-icons/md";
import { RiDeleteBin5Fill } from "react-icons/ri";
import { AiOutlineClose } from "react-icons/ai";
import "./index.scss";
import { AnimatePresence, motion } from "framer-motion";

interface BetCouponProps {
  //totalCoupons  -> Aca se dara la lista de cupones del estado global de la webapp
}

const BetCoupon = () => {
  const [amountValue, setAmountValue] = useState<string>("");
  const [showCoupon, setShowCoupon] = useState<boolean>(true);
  const handleAmountChange = (value: string) => {
    const regex = /^(?:[0-9]+(?:\.[0-9]*)?|\.[0-9]+)$/;
    if (regex.test(value)) {
      setAmountValue(value);
    } else if (value.length === 0) {
      setAmountValue("");
    }
  };
  return (
    <AnimatePresence>
      {showCoupon && (
        <motion.div
          initial={{ y: 0 }}
          animate={{ y: 0 }}
          transition={{ duration: 0.3 }}
          exit={{ y: "100%" }}
          className="bet--coupon--container"
        >
          <div className="bet--coupon--header">
            <b>Cupón</b>
            <div className="bet--coupon--count">
              <span>10</span>
            </div>
            <AiOutlineClose onClick={() => setShowCoupon(false)} />
          </div>
          <div className="bet--coupon--actions">
            <p>Múltiple</p>
            <div className="bet--coupon--buttons">
              <FIconButton icon={<MdSettings />} />
              <FIconButton icon={<RiDeleteBin5Fill />} />
            </div>
          </div>
          <div className="bet--coupon--main">
            <div className="bet--coupon--items--list">
              <BetCouponItem
                teamA="Calvary FC"
                teamB="FC Pacific Greater Victoria"
                league="Tercera División Francia"
                date="05:00"
                result="W1"
                resultCuote={2.93}
              />
              <BetCouponItem
                teamA="Calvary FC"
                teamB="FC Pacific Greater Victoria"
                league="Tercera División Francia"
                date="05:00"
                result="W1"
                resultCuote={2.93}
              />
              <BetCouponItem
                teamA="Calvary FC"
                teamB="FC Pacific Greater Victoria"
                league="Tercera División Francia"
                date="05:00"
                result="W1"
                resultCuote={2.93}
              />
              <BetCouponItem
                teamA="Calvary FC"
                teamB="FC Pacific Greater Victoria"
                league="Tercera División Francia"
                date="05:00"
                result="W1"
                resultCuote={2.93}
              />
              <BetCouponItem
                teamA="Calvary FC"
                teamB="FC Pacific Greater Victoria"
                league="Tercera División Francia"
                date="05:00"
                result="W1"
                resultCuote={2.93}
              />
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
                  <b>3.225</b>
                </div>
                <div className="bet--coupon--summary--item">
                  <span>Apuesta</span>
                  <b>{amountValue || 0}</b>
                </div>
                <div className="bet--coupon--summary--item">
                  <span>Total Return</span>
                  <b>{(3.225 * Number(amountValue)).toFixed(3)}</b>
                </div>
                <div className="bet--coupon--bet--button">
                  <FButton text="Terminar apuesta" />
                </div>
              </div>
            </div>
          </div>
        </motion.div>
      )}
    </AnimatePresence>
  );
};

export default BetCoupon;
