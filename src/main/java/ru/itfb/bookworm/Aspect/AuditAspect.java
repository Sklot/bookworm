package ru.itfb.bookworm.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.itfb.bookworm.Entity.Audit;
import ru.itfb.bookworm.Repository.AuditRepository;


@Aspect
@Component
public class AuditAspect {

    AuditRepository repository;
    public AuditAspect(AuditRepository repository) {
        this.repository = repository;
    }

    @Pointcut("execution(* ru.itfb.bookworm.Controller..*(..))")
    public void auditAction(){
    }

    @Before(value = "auditAction()")
    public void auditBefore(JoinPoint joinPoint) {
        audit(joinPoint,"START");
        System.out.println("Открытие транзакции.");
    }
    @After(value = "auditAction()")
    public void auditAfter(JoinPoint joinPoint){
        audit(joinPoint,"CLOSED");
        System.out.println("Закрытие транзакции.");
    }
    @AfterThrowing(value = "auditAction()")
    public void auditThrow(JoinPoint joinPoint) {
        audit(joinPoint,"ERROR");
        System.out.println("Операция не удалась.");
    }

    private void audit(JoinPoint joinPoint,String statusCode){
        Audit audit = new Audit(joinPoint.getSignature().getName(), statusCode);
        repository.save(audit);
    }


}
