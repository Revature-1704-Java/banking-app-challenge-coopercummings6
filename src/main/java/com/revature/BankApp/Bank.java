package com.revature.BankApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
 * Main method, handles banking
 */
public class Bank 
{
	static Scanner in = new Scanner(System.in);
    @SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
        ArrayList<Account> accounts = new ArrayList<Account>();
        System.out.println("Please enter the filename and path for accounts to be read from and/or wrote to.");
        String accountsLocation = in.nextLine();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(accountsLocation)))
        {
        	if(ois.readObject() instanceof ArrayList<?>)
        		accounts = (ArrayList<Account>)ois.readObject();//read accounts in from file
        }
        catch (Exception e)
        {
        	//if there was an exception, then the accounts file could not be accessed, so proceed as if there
        	//are no accounts
        }
        
        int option = 0;
        while(option != 6)
        {
        	//output text to describe options
        	System.out.println("Enter a number to select an action:");
        	System.out.println("1: Create an account");
        	System.out.println("2: Deposit to an account");
        	System.out.println("3: Withdraw from an account");
        	System.out.println("4: List accounts");
        	System.out.println("5: Clear accounts");
        	System.out.println("6: Save accounts and exit");
        	//retrieve selection from user
        	option = in.nextInt();
        	
        	//do things based on option selected
        	switch (option)
        	{
        	case 1:
        		//create a new account
        		createAccount(accounts);//calls a static method to condense code
        		break;
        	case 2:
        		//deposit to an account
        		makeDeposit(accounts);
        		break;
        	case 3:
        		//withdraw from an account
        		makeWithdrawl(accounts);
        		break;
        	case 4:
        		for(Account account : accounts)
        		{
        			System.out.print("Account Number: " + account.getAccountNumber() + " has balance $");
        			System.out.printf("%.1f", account.getBalance());
        			System.out.println(4);
        		}
        		break;
        	case 5:
        		//clear accounts
        		accounts.clear();
        		break;
        	default:
        		break;
        	}
        		
        }
        
        
        //write accounts to file
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(accountsLocation)))
        {
        	oos.writeObject(accounts);
        	System.out.println("accounts written successfully");
        }
        catch (Exception ex)
        {
        	System.out.println(ex.getClass());
        	System.out.println(ex.getMessage());
        }
    }
    
    public static void createAccount(ArrayList<Account> accounts)
    {
    	int newAccountNumber = accounts.size();
    	Account newAccount = new NonInterestAccount(accounts.size());
    	accounts.add(newAccount);
    	System.out.println("A new account has been created. It is account number: " + newAccountNumber);
    }
    
    public static void makeDeposit(ArrayList<Account> accounts)
    {
    	System.out.println("Enter the account number followed by the amount you would like to deposit.");
    	System.out.println("The amount should be in a dollars.cents format such as: 999.99");
    	int accNo = in.nextInt();
    	double amount = in.nextDouble();
    	
    	try
    	{
    		accounts.get(accNo).deposit(amount);
    		//this code will only execute if deposit does not throw an exception, meaning it was successful
    		System.out.print("Deposit completed, current balance: $");
    		System.out.printf("%.1f", accounts.get(accNo).getBalance());
    		System.out.println();
    	}
    	catch (NegativeAmountNotAllowedException e)
    	{
    		System.out.println("cannot withdraw a negative amount, withdrawl canceled.");
    	}
    }
    
    public static void makeWithdrawl(ArrayList<Account> accounts)
    {
    	System.out.println("Enter the account number followed by the amount you would like to withdraw.");
    	System.out.println("The amount should be in a dollars.cents format such as: 999.99");
    	int accNo = in.nextInt();
    	double amount = in.nextDouble();
    	
    	try
    	{
    		accounts.get(accNo).withdraw(amount);//this might throw an exception, the code following it will
    		//only run if the withdrawl is successful
    		System.out.print("Withdrawl completed, current balance: $");
    		System.out.printf("%.2f", accounts.get(accNo).getBalance());
    		System.out.println();
    	}
    	catch(NegativeAmountNotAllowedException e)
    	{
    		System.out.println("cannot withdraw a negative amount, withdrawl canceled.");
    	}
    	catch(WithdrawlAmountGreaterThanBalanceException e)
    	{
    		System.out.print("Attempted to withdraw more than the current balance of: $");
    		System.out.printf("%.1f", accounts.get(accNo).getBalance());
    		System.out.println();
    	}
    }
}

