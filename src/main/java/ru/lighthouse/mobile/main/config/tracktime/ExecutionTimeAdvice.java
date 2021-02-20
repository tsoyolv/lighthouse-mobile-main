package ru.lighthouse.mobile.main.config.tracktime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.util.Date;

import static ru.lighthouse.mobile.main.core.DbDataTypeConstants.DATE_FORMAT;


@Aspect
@Component
@ConditionalOnExpression("${aspect.track-time.enabled}")
public class ExecutionTimeAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExecutionTimeAdvice.class);

    @Around("@annotation(TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String simpleName = className + "." + methodName;

        long start = System.nanoTime();
        Object object = point.proceed();
        long finish = System.nanoTime();

        log.debug("{} track method finished: {}. Processing time: {} milliseconds",
                  simpleName,
                  DATE_FORMAT.format(new Date()),
                  (finish - start) / 1_000_000);
        return object;
    }
}