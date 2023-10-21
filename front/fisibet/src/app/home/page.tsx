"use client";
import React from "react";
import styles from "./index.module.scss";
import { Button } from "@mui/material";
import SportsSoccerIcon from "@mui/icons-material/SportsSoccer";
import SportsVolleyballIcon from "@mui/icons-material/SportsVolleyball";
import SportsBaseballIcon from "@mui/icons-material/SportsBaseball";
import SportsEsportsIcon from "@mui/icons-material/SportsEsports";
// import ShieldIcon from '@mui/icons-material/Shield';

const HomePage = () => {
  const [events, setEvents] = React.useState([1, 1, 1, 1, 1, 1]);

  const eventList = (events: any) => {
    return events.map((item: any, index: any) => {
      return (
        <div key={index} className={styles.eventCard}>
          <h4>Partido A</h4>
          <div className={styles.eventCounters}>
            <img src="images/peru.png" alt="" />
            <h1>VS</h1>
            <img src="images/argentina.png" alt="" />
          </div>
          <div className={styles.eventBets}>
            <button className={styles.buttonBet}>
              <span>
                Perú <br /> <strong>10.00</strong>
              </span>
            </button>
            <button className={styles.buttonBet}>
              <span>
                Empate <br /> <strong>10.00</strong>
              </span>
            </button>
            <button className={styles.buttonBet}>
              <span>
                Argentina <br /> <strong>10.00</strong>
              </span>
            </button>
          </div>
        </div>
      );
    });
  };

  return (
    <div className={styles.homeContainer}>
      <div className={styles.tabContainer}>
        <ul className={styles.tabs}>
          <li>
            <p>Deportes disponibles</p>
          </li>
          <li>
            <Button className={styles.buttonTab}>
              <SportsSoccerIcon />
              Futbol
            </Button>
          </li>
          <li>
            <Button className={styles.buttonTab}>
              <SportsVolleyballIcon />
              Voley
            </Button>
          </li>
          <li>
            <Button className={styles.buttonTab}>
              <SportsBaseballIcon />
              Básket
            </Button>
          </li>
          <li>
            <Button className={styles.buttonTab}>
              <SportsEsportsIcon />
              Videojuegos
            </Button>
          </li>
        </ul>
      </div>
      <div className={styles.eventContainer}>{eventList(events)}</div>
      <div className={styles.ticketContainer}>
        <ul className={styles.ticketTabs}>
          <li>
            <Button className={styles.buttonTicketTab}>
              Cupon de apuestas
            </Button>
          </li>
          <li>
            <Button className={styles.buttonTicketTab}>Mis apuestas</Button>
          </li>
        </ul>

        <hr />

        <div className={styles.betCard}>
          <div>
            <strong>Perú</strong>
            <br />
            <span>Resultado final</span>
            <br />
            <span>Gana local</span>
          </div>
          <div>
            <strong>S/ 10.00</strong>
          </div>
        </div>
        <div className={styles.betCard}>
          <div>
            <strong>Perú</strong>
            <br />
            <span>Resultado final</span>
            <br />
            <span>Gana local</span>
          </div>
          <div>
            <strong>S/ 10.00</strong>
          </div>
        </div>
        <div className={styles.betCard}>
          <div>
            <strong>Perú</strong>
            <br />
            <span>Resultado final</span>
            <br />
            <span>Gana local</span>
          </div>
          <div>
            <strong>S/ 10.00</strong>
          </div>
        </div>

        <div>
          <span>Cuora total: S/10.00</span>
          <span>Cantidad: S/</span>
          <input />
        </div>

        <hr />
        <div className={styles.buttonTicketContainer}>
          <span>Ganancias potenciales S/ 10.00</span>
          <button className={styles.buttonTicket}>
            Apuesta ahora S/ 10.00
          </button>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
