"use client";
import React, { useState } from "react";
import {
  ActionsHub,
  AddEventForm,
  FButton,
  FModal,
  FConfirmAction,
  FSelect,
  FSearch,
} from "@/components";
import { EventTypes } from "@/interfaces";
import {
  columnsForEvents,
  defaultEventValues,
  intersectionArrays,
} from "@/helpers";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import EventsService from "@/api/springboot/events";
import { Table } from "antd";
import "./index.scss";
import UpdateEventForm from "@/components/UpdateEventForm";

const EventsPage = () => {
  const queryClient = useQueryClient();
  const { data, isLoading, error } = useQuery({
    queryKey: ["events"],
    queryFn: () => EventsService.getAllEvents(),
  });
  const { mutate: disableEvent, isPending } = useMutation({
    mutationFn: (id: number) => EventsService.disableEvent(id),
    onSuccess: () => {
      queryClient.invalidateQueries({
        predicate: (query) => query.queryKey.includes("events"),
      });
      setShowDeleteEventModal(false);
    },
  });

  console.log("rafa", data);

  const [showAddEventModal, setShowAddEventModal] = useState<boolean>(false);
  const [showDeleteEventModal, setShowDeleteEventModal] =
    useState<boolean>(false);
  const [showUpdateEventModal, setShowUpdateEventModal] =
    useState<boolean>(false);
  const [selectedEvent, setSelectedEvent] =
    useState<EventTypes>(defaultEventValues);
  const [selectedFilter, setSelectedFilter] = useState<string>("liga");
  const [selectedStatus, setSelectedStatus] = useState<string>("");
  const [query, setQuery] = useState<string>("");

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

  const filterByQuery = () => {
    if (query) {
      const dataForQuery = data.filter((event: EventTypes) =>
        String(event[selectedFilter])
          .toLocaleLowerCase()
          .includes(query.toLocaleLowerCase())
      );
      return dataForQuery;
    } else {
      return data;
    }
  };
  const filterByStatus = () => {
    if (selectedStatus) {
      const dataForStatus = data?.filter(
        (account: EventTypes) => account.status === Number(selectedStatus)
      );
      return dataForStatus;
    } else {
      return data;
    }
  };
  const filteredData = () => {
    return intersectionArrays([filterByQuery(), filterByStatus()]);
  };

  const handleDeleteEvent = (event: EventTypes) => {
    setSelectedEvent(event);
    setShowDeleteEventModal(true);
  };

  const handleUpdateEvent = (event: EventTypes) => {
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
    <div className="admin--events--page--main--container">
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
      <div className="event--page--admin--filters">
        <FSelect
          value={selectedFilter}
          onChange={(value) => setSelectedFilter(value)}
          options={[
            { label: "Liga", value: "liga" },
            { label: "Id", value: "id" },
          ]}
        />
        <FSearch
          placeholder={`Buscar por ${selectedFilter}`}
          value={query}
          onChange={(value) => setQuery(value)}
        />
        <FSelect
          options={[
            { label: "Abierto", value: "1" },
            { label: "Cerrado", value: "2" },
          ]}
          value={selectedStatus}
          onChange={(value) => setSelectedStatus(value)}
          defaultValue="---Filtrar por estado ---"
        />
      </div>
      <Table
        pagination={{ pageSize: 10 }}
        dataSource={filteredData()?.map((event: EventTypes) => ({
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
            isLoading={isPending}
            onReject={() => setShowDeleteEventModal(false)}
            onConfirm={() => disableEvent(selectedEvent.id)}
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
    </div>
  );
};

export default EventsPage;
