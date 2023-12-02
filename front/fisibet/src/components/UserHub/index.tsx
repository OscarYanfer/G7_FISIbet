import React, { useEffect, useState } from "react";
import { FaCirclePlus, FaCircleMinus } from "react-icons/fa6";
import { FiLogOut } from "react-icons/fi";
import { CgFileDocument } from "react-icons/cg";
import { FaRegUserCircle } from "react-icons/fa";
import { useMutation, useQuery } from "@tanstack/react-query";
import AccountUserService from "@/api/springboot/account";
import { CircleButton, FButton, FModal } from "@/components";

import "./index.scss";
import { useDispatch } from "react-redux";
import { setToken } from "@/redux/actions/authTokenActions";

interface UserHubProps {
  userId: number;
}

const RefillForm = () => {
  const [refillValue, setRefillValue] = useState<string>("");
  const onChangeValue = (value: string) => {
    const regex =
      /^(2000(\.0{1,2})?|([1-9]\d{0,2}(\.\d{1,2})?|2000(\.0{1,2})?))$/;
    if (regex.test(value)) {
      setRefillValue(value);
    } else {
      setRefillValue("");
    }
  };
  return (
    <div className="refill--form">
      <div className="refill--input">
        <p>Digite el saldo a recargar:</p>
        <input
          onChange={(e) => onChangeValue(e.target.value)}
          value={refillValue}
          placeholder="S/."
          type="text"
        ></input>
      </div>
      <FButton disabled={refillValue === ""} type="primary" text="Pagar" />
    </div>
  );
};

const UserHub = ({ userId }: UserHubProps) => {
  const dispatch = useDispatch();
  const [showRefillModal, setShowRefillModal] = useState<boolean>(false);
  const { mutate: logoutUser, isPending } = useMutation({
    mutationFn: (username: string) =>
      AccountUserService.logoutAccount(username),
    onSuccess: () => {
      dispatch(setToken(null));
    },
  });
  const [id, setId] = useState<number>(userId);
  const { isLoading, error, data } = useQuery({
    queryKey: ["account", userId],
    queryFn: () => AccountUserService.getAccountById(id),
  });

  useEffect(() => {
    setId(userId);
  }, [userId]);

  console.log({ data });

  return (
    <>
      <div className="userhub--container">
        <CircleButton
          icon={<FaCirclePlus />}
          loading={isLoading}
          iconColor="primary"
          borderRadius="40px"
          onClick={() => setShowRefillModal(true)}
          type="quartery"
          text={`${(data?.wallet?.saldo || 0).toFixed(2)} PEN`}
        />
        <CircleButton
          icon={<FaCircleMinus />}
          loading={isLoading}
          iconColor="primary"
          borderRadius="40px"
          text="Retirar"
          type="quartery"
        />
        <CircleButton
          iconColor="primary"
          loading={isLoading}
          type="quartery"
          icon={<CgFileDocument />}
        />
        <CircleButton
          iconColor="primary"
          loading={isLoading}
          type="quartery"
          icon={<FaRegUserCircle />}
        />
        <FButton
          isLoading={isPending}
          onClick={() => logoutUser(data?.username)}
          border={false}
          icon={<FiLogOut />}
          type="primary"
        />
      </div>
      <FModal
        maxWidth={400}
        onClose={() => setShowRefillModal(false)}
        isOpen={showRefillModal}
        content={<RefillForm />}
        title="Recargar saldo"
      />
    </>
  );
};

export default UserHub;
