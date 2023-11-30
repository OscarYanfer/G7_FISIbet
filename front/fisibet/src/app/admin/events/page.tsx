"use client";
import React, { useState } from "react";
import {
  ActionsHub,
  AddEventForm,
  FButton,
  FModal,
  FConfirmAction,
} from "@/components";
import { EventTypes } from "@/interfaces";
import { columnsForEvents, defaultEventValues } from "@/helpers";
import { useQuery } from "@tanstack/react-query";
import EventsService from "@/api/springboot/events";
import { Table } from "antd";
import "./index.scss";
import UpdateEventForm from "@/components/UpdateEventForm";

const EventsPage = () => {
  const { data, isLoading, error } = useQuery({
    queryKey: ["events"],
    queryFn: () => EventsService.getAllEvents(),
  });

  console.log("rafa", data);

  const [showAddEventModal, setShowAddEventModal] = useState<boolean>(false);
  const [showDeleteEventModal, setShowDeleteEventModal] =
    useState<boolean>(false);
  const [showUpdateEventModal, setShowUpdateEventModal] =
    useState<boolean>(false);
  const [selectedEvent, setSelectedEvent] =
    useState<EventTypes>(defaultEventValues);

  const columns = [
    ...columnsForEvents,
    {
      title: "Acciones",
      key: "action",
      render: (_: any, record: EventTypes) => (
        <ActionsHub
          onDelete={() => handleDeleteEvent(record)}
          onEdit={() => handleUpdateEvent(record)}
        />
      ),
    },
  ];

  const handleDeleteEvent = (event: EventTypes) => {
    setSelectedEvent(event);
    setShowDeleteEventModal(true);
  };

  const handleUpdateEvent = (event: EventTypes) => {
    setSelectedEvent(event);
    setShowUpdateEventModal(true);
  };

  const handleInfoEvent = (event: EventTypes) => {
    setSelectedEvent(event);
    setShowUpdateEventModal(true);
  };

  if (isLoading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Error al obtener la data</p>;
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
        dataSource={data?.map((event: EventTypes) => ({
          ...event,
          key: event.id,
        }))}
        columns={columns}
      />
      <FModal
        title="Agregar evento"
        isOpen={showAddEventModal}
        onClose={() => setShowAddEventModal(false)}
        maxWidth={500}
        content={<AddEventForm onSubmit={() => setShowAddEventModal(false)} />}
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
      <FModal
        title="Actualizar evento"
        isOpen={showUpdateEventModal}
        onClose={() => setShowUpdateEventModal(false)}
        maxWidth={500}
        content={
          <UpdateEventForm
            initialValues={selectedEvent}
            onSubmit={() => setShowUpdateEventModal(false)}
          />
        }
      />
    </>
  );
};

export default EventsPage;
