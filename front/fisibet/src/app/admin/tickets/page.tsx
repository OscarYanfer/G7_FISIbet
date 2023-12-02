"use client";
import React, { useState } from "react";
import { Table } from "antd";
import { TicketTypes } from "@/interfaces";
import { columnsForTickets, intersectionArrays } from "@/helpers";
import { ActionsHub, FSearch, FSelect } from "@/components";
import { useQuery } from "@tanstack/react-query";
import TicketsService from "@/api/springboot/tickets";
import "./index.scss";

const TicketsPage = () => {
  const { data, isLoading, error } = useQuery({
    queryKey: ["tickets"],
    queryFn: () => TicketsService.getAllTickets(),
  });

  const [selectedFilter, setSelectedFilter] = useState<string>("id");
  const [selectedStatus, setSelectedStatus] = useState<string>("");
  const [query, setQuery] = useState<string>("");

  const columns = [
    ...columnsForTickets,
    {
      title: "Acciones",
      key: "action",
      render: (_: any, record: TicketTypes) => (
        <ActionsHub onInfo={() => console.log(record.id)} />
      ),
    },
  ];

  const filterByQuery = () => {
    if (query) {
      const dataForQuery = data.filter((ticket: TicketTypes) =>
        String(ticket[selectedFilter])
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
        (ticket: TicketTypes) => ticket.status === Number(selectedStatus)
      );
      return dataForStatus;
    } else {
      return data;
    }
  };
  const filteredData = () => {
    return intersectionArrays([filterByQuery(), filterByStatus()]);
  };

  if (isLoading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Ha ocurrido un error</p>;
  }
  return (
    <div>
      <div className="tickets--filter">
        <FSelect
          value={selectedFilter}
          onChange={(value) => setSelectedFilter(value)}
          options={[
            { label: "Id", value: "id" },
            { label: "Id de Usuario", value: "idAccountUser" },
          ]}
        />
        <FSearch
          placeholder={`Buscar por ${selectedFilter}`}
          value={query}
          onChange={(value) => setQuery(value)}
        />
        <FSelect
          options={[
            { label: "Pendiente", value: "1" },
            { label: "Concluido", value: "2" },
          ]}
          value={selectedStatus}
          onChange={(value) => setSelectedStatus(value)}
          defaultValue="---Filtrar por estado ---"
        />
      </div>

      <Table
        pagination={{ pageSize: 10 }}
        dataSource={filteredData()?.map((ticket: TicketTypes) => ({
          ...ticket,
          key: ticket.id,
        }))}
        columns={columns}
      />
    </div>
  );
};

export default TicketsPage;
