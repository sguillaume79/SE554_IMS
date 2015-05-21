package edu.depaul.cdm.se.inventory.service;

import edu.depaul.cdm.se.inventory.exception.NegativeBalanceException;
import edu.depaul.cdm.se.inventory.exception.AccountNotFoundException;
import edu.depaul.cdm.se.inventory.exception.InsufficientBalanceException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

@Remote
public interface AccountServiceRemote {
    public long openAccount(String name, float initialBalance) throws NegativeBalanceException;

    public float deposit(long accountNumber, float amount)
            throws NegativeBalanceException, AccountNotFoundException;
    
    public float withdraw(long accountNumber, float amount)
            throws NegativeBalanceException, InsufficientBalanceException, AccountNotFoundException;

    public float close(long accountNumber)
            throws InsufficientBalanceException, AccountNotFoundException;
    

    public void transferFunds(long fromAccountNumber, long toAccountNumber, float amount)
            throws AccountNotFoundException, NegativeBalanceException, InsufficientBalanceException; 
    
    public List getAllAccounts();
}
