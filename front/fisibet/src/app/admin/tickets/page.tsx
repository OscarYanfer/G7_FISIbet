"use client";
import React from "react";
import { Table } from "antd";
import { TicketTypes } from "@/interfaces";
import { formatDate, getStatusByNumber } from "@/helpers";
import { ActionsHub, FStatus } from "@/components";
import { useQuery } from "@tanstack/react-query";
import TicketsService from "@/api/springboot/tickets";
import "./index.scss";

const TicketsPage = () => {
  const { data, isLoading, error } = useQuery({
    queryKey: ["tickets"],
    queryFn: () => TicketsService.getAllTickets(),
  });

  const columsForTicketsTable = [
    { title: "Id", dataIndex: "id", key: "id" },
    {
      title: "Usuario apostador",
      dataIndex: "idAccountUser",
      key: "idAccountUser",
    },
    { title: "Monto apostado", dataIndex: "amountBet", key: "amountBet" },
    { title: "Ganancia potencial", dataIndex: "totalFee", key: "TotalFee" },
    {
      title: "Estado de la apuesta",
      dataIndex: "status",
      key: "status",
      render: (_: any, { status }: { status: any }) => (
        <FStatus status={status} />
      ),
    },
    {
      title: "Apuesta creada en",
      dataIndex: "registeredOn",
      key: "registeredOn",
    },
    {
      title: "Action",
      key: "action",
      render: (_: any, record: any) => (
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
        columns={columsForTicketsTable}
        dataSource={data?.map((ticket: TicketTypes) => ({
          ...ticket,
          key: ticket.id,
          status: getStatusByNumber(ticket.status),
          amountBet: `S/.${ticket.amountBet}`,
          totalFee: `S/.${ticket.totalFee}`,
          registeredOn: formatDate(ticket.registeredOn),
        }))}
      />
    </div>
  );
};

export default TicketsPage;
