package com.bank;

import java.math.BigDecimal;

public class AcceptanceTest {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.deposit(BigDecimal.valueOf(1000));
        account.withdraw(BigDecimal.valueOf(200));
        account.deposit(BigDecimal.valueOf(500));

        account.printStatement();
    }
}