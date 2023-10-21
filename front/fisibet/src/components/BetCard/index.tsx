"use client";
import React from "react";
import "./index.scss";

interface BetCardProps {
  teamA: string;
  teamAscore: number;
  teamApays: number;
  teamB: string;
  teamBscore: number;
  teamBpays: number;
  drawPays: number;
  league: string;
  date: string;
}

const BetCard = ({
  teamA,
  teamB,
  teamApays,
  teamAscore,
  teamBpays,
  teamBscore,
  drawPays,
  league,
  date,
}: BetCardProps) => {
  return (
    <div className="bet--card--container">
      <div className="bet--card--extra--info">
        <p className="bet--card--league">{league}</p>
        <p className="bet--card--schedule">{date}</p>
      </div>
      <div className="bet--card--teams--info">
        <div className="bet--card--team--score">
          <p>{teamA}</p>
          <span>{teamAscore}</span>
        </div>
        <div className="bet--card--team--score">
          <p>{teamB}</p>
          <span>{teamBscore}</span>
        </div>
      </div>
      <div className="bet--card--pays--info">
        <div className="pay-info">
          <span>{teamApays}</span>
        </div>
        <div className="pay-info">
          <span>{drawPays}</span>
        </div>
        <div className="pay-info">
          <span>{teamBpays}</span>
        </div>
      </div>
    </div>
  );
};

export default BetCard;
