package br.com.fiap.proj_bank_lm.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.fiap.proj_bank_lm.model.Account;
import br.com.fiap.proj_bank_lm.model.AccountType;

public class AccountService {
    private LocalDate date = LocalDate.now();

  
       public boolean validationAccount(Account account) {
        if (requiredInputs(account)) return false;
        if (!checkDate(account.getOpenDate())) return false;
        if (!checkopeningBalance(account.getOpeningBalance())) return false;
        // if (!checkTypeAccount("corrente")) return false;
        return true;
    }


    public boolean requiredInputs(Account account){
        return account.getHolderName().isEmpty() || account.getCpf().isEmpty();
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

    public boolean checkTypeAccount(String type) {
        for (AccountType typeAccount : AccountType.values()){
            if (typeAccount.name().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    // com stream
    // public boolean checkTypeAccount(String type) {
    //     return java.util.Arrays.stream(TypeAccount.values())
    //              .anyMatch(t -> t.name().equalsIgnoreCase(type));
    // }
    
    

    public static void main(String[] args) {
        AccountService service = new AccountService();
        // BigDecimal valor = new BigDecimal(-540.55);
        // service.checkDate(LocalDate.of(2025,10,02));
        // System.out.println(service.checkopeningBalance(valor));
        // System.out.println(service.checkTypeAccount("corrente"));
        System.out.println(service.validationAccount(null));
        
    }
}
