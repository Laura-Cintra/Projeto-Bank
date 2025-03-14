package br.com.fiap.proj_bank_lm.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.proj_bank_lm.model.Account;
import br.com.fiap.proj_bank_lm.model.AccountType;

public class AccountService {
    private LocalDate date = LocalDate.now();

    public boolean validationAccount(Account account) {
        if (requiredInputs(account))
            return false;
        if (!checkDate(account.getOpenDate()))
            return false;
        if (!checkBalance(account.getBalance()))
            return false;
        return true;
    }

    public void isActivateAccount(boolean accountType) {
        if(!accountType){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ação inválida, conta inativa.");
        }
    }

    public BigDecimal subtractBalance(BigDecimal balance, BigDecimal value){
        return balance.subtract(value);
    }

    public BigDecimal addBalance(BigDecimal balance, BigDecimal value){
        return balance.add(value);
    }

    public void checkValue(BigDecimal value) {
        if (!checkBalance(value)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valor deve ser maior que 0, valor: " + value);
        }
    }

    public boolean requiredInputs(Account account) {
        return account.getHolderName().isEmpty() || account.getCpf().isEmpty();
    }

    public boolean checkDate(LocalDate openDate) {
        if (openDate.isAfter(date)) {
            return false;
        }
        return true;
    }

    public boolean checkBalance(BigDecimal openingBalance) {
        BigDecimal zero = BigDecimal.ZERO;

        if (openingBalance.compareTo(zero) >= 0) {
            return true;
        }
        return false;
    }

    public boolean checkTypeAccount(String type) {
        for (AccountType typeAccount : AccountType.values()) {
            if (typeAccount.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AccountService service = new AccountService();
        // BigDecimal valor = new BigDecimal(-540.55);
        // service.checkDate(LocalDate.of(2025,10,02));
        // System.out.println(service.checkopeningBalance(valor));
        // System.out.println(service.checkTypeAccount("corrente"));
        System.out.println(service.validationAccount(null));

    }
}
