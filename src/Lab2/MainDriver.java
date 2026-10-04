package Lab2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount[] accounts = new BankAccount[3];

        // accepts 3 accounts
        for (int i = 0; i < accounts.length; i++) {
            boolean created = false;
            while (!created) {
                try {
                    System.out.println("\n--- Creating Account " + (i + 1) + " ---");
                    
                    System.out.print("Enter 9-digit Account Number: ");
                    String accNum = scanner.nextLine();

                    System.out.print("Enter Account Holder Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    accounts[i] = new BankAccount(accNum, name, balance);
                    created = true;
                    System.out.println("Account created successfully: " + accounts[i]);

                } catch (InputMismatchException e) {
                    System.out.println("Error: Balance must be a numeric value.");
                    scanner.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                } finally {
                    System.out.println("Account creation transaction has been processed.");
                }
            }
        }

        //  deposit & withdrawal
        for (int i = 0; i < accounts.length; i++) {
            BankAccount acc = accounts[i];
            System.out.println("\n--- Transactions for Account: " + acc.getAccountNumber() + " ---");

            // Deposit transaction
            try {
                System.out.print("Enter deposit amount: ");
                double depAmount = scanner.nextDouble();
                acc.deposit(depAmount);
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid numeric input.");
                scanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Deposit transaction has been processed.");
            }

            // Withdrawal transaction
            try {
                System.out.print("Enter withdrawal amount: ");
                double withdrawAmount = scanner.nextDouble();
                acc.withdraw(withdrawAmount);
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid numeric input.");
                scanner.nextLine();
            } catch (IllegalArgumentException | InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("Withdrawal transaction has been processed.");
            }

            scanner.nextLine();
        }
        
        System.out.println("\n=== FINAL ACCOUNT SUMMARY ===");
        for (BankAccount acc : accounts) {
            System.out.println(acc);
        }

        scanner.close();
        System.out.println("\nProgram completed.");
    }
}