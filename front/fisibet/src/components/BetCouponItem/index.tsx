import React, { useRef, useState } from "react";
import { RiDeleteBin5Fill } from "react-icons/ri";
import { HiInformationCircle } from "react-icons/hi";
import { motion, AnimatePresence } from "framer-motion";
import "./index.scss";
import { useOnClickOutside } from "@/hooks/useClickOutside";
import { FIconButton } from "@/components";

interface BetCouponItemProps {
  league: string;
  teamA: string;
  teamB: string;
  result: "W1" | "W2" | "Empate";
  resultCuote: number;
  date: string;
}

const BetCouponItem = ({
  league,
  teamA,
  teamB,
  result,
  resultCuote,
  date,
}: BetCouponItemProps) => {
  const [showInfo, setShowInfo] = useState<boolean>(false);
  const outSideModalRef = useRef() as React.MutableRefObject<HTMLDivElement>;
  useOnClickOutside(outSideModalRef, () => setShowInfo(false));
  return (
    <div className="bet--coupon--item--container">
      <div className="bet--coupon--item--header">
        <p>{league}</p>
        <div className="bet--coupon--item--actions">
          <FIconButton
            onClick={() => setShowInfo(!showInfo)}
            icon={<HiInformationCircle />}
          />
          <FIconButton icon={<RiDeleteBin5Fill />} />
          <AnimatePresence>
            {showInfo && (
              <motion.div
                ref={outSideModalRef}
                initial={{ opacity: 0, scale: 0.8, right: 0 }}
                animate={{ opacity: 1, scale: 1, right: 20 }}
                exit={{ opacity: 0, scale: 0.8, right: 0 }}
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
