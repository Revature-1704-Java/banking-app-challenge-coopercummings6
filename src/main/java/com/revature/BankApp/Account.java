/**
 * 
 */
package com.revature.BankApp;

import java.io.Serializable;

/**
 * @author Cooper Cummings
 *	abstract class for bank accounts. account number is the index in the ArrayList holding all the
 *	accounts.
 */
public abstract class Account implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double balance = 0;
	private int accNo;
	
	public int getAccountNumber() {return accNo;}
	protected void setAccountNumber(int accountNumber) {accNo = accountNumber;}
	public double getBalance() {return balance;}
	
	public void deposit(double amount)throws NegativeAmountNotAllowedException
	{
		if(amount < 0)
		{
			throw new NegativeAmountNotAllowedException();
		}
		else
		{
			balance += amount;
		}
	}
	
	public void withdraw(double amount) throws WithdrawlAmountGreaterThanBalanceException, NegativeAmountNotAllowedException
	{
		if(amount > balance)
		{
			throw new WithdrawlAmountGreaterThanBalanceException();
		}
		else if(amount < 0)
		{
			throw new NegativeAmountNotAllowedException();
		}
		else
		{
			balance -= amount;
		}
	}
	
	public abstract void calcInterest();
}


//exception for attempting to withdraw more than the balance of an account.
class WithdrawlAmountGreaterThanBalanceException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

//exception for attempting to use a negative amount where negative amounts are not allowed
class NegativeAmountNotAllowedException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}