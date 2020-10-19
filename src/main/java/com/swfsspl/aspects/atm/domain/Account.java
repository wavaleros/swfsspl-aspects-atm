package com.swfsspl.aspects.atm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String number;
    private String password;
    private int balance;


    public void changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
        }
    }

    public void consign(long amount) throws Exception {
        if (amount < 0) {
            throw new Exception("No se puede consignar un valor negativo");
        }
        this.balance += amount;
    }

    public void withdraw(long amount) throws Exception {
        if (amount < 0) {
            throw new Exception("No se puede retirar un valor negativo");
        }
        if (amount > this.balance) {
            throw new Exception("No se puede retirar un valor mayor al saldo");
        }
        this.balance -= amount;
    }
}
