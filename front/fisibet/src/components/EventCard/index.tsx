"use client";
import React from "react";
import "./index.scss";

interface EventCardProps {
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

const EventCard = ({
  teamA,
  teamB,
  teamApays,
  teamAscore,
  teamBpays,
  teamBscore,
  drawPays,
  league,
  date,
}: EventCardProps) => {
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

export default EventCard;
