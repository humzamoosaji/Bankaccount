package banking;
import java.util.ArrayList;
/**
 * The BankAccount class represents a bank account and is meant mainly
 * to be sub-classed
 */
public abstract class BankAccount {
	private int accountNumber;
	private String accountHolder;
	protected double balance = 0;
	protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	protected int transactionCount =0;
	
	
	public BankAccount(int accountNumber, String accountHolder, double openingBalance){
		this.accountNumber = accountNumber;
		this.accountHolder= accountHolder;
		this.balance = openingBalance;
	}
	public String getAccountHolder() {
		return accountHolder;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public int getTransactionCount() {
		return transactionCount;
	}
	
	public double getBalance(){
		return balance;
	}
	
	
	public void deposit(double amount){
		balance += amount;
	}
	
	public void transferTo (BankAccount b, double amount){
		b.balance += amount;
	}
	
	public abstract void deductFees();
	
	public abstract boolean withdraw (double amount);
	
	public void processTransaction(Transaction t){
		this.transactions.add(t);
		switch (t.transactionType){
		case Transaction.WITHDRAW : if (! withdraw(t.amount)){
				t.cancelled = true;
			}
			    break;
		case Transaction.DEPOSIT : deposit(t.amount); 
								   break;
		case Transaction.TRANSFER : transferTo(t.toAccount, t.amount); 
		                            break;
		default: System.err.println("Wrong transaction type");
		}
	}
	
	public String toString(){
		return accountNumber + "has a balance of : " + balance;
	}
	
	public void printStatement(){
		System.out.println("-----------STATEMENT----------");
		System.out.println("Account Number: " + this.accountNumber);
		System.out.println("Mr " + this.accountHolder);
		System.out.println("Remaining funds: " + this.balance);
		System.out.println("---------TRANSACTIONS---------");
		for (int i = 0; i < this.transactions.size(); i++) {
			System.out.println(transactions.get(i).toString());
		}
		System.out.println("--------------END-------------");
		System.out.println();
		
	}
}
