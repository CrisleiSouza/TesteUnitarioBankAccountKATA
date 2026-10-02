package com.bank;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BankAccountParameterizedTest {

    private BankAccount account = new BankAccount();

    @ParameterizedTest(name = "Depósito de {0} deve resultar em saldo de {0}")
    @ValueSource(strings = {
        "100",
        "500",
        "0.50",
        "2500"
    })
    void should_deposit_money(String valor) {

        BigDecimal amount = new BigDecimal(valor);

        account.deposit(amount);

        assertEquals(amount, account.getBalance());
    }

    @ParameterizedTest(name = "Saldo inicial {0} - saque {1} = {2}")
    @CsvSource({
        "1000, 100, 900",
        "1000, 300, 700",
        "1000, 500, 500",
        "500, 100, 400",
        "2000, 750, 1250"
    })
    void should_withdraw_money(
            String saldoInicial,
            String saque,
            String saldoEsperado) {

        account.deposit(new BigDecimal(saldoInicial));
        account.withdraw(new BigDecimal(saque));

        assertEquals(
            new BigDecimal(saldoEsperado),
            account.getBalance()
        );
    }

    @ParameterizedTest(name = "Depósito inválido de {0} deve lançar exceção")
    @ValueSource(strings = {
        "0",
        "-1",
        "-100",
        "-500"
    })
    void should_not_allow_invalid_deposit(String valor) {

        assertThrows(
            IllegalArgumentException.class,
            () -> account.deposit(new BigDecimal(valor))
        );
    }

    @ParameterizedTest(name = "Saque inválido de {0} deve lançar exceção")
    @ValueSource(strings = {
        "0",
        "-1",
        "-100",
        "-500"
    })
    void should_not_allow_invalid_withdrawal(String valor) {

        assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(new BigDecimal(valor))
        );
    }

    @ParameterizedTest(name = "Depósito {0} + saque {1} deve resultar em {2}")
    @CsvSource({
        "1000, 200, 800",
        "500, 100, 400",
        "2000, 500, 1500",
        "10000, 2500, 7500"
    })
    void should_calculate_balance_after_transaction(
            String deposito,
            String saque,
            String saldoEsperado) {

        account.deposit(new BigDecimal(deposito));
        account.withdraw(new BigDecimal(saque));

        assertEquals(
            new BigDecimal(saldoEsperado),
            account.getBalance()
        );
    }

    @ParameterizedTest(name = "Após depósito de {0}, deve existir 1 transação")
    @ValueSource(strings = {
        "100",
        "500",
        "1000"
    })
    void should_store_deposit_transaction(String valor) {

        account.deposit(new BigDecimal(valor));

        assertEquals(1, account.getTransactions().size());
    }

    @ParameterizedTest(name = "Após depósito e saque, devem existir 2 transações")
    @CsvSource({
        "1000, 100",
        "500, 200",
        "2000, 500"
    })
    void should_store_multiple_transactions(
            String deposito,
            String saque) {

        account.deposit(new BigDecimal(deposito));
        account.withdraw(new BigDecimal(saque));

        assertEquals(2, account.getTransactions().size());
    }

    @ParameterizedTest(name = "Depósito de {0} deve criar transação com valor {0}")
    @ValueSource(strings = {
        "100",
        "500",
        "1000"
    })
    void should_store_deposit_amount(String valor) {

        BigDecimal amount = new BigDecimal(valor);

        account.deposit(amount);

        assertEquals(
            amount,
            account.getTransactions().get(0).getAmount()
        );
    }

    @ParameterizedTest(name = "Saque de {0} deve criar transação negativa")
    @ValueSource(strings = {
        "100",
        "200",
        "500"
    })
    void should_store_withdrawal_as_negative_amount(String valor) {

        BigDecimal amount = new BigDecimal(valor);

        account.deposit(new BigDecimal("1000"));
        account.withdraw(amount);

        assertEquals(
            amount.negate(),
            account.getTransactions().get(1).getAmount()
        );
    }

    @ParameterizedTest(name = "Saldo final após depósito {0} e saque {1} deve ser {2}")
    @CsvSource({
        "1000, 100, 900",
        "1000, 250, 750",
        "1000, 1000, 0",
        "5000, 1250, 3750"
    })
    void should_allow_withdrawal_up_to_balance(
            String deposito,
            String saque,
            String saldoEsperado) {

        account.deposit(new BigDecimal(deposito));
        account.withdraw(new BigDecimal(saque));

        assertEquals(
            new BigDecimal(saldoEsperado),
            account.getBalance()
        );
    }
}
