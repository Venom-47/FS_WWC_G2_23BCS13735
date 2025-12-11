// Day - 1
// Que - 1

// Create a Java program that models a simple banking system using OOP. 
// Define a BankAccount class with private fields for account number, 
// account holder name, and balance, along with methods to deposit, withdraw, 
// and print account details. Then create a subclass SavingsAccount that adds 
// an interest rate and overrides the withdraw() method to prevent withdrawing
//  more than the available balance. In your Main class, create objects of both 
// classes, perform deposits and withdrawals, apply interest to the savings account, 
// and display the final account details.

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

class BankAccount {
    private long accNo;
    private String name;
    protected double balance;

    BankAccount(long accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited is " + amount);
        System.out.println("Currect balance: " + balance);
    }

    void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Not sufficent balance");
        }
        balance -= amount;
        System.out.println("Amount withdrawl is " + amount);
        System.out.println("Currect balance: " + balance);
    }

    void printAccoutDetails() {
        System.out.println("Name: " + name);
        System.out.println("Account number: " + accNo);
        System.out.println("Currect balance: " + balance);
    }
}

class SavingsAccount extends BankAccount {
    private double interest;

    SavingsAccount(String name, double balance, long accNo, double interest) {
        super(accNo, name, balance);
        this.interest = interest;
    }

    @Override
    void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Not sufficent balance");
        }
        balance -= amount;
        System.out.println("Amount withdrawl is " + amount);
        System.out.println("Currect balance: " + balance);
    }

    void applyInteres() {
        double inte = balance * (interest / 100);
        balance += inte;
        System.out.println("Interest amount is: " + inte);
        System.out.println("Current balance: " + balance);
    }

}

public class Task1 {
    public static void main(String[] args) {
        BankAccount obj1 = new BankAccount(1234123412341234L, "superman", 1000);
        SavingsAccount obj2 = new SavingsAccount("batman", 1000, 12341231412343425L, 12);
        obj1.printAccoutDetails();
        obj1.deposit(20);
        try {
            obj1.withdraw(30);
            obj1.withdraw(200000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        obj2.printAccoutDetails();
        try {
            obj2.withdraw(20);
            obj2.withdraw(200000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}