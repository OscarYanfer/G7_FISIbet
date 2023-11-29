"use client";
import React, { useState } from "react";
import {
  ActionsHub,
  AddEventForm,
  FButton,
  FModal,
  FStatus,
} from "@/components";
import { EventTypes } from "@/interfaces";
import { formatDate, getStatusByNumber } from "@/helpers";
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

  const columns = [
    {
      title: "ID",
      dataIndex: "id",
      key: "id",
    },
    {
      title: "Equipo W1",
      dataIndex: "equipoA",
      key: "equipoA",
    },
    {
      title: "Equipo W2",
      dataIndex: "equipoB",
      key: "equipoB",
    },
    {
      title: "Liga",
      dataIndex: "liga",
      key: "liga",
    },
    {
      title: "Fecha",
      dataIndex: "fechaHora",
      key: "fechaHora",
    },
    {
      title: "Status",
      dataIndex: "status",
      key: "status",
      render: (_: any, { status }: { status: any }) => (
        <FStatus status={status} />
      ),
    },
    {
      title: "Creado en",
      dataIndex: "registeredOn",
      key: "registeredOn",
    },
    {
      title: "Ultima modificación en",
      dataIndex: "updateOn",
      key: "updateOn",
    },
    {
      title: "Acciones",
      key: "action",
      render: (_: any, record: any) => (
        <>
          <ActionsHub onInfo={() => console.log({ record })} />
        </>
      ),
    },
  ];

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
        dataSource={data?.map((event: EventTypes) => ({
          ...event,
          key: event.id,
          status: getStatusByNumber(event.status),
          fechaHora: formatDate(event.fechaHora),
          registeredOn: formatDate(event.registeredOn),
          updateOn: formatDate(event.updateOn || event.registeredOn),
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
    </>
  );
};

export default EventsPage;
