package banking;

public class CurrentAccount extends BankAccount{
	
	public CurrentAccount(int accountNumber, String accountHolder, double openingBalance) {
		super(accountNumber,accountHolder,openingBalance);
	}
	
	public void deductFees() {
		final double transactionFee = 1.80;
		if (transactionCount > 5) {
			withdraw(transactionCount * transactionFee - 9);
			transactionCount = 0;
		}
		else
			transactionCount = 0;
	}
	
	public boolean withdraw (double amount) {
		if (balance - amount > 0.00) {
			balance -= amount;
			return true;
		} else
			return false;
	}
}
