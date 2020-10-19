package com.swfsspl.aspects.atm;


import com.swfsspl.aspects.atm.domain.Account;
import com.swfsspl.aspects.atm.domain.Bank;
import com.swfsspl.aspects.atm.domain.process.commands.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ATM implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationContext applicationContext;

    @Autowired
    public ATM(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        Bank bank = new Bank();

        bank.addAccount(new Account("1", "clave", 1000));
        bank.addAccount(new Account("2", "clave", 2000));
        bank.addAccount(new Account("3", "clave", 3000));

        List<ICommand> commands = loadCommands();

        System.out.println("Cajero Automático");
        System.out.println("=================");
        System.out.println();

        boolean fin = false;
        do {
            showCommandsMenu(commands);
            System.out.println("X.- Salir");

            Scanner console = new Scanner(System.in);
            String input = console.nextLine();

            ICommand commandSelected = returnCommandSelected(commands, input);
            if (commandSelected != null) {
                try {
                    commandSelected.execute(bank);

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

            } else if (input.equalsIgnoreCase("X")) {
                fin = true;
            }

            System.out.println();
        } while (!fin);
        printLog(bank);

        System.out.println("Gracias por usar el programa.");
    }

    private void printLog(Bank bank) {
        System.out.println("###############Inicio - Listado de operaciones###############");
        System.out.println();
        bank.getOperationLogs().stream().forEach(x -> System.out.println(x));
        System.out.println("###############Fin - Listado de operaciones###############");
    }


    private List<ICommand> loadCommands() {
        List<ICommand> commands = new ArrayList<>();
        commands.add((ICommand) this.applicationContext.getBean("ListAccountsCommand"));
        commands.add((ICommand) this.applicationContext.getBean("TransferCommand"));
        commands.add((ICommand) this.applicationContext.getBean("ConsingCommand"));
        commands.add((ICommand) this.applicationContext.getBean("WithdrawCommand"));
        return commands;
    }


    private void showCommandsMenu(List<ICommand> commands) {
        for (int i = 0; i < commands.size(); i++) {
            System.out.println(i + ".-" + commands.get(i).getName());
        }
    }

    private static ICommand returnCommandSelected(List<ICommand> commands, String input) {
        ICommand selectedCommand = null;
        if (input.matches("[0-9]")) {
            int selectedValue = Integer.valueOf(input);
            if (selectedValue < commands.size()) {
                selectedCommand = commands.get(selectedValue);
            }
        }
        return selectedCommand;
    }
}
