"use client";
import AccountUserService from "@/api/springboot/account";
import {
  ActionsHub,
  FModal,
  FSearch,
  FSelect,
  UpdateAccountForm,
} from "@/components";
import {
  columnsForAccountsTable,
  defaultAccountUserValues,
  intersectionArrays,
} from "@/helpers";
import { AccountUserTypes } from "@/interfaces";
import { useQuery } from "@tanstack/react-query";
import { Table } from "antd";
import React, { useState } from "react";
import "./index.scss";

const UsersPage = () => {
  const { data, isLoading, error } = useQuery({
    queryKey: ["accounts"],
    queryFn: () => AccountUserService.getAllAccounts(),
  });

  const [showUpdateAccount, setShowUpdateAccount] = useState<boolean>(false);
  const [selectedAccount, setSelectedAccount] = useState<AccountUserTypes>(
    defaultAccountUserValues
  );
  const [selectedFilter, setSelectedFilter] = useState<string>("username");
  const [selectedStatus, setSelectedStatus] = useState<string>("");
  const [query, setQuery] = useState<string>("");

  const filterByQuery = () => {
    if (query) {
      const dataForQuery = data.filter((account: AccountUserTypes) =>
        String(account[selectedFilter])
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
        (account: AccountUserTypes) => account.status === Number(selectedStatus)
      );
      return dataForStatus;
    } else {
      return data;
    }
  };
  const filteredData = () => {
    console.log("query", filterByQuery());
    console.log("status", filterByStatus());
    return intersectionArrays([filterByQuery(), filterByStatus()]);
  };

  const columns = [
    ...columnsForAccountsTable,
    {
      title: "Acciones",
      key: "action",
      render: (_: any, record: AccountUserTypes) => (
        <ActionsHub onEdit={() => handleEditAccount(record)} />
      ),
    },
  ];
  const handleEditAccount = (record: AccountUserTypes) => {
    setSelectedAccount(record);
    setShowUpdateAccount(true);
  };

  if (isLoading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Ha ocurrido un error</p>;
  }

  return (
    <div>
      <div className="user--page--admin--filters">
        <FSelect
          value={selectedFilter}
          onChange={(value) => setSelectedFilter(value)}
          options={[
            { label: "Usuario", value: "username" },
            { label: "Id", value: "id" },
            { label: "Correo", value: "email" },
            { label: "DNI", value: "dni" },
          ]}
        />
        <FSearch
          placeholder={`Buscar por ${selectedFilter}`}
          value={query}
          onChange={(value) => setQuery(value)}
        />
        <FSelect
          options={[
            { label: "Habilitado", value: "1" },
            { label: "Deshabilitado", value: "2" },
          ]}
          value={selectedStatus}
          onChange={(value) => setSelectedStatus(value)}
          defaultValue="---Filtrar por estado ---"
        />
      </div>
      <Table
        pagination={{ pageSize: 10 }}
        dataSource={filteredData()?.map((account: AccountUserTypes) => ({
          ...account,
          key: account.id,
        }))}
        columns={columns}
      />
      <FModal
        title="Actualizar información"
        isOpen={showUpdateAccount}
        onClose={() => setShowUpdateAccount(false)}
        maxWidth={500}
        content={
          <UpdateAccountForm
            onSubmit={() => setShowUpdateAccount(false)}
            initialValues={selectedAccount}
          />
        }
      />
    </div>
  );
};

export default UsersPage;
