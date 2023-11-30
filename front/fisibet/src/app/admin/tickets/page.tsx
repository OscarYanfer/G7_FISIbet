"use client";
import React from "react";
import { Table } from "antd";
import { TicketTypes } from "@/interfaces";
import { columnsForTickets } from "@/helpers";
import { ActionsHub } from "@/components";
import { useQuery } from "@tanstack/react-query";
import TicketsService from "@/api/springboot/tickets";
import "./index.scss";

const TicketsPage = () => {
  const { data, isLoading, error } = useQuery({
    queryKey: ["tickets"],
    queryFn: () => TicketsService.getAllTickets(),
  });

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
  if (isLoading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Ha ocurrido un error</p>;
  }
  return (
    <div>
      <Table
        pagination={{ pageSize: 10 }}
        dataSource={data?.map((ticket: TicketTypes) => ({
          ...ticket,
          key: ticket.id,
        }))}
        columns={columns}
      />
    </div>
  );
};

export default TicketsPage;
