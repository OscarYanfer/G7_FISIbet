"use client";
import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { EventCardTypes, EventTypes, RootReducerTypes } from "@/interfaces";
import {
  checkBetOnCoupon,
  checkResultOnCoupon,
  formatDate,
  getBetByResult,
  getBetInfo,
  getDayAndMonthofDate,
  getHourAndMinuteOfDate,
} from "@/helpers";
import {
  addBetToCoupon,
  removeBetFromCoupon,
} from "@/redux/actions/couponActions";
import "./index.scss";

interface EventCardProps {
  eventData: EventTypes;
}

const EventCard = ({ eventData }: EventCardProps) => {
  const dispatch = useDispatch();
  const coupon = useSelector((state: RootReducerTypes) => state.coupon);
  console.log("aca1", eventData.fechaHora);
  return (
    <div className="event--card--container">
      <div className="event--card--extra--info">
        <p className="event--card--league">{eventData.liga}</p>
        <p className="event--card--schedule">
          {getDayAndMonthofDate(eventData.fechaHora) +
            " - " +
            getHourAndMinuteOfDate(eventData.fechaHora)}
        </p>
      </div>
      <div className="event--card--teams--info">
        <div className="event--card--team--score">
          <p>{eventData.equipoA}</p>
          <span>0</span>
        </div>
        <div className="event--card--team--score">
          <p>{eventData.equipoB}</p>
          <span>0</span>
        </div>
      </div>
      <div className="event--card--pays--info">
        <div
          className={
            checkResultOnCoupon(coupon.bets, eventData.id) === "W1"
              ? "pay-info onbet"
              : "pay-info"
          }
          onClick={() =>
            checkBetOnCoupon(coupon.bets, eventData.id, "W1")
              ? dispatch(removeBetFromCoupon(eventData.id))
              : dispatch(
                  addBetToCoupon(
                    getBetInfo(
                      eventData,
                      "W1",
                      getBetByResult(eventData, eventData.equipoA).pay
                    )
                  )
                )
          }
        >
          <span>{getBetByResult(eventData, eventData.equipoA).pay}</span>
        </div>
        <div
          className={
            checkResultOnCoupon(coupon.bets, eventData.id) === "Empate"
              ? "pay-info onbet"
              : "pay-info"
          }
          onClick={() =>
            checkBetOnCoupon(coupon.bets, eventData.id, "Empate")
              ? dispatch(removeBetFromCoupon(eventData.id))
              : dispatch(
                  addBetToCoupon(
                    getBetInfo(
                      eventData,
                      "Empate",
                      getBetByResult(eventData, "Empate")?.pay
                    )
                  )
                )
          }
        >
          <span>{getBetByResult(eventData, "Empate")?.pay}</span>
        </div>
        <div
          className={
            checkResultOnCoupon(coupon.bets, eventData.id) === "W2"
              ? "pay-info onbet"
              : "pay-info"
          }
          onClick={() =>
            checkBetOnCoupon(coupon.bets, eventData.id, "W2")
              ? dispatch(removeBetFromCoupon(eventData.id))
              : dispatch(
                  addBetToCoupon(
                    getBetInfo(
                      eventData,
                      "W2",
                      getBetByResult(eventData, eventData.equipoB).pay
                    )
                  )
                )
          }
        >
          <span>{getBetByResult(eventData, eventData.equipoB)?.pay}</span>
        </div>
      </div>
    </div>
  );
};

export default EventCard;
