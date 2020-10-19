package com.swfsspl.aspects.atm.domain.process.commands;

import com.swfsspl.aspects.atm.domain.Account;
import com.swfsspl.aspects.atm.domain.Bank;
import org.springframework.stereotype.Component;

@Component("ListAccountsCommand")
public class ListAccountsCommand implements ICommand {
    @Override
    public String getName() {
        return "Listar Cuentas";
    }

    @Override
    public void execute(Bank context) throws Exception {
        System.out.println("Listado de Cuentas");
        System.out.println();

        for (Account account : context.getAccounts()) {
            System.out.println(account.getNumber() + " : $ " + account.getBalance());
        }

    }
}
