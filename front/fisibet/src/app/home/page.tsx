"use client";
import React, { useState } from "react";
import {
  MdOutlineSportsSoccer,
  MdOutlineSportsVolleyball,
  MdGolfCourse,
} from "react-icons/md";
import { BetCard, BetCoupon, BetCouponItem } from "@/components";
import "./index.scss";

const sportTypes = [
  {
    id: 1,
    name: "Fútbol",
    icon: <MdOutlineSportsSoccer />,
  },
  { id: 2, name: "Volleyball", icon: <MdOutlineSportsVolleyball /> },
  { id: 3, name: "Golf", icon: <MdGolfCourse /> },
];

const HomePage = () => {
  const [sportType, setSportType] = useState<string>(sportTypes[0].name);
  return (
    <div className="home--page--container page--container">
      <p>HOLA</p>
      <div className="home--page--main--container">
        <div className="events--sports--list">
          {sportTypes &&
            sportTypes.map((sport) => {
              return (
                <div
                  key={sport?.id}
                  className={
                    sportType === sport.name
                      ? "event--sport--item active"
                      : "event--sport--item"
                  }
                  onClick={() => setSportType(sport?.name)}
                >
                  {sport?.icon}
                  <p>{sport?.name}</p>
                </div>
              );
            })}
        </div>
        <div className="event--bets--list">
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
          <BetCard
            teamA="Calvary FC"
            teamApays={1.12}
            teamAscore={1}
            teamB="FC Pacific Greater Victoria"
            teamBpays={30.05}
            teamBscore={2}
            drawPays={10.01}
            league="Tercera División Francia"
            date="05:00"
          />
        </div>
      </div>

      <BetCoupon />
    </div>
  );
};

export default HomePage;
