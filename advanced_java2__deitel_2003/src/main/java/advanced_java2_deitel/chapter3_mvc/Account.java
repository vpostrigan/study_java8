package advanced_java2_deitel.chapter3_mvc;

import java.util.Observable;

public class Account extends Observable {
    private String name;
    private double balance;

    public Account(String name, double openingDeposit) {
        this.name = name;
        this.balance = openingDeposit;
    }

    // снятие со счета
    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot withdraw negative amount");

        setBalance(getBalance() - amount);
    }

    // начисление суммы на счет
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot deposit negative amount");

        setBalance(getBalance() + amount);
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    private void setBalance(double accountBalance) {
        balance = accountBalance;
        setChanged();
        notifyObservers();
    }

}
