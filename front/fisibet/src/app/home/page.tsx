"use client";
import React, { useState } from "react";
import {
  MdOutlineSportsSoccer,
  MdOutlineSportsVolleyball,
  MdGolfCourse,
} from "react-icons/md";
import { BetCoupon, DropDownOption, EventList } from "@/components";
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
      <div className="home--page--left--side">
        <DropDownOption optionTitle="Perú" />
        <DropDownOption optionTitle="Colombia" />
        <DropDownOption optionTitle="Chile" />
        <DropDownOption optionTitle="Uruguay" />
        <DropDownOption optionTitle="Paraguay" />
        <DropDownOption optionTitle="Perú" />
        <DropDownOption optionTitle="Colombia" />
        <DropDownOption optionTitle="Chile" />
        <DropDownOption optionTitle="Uruguay" />
        <DropDownOption optionTitle="Paraguay" />
        <DropDownOption optionTitle="Perú" />
        <DropDownOption optionTitle="Colombia" />
        <DropDownOption optionTitle="Chile" />
        <DropDownOption optionTitle="Uruguay" />
        <DropDownOption optionTitle="Paraguay" />
        <DropDownOption optionTitle="Perú" />
        <DropDownOption optionTitle="Colombia" />
        <DropDownOption optionTitle="Chile" />
        <DropDownOption optionTitle="Uruguay" />
        <DropDownOption optionTitle="Paraguay" />
      </div>
      <div className="home--page--center--container">
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
        <EventList />
      </div>
      <BetCoupon />
    </div>
  );
};

export default HomePage;
