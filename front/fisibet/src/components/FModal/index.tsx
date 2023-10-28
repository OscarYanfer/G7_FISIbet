"use client";
import React, { useRef } from "react";
import { motion, AnimatePresence } from "framer-motion";
import { useOnClickOutside } from "@/hooks/useClickOutside";
import { MdOutlineClose } from "react-icons/md";
import "./index.scss";

interface FModalProps {
  content: React.ReactNode;
  isOpen: boolean;
  onClose(): void;
  maxWidth?: number;
}

const FModal = ({ isOpen, onClose, content, maxWidth }: FModalProps) => {
  const outSideModalRef = useRef() as React.MutableRefObject<HTMLDivElement>;
  useOnClickOutside(outSideModalRef, onClose);
  return (
    <AnimatePresence>
      {isOpen && (
        <>
          <motion.div
            className="modal--container--mask"
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            transition={{ duration: 0.1, delay: 0.1 }}
            exit={{ opacity: 0 }}
          >
            <div className="modal--main--container">
              <motion.div
                initial={{ y: 10, opacity: 0 }}
                animate={{ y: 0, opacity: 1 }}
                exit={{ y: -10, opacity: 0 }}
                transition={{ duration: 0.2, delay: 0.1 }}
                ref={outSideModalRef}
                style={{ maxWidth: maxWidth + "px" }}
                className="modal--content--container"
              >
                <div className="modal--close--button" onClick={onClose}>
                  <MdOutlineClose />
                </div>
                {content}
              </motion.div>
            </div>
          </motion.div>
        </>
      )}
    </AnimatePresence>
  );
};

export default FModal;
