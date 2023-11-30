"use client";
import React, { useState } from "react";
import {
  ActionsHub,
  AddEventForm,
  FButton,
  FModal,
  FConfirmAction,
} from "@/components";
import { EventOnTableTypes, EventTypes } from "@/interfaces";
import { columnsForEvents, transformEventDataForTable } from "@/helpers";
import { useQuery } from "@tanstack/react-query";
import EventsService from "@/api/springboot/events";
import { Table } from "antd";
import "./index.scss";

const EventsPage = () => {
  const { data, isLoading, error } = useQuery({
    queryKey: ["events"],
    queryFn: () => EventsService.getAllEvents(),
  });

  const [showAddEventModal, setShowAddEventModal] = useState<boolean>(false);
  const [showDeleteEventModal, setShowDeleteEventModal] =
    useState<boolean>(false);
  const [selectedEvent, setSelectedEvent] = useState<EventTypes | null>(null);

  const columns = [
    ...columnsForEvents,
    {
      title: "Acciones",
      key: "action",
      render: (_: any, record: EventOnTableTypes) => (
        <>
          <ActionsHub
            onDelete={() => handleDeleteEvent(record.id)}
            onInfo={() => handleInfoEvent(record.id)}
          />
        </>
      ),
    },
  ];

  const handleAddEvent = () => {};

  const handleDeleteEvent = (id: number) => {
    setShowDeleteEventModal(true);
  };

  const handleUpdateEvent = () => {};

  const handleInfoEvent = (id: number) => {};

  if (isLoading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Ha ocurrido un error</p>;
  }

  return (
    <>
      <div className="admin--events--page--container">
        <div className="admin--events--page--header">
          <div className="admin--events--page--count">
            <b>Eventos</b>
            <p>{data?.length} total</p>
          </div>
          <FButton
            text="Añadir +"
            type="primary"
            onClick={() => setShowAddEventModal(true)}
          />
        </div>
      </div>
      <Table
        pagination={{ pageSize: 10 }}
        dataSource={transformEventDataForTable(data)}
        columns={columns}
      />
      <FModal
        title="Agregar evento"
        isOpen={showAddEventModal}
        onClose={() => setShowAddEventModal(false)}
        maxWidth={500}
        content={
          <AddEventForm onSubmit={() => setShowDeleteEventModal(false)} />
        }
      />
      <FModal
        title="Eliminar evento"
        isOpen={showDeleteEventModal}
        onClose={() => setShowDeleteEventModal(false)}
        maxWidth={400}
        content={
          <FConfirmAction
            onReject={() => setShowDeleteEventModal(false)}
            onConfirm={() => console.log("Evento eliminado")}
            description={`Estas a punto de eliminar el evento de la base de datos, ¿Estas seguro de proceder?`}
          />
        }
      />
    </>
  );
};

export default EventsPage;
