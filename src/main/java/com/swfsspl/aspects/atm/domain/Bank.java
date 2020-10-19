package com.swfsspl.aspects.atm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Bank {


    private Map<String, Account> accounts = new HashMap<>();
    private List<String> operationLogs = new ArrayList<>();

    public Bank() {
    }


    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    public Account searchAccountByNumber(String number) {
        return accounts.get(number);
    }

    public void addAccount(Account account) {
        accounts.put(account.getNumber(), account);
    }

}
