package com.revature.BankApp;

import org.junit.*;
import static org.junit.Assert.*;


//tests for the Account abstract class methods, which are inherited by NonInterestAccount (the only abstract method implemented as a stub)
public class NonInterestAccountTest 
{
	@Test
	public void getAccountNumberReturnsExpectedValue()
	{
		//this also tests setAccountNumber because it is used in the constructor to initialize the value
		Account test = new NonInterestAccount(555);
		assertEquals(test.getAccountNumber(), 555);
	}
	
	@Test
	public void getBalanceReturnsExpectedValue()
	{
		Account test = new NonInterestAccount(555);
		assertTrue(test.getBalance() == 0);
	}
	
	@Test(expected = NegativeAmountNotAllowedException.class)
	public void depositingNegativeAmountsThrowsException()
	{
		Account test = new NonInterestAccount(555);
		test.deposit(-2);
	}
	
	@Test
	public void depositingPositiveAmountsAddsToBalance()
	{
		Account test = new NonInterestAccount(555);
		test.deposit(999.99);
		assertTrue(Math.abs(999.99 - test.getBalance()) <= 0.0001);//doubles have a margin of error, can't just compare with == and get expected results
	}
	
	@Test(expected = NegativeAmountNotAllowedException.class)
	public void withdrawingNegativeAmountsThrowsException()
	{
		Account test = new NonInterestAccount(555);
		test.withdraw(-500.00);
	}
	
	@Test(expected = WithdrawlAmountGreaterThanBalanceException.class)
	public void withdrawingMoreThanBalanceThrowsException()
	{
		Account test = new NonInterestAccount(555);
		test.withdraw(1);
	}
	
	@Test
	public void withdrawingRemovesExpectedAmount()
	{
		Account test = new NonInterestAccount(555);
		test.deposit(500.00);
		test.withdraw(400.00);
		assertTrue(Math.abs(test.getBalance() - 100 ) <= 0.0001 );
	}
}
