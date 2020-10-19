package com.swfsspl.aspects.atm.domain.process.aspects;

import com.swfsspl.aspects.atm.domain.Bank;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Configuration
public class GenerateAuditLogAspect {
    @Around("execution(* com.swfsspl.aspects.atm.domain.process.commands.*.execute(..))")
    public void logTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Bank contextBank = (Bank) joinPoint.getArgs()[0];
        contextBank.getAuditLogs().add("Starting execution method from class: " + joinPoint.getTarget().getClass().getSimpleName() + " method: " + joinPoint.getSignature()
                .getName() + "at: " + LocalDateTime.now().toString());
        long startTime = System.currentTimeMillis();
        joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        contextBank.getAuditLogs().add("Finishing execution method from class: " + joinPoint.getTarget().getClass().getSimpleName() + " method: " + joinPoint.getSignature()
                .getName() + "at: " + LocalDateTime.now().toString());
        contextBank.getAuditLogs().add("Elapsed time in ms: " + (endTime - startTime));
    }
}
