package banking;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
    	File accountsLoad = new File("accounts.txt");
    	File transactions = new File("transactions.txt");
    	Scanner sc = new Scanner(accountsLoad);
    	ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
    	sc.nextLine();
    	
    	//Load account from text file.
    	while(sc.hasNextLine()) {
    		int accNum = sc.nextInt();
    		String accType = sc.next();
    		String accHol = sc.next() + " " + sc.next();
    		String opBal = sc.next();
    		if (accType.equals("Current")) {
    			CurrentAccount tempAcc = new CurrentAccount(accNum, accHol, Double.parseDouble(opBal));
    			accounts.add(tempAcc);
    		}
    		else {
    			SavingsAccount tempAcc = new SavingsAccount(accNum, accHol, Double.parseDouble(opBal));
    			accounts.add(tempAcc);
    		}
    	}
    	sc.close();
    	//Carry out Transactions
    	Scanner sc1 = new Scanner(transactions);
    	sc1.nextLine();
    	while(sc1.hasNextLine()) {
    		int day = sc1.nextInt();
    		int month = sc1.nextInt();
    		int year = sc1.nextInt();
    		int accNum = sc1.nextInt();
    		String transactionType = sc1.next();
    		int intTransactionType;
    		if (transactionType.equals("withdraw")) {
    			intTransactionType = 0;
    		}
    		else if (transactionType.equals("deposit")) {
    			intTransactionType = 1;
    		}
    		else {
    			intTransactionType = 2;
    		}
    		String amount = sc1.next();
    		BankAccount toAcc = null;
    		if (transactionType.equals("transfer")) {
    			int toAccNum = sc1.nextInt(); 
    			for (int x = 0; x < accounts.size(); x++) {
    				if (toAccNum == accounts.get(x).getAccountNumber()) {
    					toAcc = accounts.get(x);    				
    					break;
    				}
    			}
    		}
    		for (int i = 0; i < accounts.size(); i++) {
    			if (accNum == accounts.get(i).getAccountNumber()) {
    				Transaction t = new Transaction(day, month, year, intTransactionType, toAcc, Double.parseDouble(amount));
    				accounts.get(i).processTransaction(t);
    			}
    		}
    	}
    	sc1.close();
    	for (int i = 0; i < accounts.size(); i++) {
    		accounts.get(i).printStatement();
    	}
    }
}
