package br.com.fiap.proj_bank_lm.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.proj_bank_lm.model.Account;

@RestController
@RequestMapping("/account")
public class AccountController {
    List<Account> repository = new ArrayList<>();
    private Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Account create(@RequestBody Account account){
        log.info("Cadastrando a conta " + account.getAccountNumber());
        repository.add(account);
        return account;
    }
}
