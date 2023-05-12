package windwish.com.example.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect : aop로 쓸 수 있음
@Component
@Aspect
public class TimeTraceAop {

    //공통 사항 타겟팅 : Around : windwish.com.example.hellospring 하위 다 적용
    @Around("execution(* windwish.com.example.hellospring..*(..))")

    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //시간 가져옴
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
