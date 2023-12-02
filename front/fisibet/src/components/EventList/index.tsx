import React from "react";
import "./index.scss";
import { EventCard } from "..";
import { EventTypes } from "@/interfaces";

interface EventListProps {
  data: EventTypes[];
}

const eventListFakeData = [
  {
    id: 1,
    teamA: "Calvary FC",
    teamApays: 1.12,
    teamAscore: 1,
    teamB: "FC Pacific Greater Victoria",
    teamBpays: 2.05,
    teamBscore: 2,
    drawPays: 3.43,
    league: "Tercera División Francia",
    date: "05:00",
  },
  {
    id: 2,
    teamA: "Santos FC",
    teamApays: 1.32,
    teamAscore: 2,
    teamB: "FC Pacific Southam",
    teamBpays: 2.05,
    teamBscore: 1,
    drawPays: 4.05,
    league: "Tercera División Francia",
    date: "03:00",
  },
  {
    id: 3,
    teamA: "Gorillas FC",
    teamApays: 1.52,
    teamAscore: 0,
    teamB: "Michis FC",
    teamBpays: 1.35,
    teamBscore: 0,
    drawPays: 1.15,
    league: "Tercera División Francia",
    date: "08:00",
  },
];

const EventList = ({ data }: EventListProps) => {
  return (
    <div className="event--bets--list">
      {data &&
        data?.map((event) => {
          return <EventCard key={event.id} eventData={event} />;
        })}
    </div>
  );
};

export default EventList;
