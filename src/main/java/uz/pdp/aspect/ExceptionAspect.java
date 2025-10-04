package uz.pdp.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {
    @AfterThrowing(pointcut = "execution(* uz.pdp.daos.*.*(..))", throwing = "ex")
    public void logException(Exception ex) {
        System.out.println("❌ Xatolik: " + ex.getMessage());
        // log faylga yozish mumkin
    }
}
