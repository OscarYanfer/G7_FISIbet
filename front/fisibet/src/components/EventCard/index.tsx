"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { addBetToCoupon, removeBetFromCoupon } from "@/store/slice";
import { EventCardTypes, RootReducerTypes } from "@/interfaces";
import { checkBetOnCoupon, checkResultOnCoupon, getBetInfo } from "@/helpers";
import "./index.scss";

interface EventCardProps {
  eventData: EventCardTypes;
}

const EventCard = ({ eventData }: EventCardProps) => {
  const {
    id,
    date,
    league,
    teamA,
    teamB,
    drawPays,
    teamApays,
    teamBpays,
    teamAscore,
    teamBscore,
  } = eventData;
  const dispatch = useDispatch();
  const coupon = useSelector((state: RootReducerTypes) => state.coupon);

  return (
    <div className="event--card--container">
      <div className="event--card--extra--info">
        <p className="event--card--league">{league}</p>
        <p className="event--card--schedule">{date}</p>
      </div>
      <div className="event--card--teams--info">
        <div className="event--card--team--score">
          <p>{teamA}</p>
          <span>{teamAscore}</span>
        </div>
        <div className="event--card--team--score">
          <p>{teamB}</p>
          <span>{teamBscore}</span>
        </div>
      </div>
      <div className="event--card--pays--info">
        <div
          className={
            checkResultOnCoupon(coupon.bets, id) === "W1"
              ? "pay-info onbet"
              : "pay-info"
          }
          onClick={() =>
            checkBetOnCoupon(coupon.bets, id, "W1")
              ? dispatch(removeBetFromCoupon(id))
              : dispatch(
                  addBetToCoupon(
                    getBetInfo(eventData, "W1", eventData?.teamApays)
                  )
                )
          }
        >
          <span>{teamApays}</span>
        </div>
        <div
          className={
            checkResultOnCoupon(coupon.bets, id) === "Empate"
              ? "pay-info onbet"
              : "pay-info"
          }
          onClick={() =>
            checkBetOnCoupon(coupon.bets, id, "Empate")
              ? dispatch(removeBetFromCoupon(id))
              : dispatch(
                  addBetToCoupon(
                    getBetInfo(eventData, "Empate", eventData?.drawPays)
                  )
                )
          }
        >
          <span>{drawPays}</span>
        </div>
        <div
          className={
            checkResultOnCoupon(coupon.bets, id) === "W2"
              ? "pay-info onbet"
              : "pay-info"
          }
          onClick={() =>
            checkBetOnCoupon(coupon.bets, id, "W2")
              ? dispatch(removeBetFromCoupon(id))
              : dispatch(
                  addBetToCoupon(
                    getBetInfo(eventData, "W2", eventData?.teamBpays)
                  )
                )
          }
        >
          <span>{teamBpays}</span>
        </div>
      </div>
    </div>
  );
};

export default EventCard;
