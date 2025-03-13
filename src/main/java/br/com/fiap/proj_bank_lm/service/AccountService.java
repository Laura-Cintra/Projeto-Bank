package br.com.fiap.proj_bank_lm.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.proj_bank_lm.model.Account;

public class AccountService {
    private LocalDate date = LocalDate.now();

    public boolean requiredInputs(Account account){
        return account.getHolderName().isEmpty() || account.getCpf() == 0;
    } 

    public boolean checkDate(LocalDate openDate){
       if(openDate.isAfter(date)){
        return false;
       }
       return true;
    }
    
    public boolean checkopeningBalance(BigDecimal openingBalance){
        BigDecimal zero = BigDecimal.ZERO;

        if (openingBalance.compareTo(zero) >= 0) {
            return true;
        }
        return false;
    }

    // public boolean typeValid(String typeAccount){
    //     TypeAccount typeAccount2;
    //     return typeAccount2.
    // }

    public static void main(String[] args) {
        AccountService service = new AccountService();
        BigDecimal valor = new BigDecimal(-540.55);
        service.checkDate(LocalDate.of(2025,10,02));
        System.out.println(service.checkopeningBalance(valor));
    }
}
