"use client";
import { AddEventForm, FButton, FIconButton } from "@/components";
import React, { useEffect, useState } from "react";
import { MdEdit, MdInfo, MdDelete } from "react-icons/md";
import "./index.scss";
import FModal from "@/components/FModal";

const EventsPage = () => {
  const [showAddEventModal, setShowAddEventModal] = useState<boolean>(false);
  useEffect(() => {
    //hacer llamada de la api
  }, []);

  return (
    <>
      <div className="admin--events--page--container">
        <div className="admin--events--page--header">
          <div className="admin--events--page--count">
            <b>Eventos</b>
            <p>249 total</p>
          </div>
          <FButton
            text="Añadir +"
            type="primary"
            onClick={() => setShowAddEventModal(true)}
          />
        </div>
        <div className="admin--events--page--table">
          <div className="admin--events--page--table--header">
            <input type="checkbox"></input>
            <span>ID</span>
            <span>Titulo</span>
            <span>Fecha</span>
            <span>Tag</span>
            <span>Creado en</span>
            <span>Ultima actualización en</span>
            <span>Acciones</span>
          </div>
          <div className="admin--events--page--table--body">
            <div className="admin--events--page--table--item">
              <input type="checkbox"></input>
              <span>1</span>
              <span>Sevilla FC vs Arsenal</span>
              <span>2022-10-08 18:00:00</span>
              <span>Fútbol</span>
              <span>2022-10-05 18:56:23</span>
              <span>2022-10-05 19:00:12</span>
              <div className="admin--events--page--table--item--actions">
                <FIconButton icon={<MdEdit />} bgColor="green" />
                <FIconButton icon={<MdInfo />} bgColor="#19bfe3" />
                <FIconButton icon={<MdDelete />} bgColor="#ff0000" />
              </div>
            </div>
            <div className="admin--events--page--table--item">
              <input type="checkbox"></input>
              <span>1</span>
              <span>Sevilla FC vs Arsenal</span>
              <span>2022-10-08 18:00:00</span>
              <span>Fútbol</span>
              <span>2022-10-05 18:56:23</span>
              <span>2022-10-05 19:00:12</span>
              <div className="admin--events--page--table--item--actions">
                <FIconButton icon={<MdEdit />} bgColor="green" />
                <FIconButton icon={<MdInfo />} bgColor="#19bfe3" />
                <FIconButton icon={<MdDelete />} bgColor="#ff0000" />
              </div>
            </div>
            <div className="admin--events--page--table--item">
              <input type="checkbox"></input>
              <span>1</span>
              <span>Sevilla FC vs Arsenal</span>
              <span>2022-10-08 18:00:00</span>
              <span>Fútbol</span>
              <span>2022-10-05 18:56:23</span>
              <span>2022-10-05 19:00:12</span>
              <div className="admin--events--page--table--item--actions">
                <FIconButton icon={<MdEdit />} bgColor="green" />
                <FIconButton icon={<MdInfo />} bgColor="#19bfe3" />
                <FIconButton icon={<MdDelete />} bgColor="#ff0000" />
              </div>
            </div>
          </div>
        </div>
        <div className="admin--events--page--pagination"></div>
      </div>
      <FModal
        isOpen={showAddEventModal}
        onClose={() => setShowAddEventModal(false)}
        maxWidth={500}
        content={<AddEventForm />}
      />
    </>
  );
};

export default EventsPage;
