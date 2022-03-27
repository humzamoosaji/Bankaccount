package banking;

public class SavingsAccount extends BankAccount{
	
	
	public SavingsAccount(int accountNumber, String accountHolder, double openingBalance) {
		super(accountNumber,accountHolder,openingBalance);
	}
	
	public void deductFees() {
		withdraw(50);
		transactionCount = 0;
	}
	
	public boolean withdraw (double amount) {
		if (balance - amount > 500) {
			balance -= amount;
			return true;
		} else
			return false;
	}
	
	public void earnInterest() {
		final double interest = 0.015;
		deposit(balance*interest);
	}
}
