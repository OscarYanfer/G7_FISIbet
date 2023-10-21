package com.ms.gestionapuestas.infraestructure.port;

import com.ms.gestionapuestas.aplication.port.ConnectionPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
@Service
public class ConnectionMySQLPort implements ConnectionPort {
    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionStatus transaction;
    @Override
    public void begin() {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        this.transaction = this.transactionManager.getTransaction(definition);
    }

    @Override
    public void close() {
        //transaction.flush();
        System.out.println("ConnectionMySQLRepository_close" + this.transaction);
    }

    @Override
    public void commit() {
        System.out.println("ConnectionMySQLRepository_commit");
        this.transactionManager.commit(this.transaction);
    }

    @Override
    public void rollback() {
        System.out.println("ConnectionMySQLRepository_rollback_transaction" + this.transaction);
        if(this.transaction != null){
            System.out.println("ConnectionMySQLRepository_rollback_transactionManager" + this.transactionManager);
            this.transaction.setRollbackOnly();
        }
    }
}
