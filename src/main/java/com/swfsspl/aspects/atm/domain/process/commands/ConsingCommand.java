package com.swfsspl.aspects.atm.domain.process.commands;

import com.swfsspl.aspects.atm.domain.Account;
import com.swfsspl.aspects.atm.domain.Bank;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("ConsingCommand")
public class ConsingCommand implements ICommand {

    @Override
    public String getName() {
        return "Consignar dinero";
    }

    @Override
    public void execute(Bank context) throws Exception {

        System.out.println("Consignación de Dinero");
        System.out.println();
        Scanner console = new Scanner(System.in);

        System.out.println("Ingrese el número de cuenta");
        String accountNumber = console.nextLine();

        Account account = context.searchAccountByNumber(accountNumber);
        if (account == null) {
            throw new Exception("No existe cuenta con el número " + accountNumber);
        }

        System.out.println("Ingrese el valor a consignar");
        String inputAmount = console.nextLine();

        try {
            long amount = Long.parseLong(inputAmount);
            account.consign(amount);

        } catch (NumberFormatException e) {
            throw new Exception("Valor a consignar no válido : " + inputAmount);
        }
    }
}
