package Lab2;

public class BankAccount {
	
    String accountNumber;
    String accountHolderName;
    double balance;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance)
            throws IllegalArgumentException {
        if (accountNumber == null || accountNumber.length() != 9) {
            throw new IllegalArgumentException("Account Number must be exactly 9 digits.");
        }
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Account Holder Name cannot be blank.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName.trim();
        this.balance = initialBalance;
    }
    
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than 0.");
        }
        this.balance += amount;
        System.out.printf("Successfully deposited $%.2f. New Balance: $%.2f%n", amount, this.balance);
    }
    
    public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");            
        }
        if (amount > this.balance) {
            throw new InsufficientFundsException(
                String.format("Insufficient funds! Requested: $%.2f, Available: $%.2f", amount, this.balance)
            );
        }
        this.balance -= amount;
        System.out.printf("Successfully withdrew $%.2f. New Balance: $%.2f%n", amount, this.balance);
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Account [#%s | Holder: %s | Balance: $%.2f]", 
                accountNumber, accountHolderName, balance);
    }
}