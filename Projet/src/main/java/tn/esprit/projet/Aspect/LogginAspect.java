package tn.esprit.projet.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j

public class LogginAspect {

    @After("execution(* tn.esprit.projet.services.*.add*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method " + name + " : ");
    }
    @Before("execution(* tn.esprit.projet.services.*.add*(..))")
    public void logB4MethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("testBefore " + name + " : ");
    }
    @Around("execution(* tn.esprit.projet.services.*.add*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }

}
