import React, { useRef, useState } from "react";
import { RiDeleteBin5Fill } from "react-icons/ri";
import { HiInformationCircle } from "react-icons/hi";
import { motion, AnimatePresence } from "framer-motion";
import { useOnClickOutside } from "@/hooks/useClickOutside";
import { FIconButton } from "@/components";
import { useDispatch } from "react-redux";
import { removeBetFromCoupon } from "@/store/slice";
import { BetOnCouponTypes } from "@/interfaces";
import "./index.scss";

interface BetCouponItemProps {
  betOnCouponData: BetOnCouponTypes;
}

const BetCouponItem = ({ betOnCouponData }: BetCouponItemProps) => {
  const { id, league, teamA, teamB, result, resultCuote, date } =
    betOnCouponData;
  const [showInfo, setShowInfo] = useState<boolean>(false);
  const outSideModalRef = useRef() as React.MutableRefObject<HTMLDivElement>;
  useOnClickOutside(outSideModalRef, () => setShowInfo(false));
  const dispatch = useDispatch();

  return (
    <div className="bet--coupon--item--container">
      <div className="bet--coupon--item--header">
        <p>{league}</p>
        <div className="bet--coupon--item--actions">
          <FIconButton
            onClick={() => setShowInfo(!showInfo)}
            icon={<HiInformationCircle />}
          />
          <FIconButton
            icon={<RiDeleteBin5Fill />}
            onClick={() => dispatch(removeBetFromCoupon(id))}
          />
          <AnimatePresence>
            {showInfo && (
              <motion.div
                ref={outSideModalRef}
                initial={{ opacity: 0, scale: 0.8 }}
                animate={{ opacity: 1, scale: 1 }}
                exit={{ opacity: 0, scale: 0.8 }}
                className="bet--coupon--item--full--info"
              >
                <p>{league}</p>
                <p> 26 oct {date}</p>
                <p>
                  {teamA} vs {teamB}
                </p>
              </motion.div>
            )}
          </AnimatePresence>
        </div>
      </div>
      <div className="bet--coupon--item--info">
        <div className="bet--coupon--item--teams">
          <p>
            {teamA} - {teamB}
          </p>
          <span>26 oct</span>
        </div>
        <div className="bet--coupon--item--schedule">
          <b>Resultado del partido</b>
          <span>{date}</span>
        </div>
        <div className="bet--coupon--item--result">
          <span>{result}</span>
          <b>{resultCuote}</b>
        </div>
      </div>
    </div>
  );
};

export default BetCouponItem;
