package com.swfsspl.aspects.atm.domain.process.commands;

import com.swfsspl.aspects.atm.domain.Bank;

public interface ICommand {

    String getName();

    public void execute(Bank context) throws Exception;
}
