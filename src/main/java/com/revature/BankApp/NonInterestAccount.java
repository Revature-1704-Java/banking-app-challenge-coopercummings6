package com.revature.BankApp;

//this class exists to test the abstract Account class and the bank program, so the only implemented
//methods it will get are a constructor and a stub for its ancestor's abstract method.
public class NonInterestAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NonInterestAccount(int accountNumber) 
	{
		this.setAccountNumber(accountNumber);
	}

	@Override
	public void calcInterest() {
		// Stub, doesn't accrue interest

	}

}
