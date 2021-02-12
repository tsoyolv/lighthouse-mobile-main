package ru.lighthouse.mobile.main.config.tracktime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Aspect
@Component
@ConditionalOnExpression("${aspect.track-time.enabled}")
public class ExecutionTimeAdvice {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd+HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(ExecutionTimeAdvice.class);

    @Around("@annotation(TrackExecutionTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        String simpleName = className + "." + methodName;
        Date start = new Date();
        log.debug("{} Processing start: {}", simpleName, DATE_FORMAT.format(start));

        Object object = point.proceed();

        Date end = new Date();
        log.debug("{} Processing finished: {}. Processing time: {} milliseconds", simpleName, DATE_FORMAT.format(end), end.getTime() - start.getTime());
        return object;
    }
}