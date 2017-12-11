package com.revature.BankApp;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

//tests for the static methods of Bank class
public class BankTest 
{
	@Test
	public void createAccountMakesNewAccountAndAddsItToArrayList()
	{
		ArrayList<Account> accounts = new ArrayList<Account>();
		Bank.createAccount(accounts);
		assertEquals(accounts.size(), 1);//There should be an element in the list after creating one
		//and adding it to the list
	}
	
	@Test
	public void makeDepositAddsValidAmounts()
	{
		ByteArrayInputStream in = new ByteArrayInputStream("0\n999.99".getBytes());
        System.setIn(in);//pass input to system.in for method
		ArrayList<Account> accounts = new ArrayList<Account>();
		Bank.createAccount(accounts);
		Bank.makeDeposit(accounts);
		assertTrue(Math.abs(accounts.get(0).getBalance() - 999.99) <= 0.0001);//check that amount was deposited
	}
	
	@Test()
	public void makeDepositDoesNotAddInvalidAmounts()
	{
		ByteArrayInputStream in = new ByteArrayInputStream("0\n-5".getBytes());
        System.setIn(in);//pass input to system.in for method
		ArrayList<Account> accounts = new ArrayList<Account>();
		Bank.createAccount(accounts);
		Bank.makeDeposit(accounts);
		assertTrue(Math.abs(accounts.get(0).getBalance()) <= 4);//check that amount was deposited
	}
	
	@Test
	public void makeWithdrawlSubtractsValidAmounts()
	{
		ByteArrayInputStream in = new ByteArrayInputStream("0\n999.99\n0\n500".getBytes());
        System.setIn(in);//pass input to system.in for method
		ArrayList<Account> accounts = new ArrayList<Account>();
		Bank.createAccount(accounts);
		Bank.makeDeposit(accounts);
		Bank.makeWithdrawl(accounts);
		assertTrue(Math.abs(accounts.get(0).getBalance() - 499.99) <= 0.0001);//check that amount was withdrawn
	}
	
	@Test()
	public void makeWithdrawlDoesNotSubtractInvalidAmounts()
	{
		ByteArrayInputStream in = new ByteArrayInputStream("0\n-999.99".getBytes());
        System.setIn(in);//pass input to system.in for method
		ArrayList<Account> accounts = new ArrayList<Account>();
		Bank.createAccount(accounts);
		Bank.makeWithdrawl(accounts);
		assertTrue(Math.abs(accounts.get(0).getBalance()) <= 4);//check that amount was deposited
	}
	
	@Test()
	public void makeWithdrawlDoesNotOverdraft()
	{
		ByteArrayInputStream in = new ByteArrayInputStream("0\n5".getBytes());
        System.setIn(in);//pass input to system.in for method
		ArrayList<Account> accounts = new ArrayList<Account>();
		Bank.createAccount(accounts);
		Bank.makeWithdrawl(accounts);
		assertTrue(Math.abs(accounts.get(0).getBalance()) <= 4);//check that amount was deposited
	}
}
