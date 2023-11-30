"use client";
import AccountUserService from "@/api/springboot/account";
import { ActionsHub, FModal, UpdateAccountForm } from "@/components";
import { columnsForAccountsTable, defaultAccountUserValues } from "@/helpers";
import { AccountUserTypes } from "@/interfaces";
import { useQuery } from "@tanstack/react-query";
import { Table } from "antd";
import React, { useState } from "react";

const UsersPage = () => {
  const { data, isLoading, error } = useQuery({
    queryKey: ["accounts"],
    queryFn: () => AccountUserService.getAllAccounts(),
  });

  const [showUpdateAccount, setShowUpdateAccount] = useState<boolean>(false);

  const [selectedAccount, setSelectedAccount] = useState<AccountUserTypes>(
    defaultAccountUserValues
  );

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
      <Table
        pagination={{ pageSize: 10 }}
        dataSource={data?.map((account: AccountUserTypes) => ({
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