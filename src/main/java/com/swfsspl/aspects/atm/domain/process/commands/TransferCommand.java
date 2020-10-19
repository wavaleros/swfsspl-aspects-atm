package com.swfsspl.aspects.atm.domain.process.commands;

import com.swfsspl.aspects.atm.domain.Account;
import com.swfsspl.aspects.atm.domain.Bank;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("TransferCommand")
public class TransferCommand implements ICommand {
    @Override
    public String getName() {
        return "Transferir dinero";
    }

    @Override
    public void execute(Bank context) throws Exception {
        System.out.println("Transferencia de Dinero");
        System.out.println();

        Scanner console = new Scanner(System.in);

        System.out.println("Ingrese el número de cuenta origen");
        String originAccountNumber = console.nextLine();

        Account originAccount = context.searchAccountByNumber(originAccountNumber);
        if (originAccount == null) {
            throw new Exception("No existe cuenta con el número " + originAccountNumber);
        }

        System.out.println("Ingrese el número de cuenta destino");
        String destinyAccountNumber = console.nextLine();

        Account destinyAccount = context.searchAccountByNumber(destinyAccountNumber);
        if (destinyAccount == null) {
            throw new Exception("No existe cuenta con el número " + destinyAccountNumber);
        }

        System.out.println("Ingrese el valor a transferir");
        String amountInput = console.nextLine();

        try {
            long amount = Long.parseLong(amountInput);
            originAccount.withdraw(amount);
            destinyAccount.consign(amount);

        } catch (NumberFormatException e) {
            throw new Exception("Valor a transferir no válido : " + amountInput);
        }

    }
}
