package collection_assesment;
	
	import java.util.*;

	class BankAccount {
	    private int accountNumber;
	    private String accountHolder;
	    private double balance;

	    public BankAccount(int accountNumber, String accountHolder, double initialBalance) {
	        this.accountNumber = accountNumber;
	        this.accountHolder = accountHolder;
	        this.balance = initialBalance;
	    }

	    public int getAccountNumber() {
	        return accountNumber;
	    }

	    public String getAccountHolder() {
	        return accountHolder;
	    }

	    public double getBalance() {
	        return balance;
	    }

	    public void deposit(double amount) {
	        balance += amount;
	        System.out.println("Deposit of $" + amount + " successful. New balance: $" + balance);
	    }

	    public void withdraw(double amount) {
	        if (balance >= amount) {
	            balance -= amount;
	            System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + balance);
	        } else {
	            System.out.println("Insufficient funds. Withdrawal not allowed.");
	        }
	    }

	    @Override
	    public String toString() {
	        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Balance: $" + balance;
	    }
	}

	public class BankApplication {
	    public static void main(String[] args) {
	        List<BankAccount> accounts = new ArrayList<>();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("1. Create Account");
	            System.out.println("2. Deposit");
	            System.out.println("3. Withdraw");
	            System.out.println("4. Check Balance");
	            System.out.println("5. List All Accounts");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter Account Number: ");
	                    int accountNumber = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Account Holder Name: ");
	                    String accountHolder = scanner.nextLine();
	                    System.out.print("Enter Initial Balance: ");
	                    double initialBalance = scanner.nextDouble();

	                    BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);
	                    accounts.add(account);
	                    System.out.println("Account created successfully!");
	                    break;
	                case 2:
	                    System.out.print("Enter Account Number: ");
	                    int depositAccountNumber = scanner.nextInt();
	                    System.out.print("Enter Deposit Amount: ");
	                    double depositAmount = scanner.nextDouble();

	                    BankAccount depositAccount = findAccount(accounts, depositAccountNumber);
	                    if (depositAccount != null) {
	                        depositAccount.deposit(depositAmount);
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;
	                case 3:
	                    System.out.print("Enter Account Number: ");
	                    int withdrawAccountNumber = scanner.nextInt();
	                    System.out.print("Enter Withdraw Amount: ");
	                    double withdrawAmount = scanner.nextDouble();

	                    BankAccount withdrawAccount = findAccount(accounts, withdrawAccountNumber);
	                    if (withdrawAccount != null) {
	                        withdrawAccount.withdraw(withdrawAmount);
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;
	                case 4:
	                    System.out.print("Enter Account Number: ");
	                    int checkBalanceAccountNumber = scanner.nextInt();

	                    BankAccount checkBalanceAccount = findAccount(accounts, checkBalanceAccountNumber);
	                    if (checkBalanceAccount != null) {
	                        System.out.println("Balance for Account " + checkBalanceAccount.getAccountNumber() + ": $" + checkBalanceAccount.getBalance());
	                    } else {
	                        System.out.println("Account not found.");
	                    }
	                    break;
	                case 5:
	                    System.out.println("List of Accounts:");
	                    for (BankAccount acc : accounts) {
	                        System.out.println(acc);
	                    }
	                    break;
	                case 6:
	                    System.out.println("Exiting the program.");
	                    scanner.close();
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice. Please enter a valid option.");
	            }
	        }
	    }

	    private static BankAccount findAccount(List<BankAccount> accounts, int accountNumber) {
	        for (BankAccount account : accounts) {
	            if (account.getAccountNumber() == accountNumber) {
	                return account;
	            }
	        }
	        return null;
	    }
	}



