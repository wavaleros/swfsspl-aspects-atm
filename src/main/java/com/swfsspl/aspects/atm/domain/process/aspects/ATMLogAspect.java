package com.swfsspl.aspects.atm.domain.process.aspects;

import com.swfsspl.aspects.atm.domain.Bank;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class ATMLogAspect {

    @Before("execution(* com.swfsspl.aspects.atm.domain.process.commands.*.execute(..))")
    public void logTransaction(JoinPoint joinPoint) {
        Bank contextBank = (Bank) joinPoint.getArgs()[0];
        contextBank.getOperationLogs().add("Executing method from class :" + joinPoint.getTarget().getClass().getSimpleName() + " method: " + joinPoint.getSignature().getName());
    }


}
